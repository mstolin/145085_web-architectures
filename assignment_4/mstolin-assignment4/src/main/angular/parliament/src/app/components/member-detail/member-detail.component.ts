import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {filter, Observable, of, groupBy, mergeMap, toArray, reduce, flatMap, map, mergeAll} from 'rxjs';
import { switchMap } from 'rxjs/operators';
import {DataService} from "../../services/data/data.service";
import {Member} from "../../models/member";
import {Website} from "../../models/website";
import {Party} from "../../models/party";
import {MemberParty} from "../../models/member-party";
import {flatten} from "@angular/compiler";

@Component({
  selector: 'app-member-detail',
  templateUrl: './member-detail.component.html',
  styleUrls: ['./member-detail.component.css']
})
export class MemberDetailComponent implements OnInit {

  private memberId$?: Observable<number>;

  member?: Member;
  websites: Website[] = [];
  parties: Party[] = [];

  constructor(private route: ActivatedRoute, private dataService: DataService) { }

  ngOnInit(): void {
    this.receiveMemberId();

    if (typeof this.memberId$ !== 'undefined') {
      this.memberId$.subscribe(id => {
        // get current member
        this.dataService.members$!.pipe(
          filter(member => member.PersonID == id)
        ).subscribe(member => { this.member = member; });

        // get websites
        this.dataService.websites$?.pipe(
          filter(website => website.PersonID == id)
        ).subscribe(website => { this.websites.push(website) });

        // get parties
        this.dataService.memberParties$?.pipe(
          filter(memberParty => memberParty.PersonID == id), // only for current member
          groupBy(memberParty => memberParty.PartyID), // group duplicate parties ...
          mergeMap(group =>
            group.pipe(
              toArray(),
              map(groupedParties => this.mapPartyHistory(groupedParties))
            )
          ),
          map(memberParty => {
            // TODO: Map das in ein anderes model, sodass man noch das until und from anzeigen kann
            // und danach sortieren
            return this.getParty(memberParty.PartyID);
          })
        ).subscribe(party => this.parties.push(party));
      });
    }
  }

  private receiveMemberId(): void {
    this.memberId$ = this.route.paramMap.pipe(
      switchMap(params => {
        let memberId = Number(params.get('memberId'));
        return of(memberId);
      })
    );
  }

  private mapPartyHistory(parties: MemberParty[]): MemberParty {
    let first = parties[0];

    // get smallest from
    let from = parties
      .map(party => new Date(party.ValidFromDate))
      .reduce((previous, current) => {
        return (previous < current) ? previous : current;
      });

    // get highest until
    const now = new Date();
    let until = parties
      .map(party => {
        if (party.ValidUntilDate != null) {
          return new Date(party.ValidUntilDate);
        } else {
          return now;
        }
      })
      .reduce((previous, current) => {
        return (previous < current) ? current : previous;
      });

    first.ValidFromDate = from.toDateString();
    first.ValidUntilDate = until.toDateString();

    return first;
  }

  private getParty(id: number): Party {
    return this.dataService
      .parties
      .filter(party => party.ID == id)[0];
  }

}
