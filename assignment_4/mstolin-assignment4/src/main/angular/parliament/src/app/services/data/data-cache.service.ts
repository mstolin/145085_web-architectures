import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {forkJoin, Observable, from} from "rxjs";
import {MemberService} from "../member/member.service";
import {MemberPartyService} from "../member-party/member-party.service";
import {PartyService} from "../party/party.service";
import {WebsiteService} from "../website/website.service";
import {Member} from "../../models/member";
import {MemberParty} from "../../models/member-party";
import {Party} from "../../models/party";
import {Website} from "../../models/website";

type DataRequestSources = {
  members: Observable<Member[]>;
  memberParties: Observable<MemberParty[]>;
  parties: Observable<Party[]>;
  websites: Observable<Website[]>;
}

type DataResponse = {
  members$: Observable<Member>;
  memberParties$: Observable<MemberParty>;
  parties$: Observable<Party>;
  websites$: Observable<Website>;
}

@Injectable({
  providedIn: 'root'
})
export class DataCacheService {

  members$?: Observable<Member>;
  memberParties$?: Observable<MemberParty>;
  parties$?: Observable<Party>;
  websites$?: Observable<Website>;

  constructor(
    private http: HttpClient,
    private memberService: MemberService,
    private memberPartyService: MemberPartyService,
    private partyService: PartyService,
    private websiteService: WebsiteService
  ) { }

  public fetchData(): Promise<DataResponse> {
    if (!this.isDataAlreadyFetched()) {
      let requestSources: DataRequestSources = {
        members: this.memberService.fetchData(),
        memberParties: this.memberPartyService.fetchData(),
        parties: this.partyService.fetchData(),
        websites: this.websiteService.fetchData()
      };

      let promise = new Promise<DataResponse>((resolve, reject) =>
        forkJoin(requestSources)
          .subscribe(responses => {
            this.members$ = from(responses.members);
            this.memberParties$ = from(responses.memberParties);
            this.parties$ = from(responses.parties);
            this.websites$ = from(responses.websites);

            resolve({
              members$: this.members$,
              memberParties$: this.memberParties$,
              parties$: this.parties$,
              websites$: this.websites$
            })
          }, error => {
            reject(error);
          })
      );

      return promise;
    } else {
      return new Promise<DataResponse>((resolve, _) => {
        resolve({
          members$: this.members$!,
          memberParties$: this.memberParties$!,
          parties$: this.parties$!,
          websites$: this.websites$!
        });
      });
    }
  }

  private isDataAlreadyFetched(): boolean {
    return typeof this.members$ !== 'undefined'
      && typeof this.memberParties$ !== 'undefined'
      && typeof this.parties$ !== 'undefined'
      && typeof this.websites$ !== 'undefined';
  }

}
