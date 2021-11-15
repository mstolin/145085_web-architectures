import { Injectable } from '@angular/core';
import {Member} from "../../models/member";
import {HttpClient} from "@angular/common/http";
import {forkJoin, Observable} from "rxjs";
import {MemberParty} from "../../models/member-party";
import {DataResponse} from "../../models/data-response";
import {Party} from "../../models/party";
import {Website} from "../../models/website";

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

  constructor(private http: HttpClient) { }

  private isDataAlreadyFetched(): boolean {
    return this._members.length > 0
      && this._memberParties.length > 0
      && this._parties.length > 0
      && this._websites.length > 0;
  }

  public fetchData(): Promise<DataResponse> {
    if (!this.isDataAlreadyFetched()) {
      let membersRequest = this.http.get<Member[]>(this.membersApiUrl);
      let memberPartiesRequest = this.http.get<MemberParty[]>(this.memberPartiesApiUrl);
      let partiesRequest = this.http.get<Party[]>(this.partiesApiUrl);
      let websitesRequest = this.http.get<Website[]>(this.websitesApiUrl);
      let requestSources: Observable<any>[] = [
        membersRequest,
        memberPartiesRequest,
        partiesRequest,
        websitesRequest
      ];

      let promise = new Promise<DataResponse>((resolve, reject) => {
        forkJoin(requestSources)
          .subscribe(responses => {
            let members: Member[] = responses[0];
            let memberParties: MemberParty[] = responses[1];
            let parties: Party[] = responses[2];
            let websites: Website[] = responses[3];

            this._members = members;
            this._memberParties = memberParties;
            this._parties = parties;
            this._websites = websites;

            resolve({members, memberParties, parties, websites})
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
