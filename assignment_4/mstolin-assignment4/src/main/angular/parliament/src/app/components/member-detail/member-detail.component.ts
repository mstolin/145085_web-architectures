import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {filter, Observable, of, groupBy, mergeMap, toArray, map, first} from 'rxjs';
import { switchMap } from 'rxjs/operators';
import {DataCacheService} from "../../services/data/data-cache.service";
import {Member} from "../../models/member";
import {Website} from "../../models/website";
import {Party} from "../../models/party";
import {MemberParty} from "../../models/member-party";

type Membership = {
  memberParty: MemberParty
  from: Date
  until?: Date
}

type PartyMembership = {
  party: Party
  from: String
  until: String
}

@Component({
  selector: 'app-member-detail',
  templateUrl: './member-detail.component.html',
  styleUrls: ['./member-detail.component.css']
})
export class MemberDetailComponent implements OnInit {

  private memberId$?: Observable<number>;

  member?: Member;
  websites: Website[] = [];
  partyMemberships: PartyMembership[] = [];

  constructor(private route: ActivatedRoute, private dataCacheService: DataCacheService) { }

  ngOnInit(): void {
    this.receiveMemberId();

    if (typeof this.memberId$ !== 'undefined') {
      this.memberId$.subscribe(memberId => {
        this.receiveMember(memberId);
        this.receiveWebsites(memberId);
        this.receiveParties(memberId);
      });
    }
  }

  getFormattedBirthdate(date: Date): string {
    return `${date.getMonth()}, ${date.getDay()} ${date.getFullYear()}`;
  }

  private receiveMemberId(): void {
    this.memberId$ = this.route.paramMap.pipe(
      switchMap(params => {
        let memberId = Number(params.get('memberId'));
        return of(memberId);
      })
    );
  }

  private receiveMember(memberId: number): void {
    this.dataCacheService.members$!.pipe(
      filter(member => member.id == memberId)
    ).subscribe(member => this.member = member);
  }

  private receiveWebsites(memberId: number) {
    this.dataCacheService.websites$?.pipe(
      filter(website => website.personId == memberId)
    ).subscribe(website => this.websites.push(website));
  }

  private receiveParties(memberId: number) {
    this.dataCacheService.memberParties$?.pipe(
      filter(memberParty => memberParty.personId == memberId), // only for current member
      groupBy(memberParty => memberParty.partyId), // group duplicate parties ...
      mergeMap(group =>
        group.pipe(
          toArray(),
          map(groupedParties => this.generateMembership(groupedParties)) // map to membership
        )
      )
    ).subscribe(membership => {
      this.dataCacheService.parties$?.pipe(
        filter(party => party.id == membership.memberParty.partyId), // only parties of current member
        map(party => this.generatePartyMembership(party, membership)) // generate partyMemberships
      ).subscribe(partyMembership => this.partyMemberships.push(partyMembership)); // push to array
    });
  }

  private generateMembership(parties: MemberParty[]): Membership {
    let memberParty = parties[0];

    // get smallest from
    let from = parties
      .map(party => party.validFrom)
      .reduce((previous, current) =>
        (previous < current) ? previous : current
      );

    // get highest until
    const now = new Date();
    let until = parties
      .map(party => {
        if (party.validUntil != null) {
          return party.validUntil;
        } else {
          return now;
        }
      })
      .reduce((previous, current) =>
        (previous < current) ? current : previous
      );

    return {
      memberParty,
      from,
      until: until != now ? until: undefined
    };
  }

  private generatePartyMembership(party: Party, membership: Membership): PartyMembership {
    let from = this.getFormattedDate(membership.from);
    let until = typeof membership.until !== 'undefined'
      ? this.getFormattedDate(membership.until)
      : 'Today';

    return {party, from, until};
  }

  private getFormattedDate(date: Date): string {
    return `${date.getMonth()}/${date.getFullYear()}`;
  }

}
