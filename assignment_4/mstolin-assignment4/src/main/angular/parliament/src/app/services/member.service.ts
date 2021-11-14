import {Injectable} from '@angular/core';
import {Member} from "../models/member";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MemberService {

  private apiUrl: string = 'https://data.parliament.scot/api/members';

  constructor(private http: HttpClient) {
  }

  public fetchMembers(): Observable<Member[]> {
    return this.http
      .get<Member[]>(this.apiUrl);
  }

}
