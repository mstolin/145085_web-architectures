import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Member} from "../../models/member";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private membersApiUrl: string = 'https://data.parliament.scot/api/members';

  constructor(private http: HttpClient) { }

  public fetchData(): Observable<Member[]> {
    return this.http.get<Member[]>(this.membersApiUrl);
  }
}
