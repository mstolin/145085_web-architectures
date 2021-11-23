import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MemberResponse} from "../../models/responses/member-response";
import {map, Observable} from "rxjs";
import {Member} from "../../models/member";

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private readonly membersApiUrl: string = 'https://data.parliament.scot/api/members';

  constructor(private http: HttpClient) { }

  public fetchData(): Observable<Member[]> {
    return this.http
      .get<MemberResponse[]>(this.membersApiUrl)
      .pipe(
        map(response => response.map(memberResponse => new Member(memberResponse)))
      );
  }
}
