import { Injectable } from '@angular/core';
import {Member} from "../../models/member";
import {HttpClient} from "@angular/common/http";
import {forkJoin} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private membersApiUrl: string = 'https://data.parliament.scot/api/members';

  private _members: Member[] = [];

  constructor(private http: HttpClient) { }

  public fetchData(): Promise<Member[]> {
    let membersRequest = this.http.get<Member[]>(this.membersApiUrl);

    let promise = new Promise<Member[]>((resolve, reject) => {
      forkJoin([membersRequest])
        .subscribe(responses => {
          let members: Member[] = responses[0];
          this._members = members;
          resolve(members)
        }, error => {
          reject(error);
        });
    });

    return promise;
  }


  get members(): Member[] {
    return this._members;
  }

}
