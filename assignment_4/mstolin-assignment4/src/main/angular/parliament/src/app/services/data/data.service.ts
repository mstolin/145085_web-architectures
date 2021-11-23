import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {forkJoin, Observable, from} from "rxjs";
import {DataResponse} from "../../models/responses/data-response";
import {MemberService} from "../member/member.service";
import {MemberPartyService} from "../member-party/member-party.service";
import {PartyService} from "../party/party.service";
import {WebsiteService} from "../website/website.service";
import {Member} from "../../models/member";
import {MemberParty} from "../../models/member-party";
import {Party} from "../../models/party";
import {Website} from "../../models/website";

type DataRequestSources = {
  members: Observable<any>;
  memberParties: Observable<any>;
  parties: Observable<any>;
  websites: Observable<any>;
}

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private _members: Member[] = [];
  private _memberParties: MemberParty[] = [];
  private _parties: Party[] = [];
  private _websites: Website[] = [];

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

  private isDataAlreadyFetched(): boolean {
    return this._members.length > 0
      && this._memberParties.length > 0
      && this._parties.length > 0
      && this._websites.length > 0;
  }

  public fetchData(): Promise<DataResponse> {
    /*
    TODO:

    - services fuer alle models machen
    - in den services auf richtiges model mappen
    - typen hier und in den components anpassen
     */
    if (!this.isDataAlreadyFetched()) {
      let memberData = this.memberService.fetchData();
      let memberPartiesData = this.memberPartyService.fetchData();
      let partyData = this.partyService.fetchData();
      let websiteData = this.websiteService.fetchData();
      let requestSources: DataRequestSources = {
        members: memberData,
        memberParties: memberPartiesData,
        parties: partyData,
        websites: websiteData
      };

      let promise = new Promise<DataResponse>((resolve, reject) => {
        forkJoin(requestSources)
          .subscribe(responses => {
            this._members = responses.members;
            this._memberParties = responses.memberParties;
            this._parties = responses.parties;
            this._websites = responses.websites;

            this.members$ = from(this._members);
            this.memberParties$ = from(this._memberParties);
            this.parties$ = from(this._parties);
            this.websites$ = from(this._websites);

            resolve(responses)
          }, error => {
            reject(error);
          });
      });

      return promise;
    } else {
      return new Promise<DataResponse>((resolve, _) => {
        resolve({
          members: this._members,
          memberParties: this._memberParties,
          parties: this._parties,
          websites: this._websites
        });
      });
    }
  }

  get members(): Member[] {
    return this._members;
  }

  get memberParties(): MemberParty[] {
    return this._memberParties;
  }

  get parties(): Party[] {
    return this._parties;
  }

  get websites(): Website[] {
    return this._websites;
  }

}
