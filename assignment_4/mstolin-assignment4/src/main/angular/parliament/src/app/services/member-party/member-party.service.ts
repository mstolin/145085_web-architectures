import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {MemberParty} from "../../models/member-party";
import {MemberPartyResponse} from "../../models/responses/member-party-response";

@Injectable({
  providedIn: 'root'
})
export class MemberPartyService {

  private readonly memberPartyApiUrl: string = 'https://data.parliament.scot/api/memberparties';

  constructor(private http: HttpClient) { }

  public fetchData(): Observable<MemberParty[]> {
    return this.http
      .get<MemberPartyResponse[]>(this.memberPartyApiUrl)
      .pipe(
        map(response => response.map(memberPartyResponse => new MemberParty(memberPartyResponse)))
      );
  }

}
