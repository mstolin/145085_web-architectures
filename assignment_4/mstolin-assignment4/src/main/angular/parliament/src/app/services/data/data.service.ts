import { Injectable } from '@angular/core';
import {Member} from "../../models/member";
import {HttpClient} from "@angular/common/http";
import {forkJoin} from "rxjs";
import {MemberParty} from "../../models/member-party";
import {DataResponse} from "../../models/data-response";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private membersApiUrl: string = 'https://data.parliament.scot/api/members';
  private memberPartiesApiUrl: string = 'https://data.parliament.scot/api/memberparties';

  private _members: Member[] = [];
  private _memberParties: MemberParty[] = [];

  constructor(private http: HttpClient) { }

  private isDataAlreadyFetched(): boolean {
    return this._members.length > 0 && this._memberParties.length > 0;
  }

  public fetchData(): Promise<DataResponse> {
    if (!this.isDataAlreadyFetched()) {
      let membersRequest = this.http.get<Member[]>(this.membersApiUrl);
      let memberPartyRequest = this.http.get<MemberParty[]>(this.memberPartiesApiUrl);

      let promise = new Promise<DataResponse>((resolve, reject) => {
        forkJoin([membersRequest, memberPartyRequest])
          .subscribe(responses => {
            let members: Member[] = responses[0];
            let memberParties: MemberParty[] = responses[1];

            this._members = members;
            this._memberParties = memberParties;

            resolve({members, memberParties})
          }, error => {
            reject(error);
          });
      });

      return promise;
    } else {
      return new Promise<DataResponse>((resolve, _) => {
        resolve({members: this._members, memberParties: this._memberParties});
      });
    }
  }

  get members(): Member[] {
    return this._members;
  }

  get memberParties(): MemberParty[] {
    return this._memberParties;
  }

}
