import { Injectable } from '@angular/core';
import {Member} from "../../models/member";
import {HttpClient} from "@angular/common/http";
import {forkJoin, Observable, from} from "rxjs";
import {MemberParty} from "../../models/member-party";
import {DataResponse} from "../../models/data-response";
import {Party} from "../../models/party";
import {Website} from "../../models/website";
import {MemberService} from "../members/member.service";

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

  private membersApiUrl: string = 'https://data.parliament.scot/api/members';
  private memberPartiesApiUrl: string = 'https://data.parliament.scot/api/memberparties';
  private partiesApiUrl: string = 'https://data.parliament.scot/api/parties';
  private websitesApiUrl: string = 'https://data.parliament.scot/api/websites';

  private _members: Member[] = [];
  private _memberParties: MemberParty[] = [];
  private _parties: Party[] = [];
  private _websites: Website[] = [];

  members$?: Observable<Member>;
  memberParties$?: Observable<MemberParty>;
  parties$?: Observable<Party>;
  websites$?: Observable<Website>;


  constructor(private http: HttpClient, private memberService: MemberService) { }

  private isDataAlreadyFetched(): boolean {
    return this._members.length > 0
      && this._memberParties.length > 0
      && this._parties.length > 0
      && this._websites.length > 0;
  }

  public fetchData(): Promise<DataResponse> {
    if (!this.isDataAlreadyFetched()) {
      let membersRequest = this.memberService.fetchData();
      let memberPartiesRequest = this.http.get<MemberParty[]>(this.memberPartiesApiUrl);
      let partiesRequest = this.http.get<Party[]>(this.partiesApiUrl);
      let websitesRequest = this.http.get<Website[]>(this.websitesApiUrl);
      let requestSources: DataRequestSources = {
        members: membersRequest,
        memberParties: memberPartiesRequest,
        parties: partiesRequest,
        websites: websitesRequest
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
