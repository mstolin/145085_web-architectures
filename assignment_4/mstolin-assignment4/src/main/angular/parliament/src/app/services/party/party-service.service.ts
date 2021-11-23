import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Party} from "../../models/party";
import {PartyResponse} from "../../models/responses/party-response";

@Injectable({
  providedIn: 'root'
})
export class PartyServiceService {

  private readonly partiesApiUrl: string = 'https://data.parliament.scot/api/parties';

  constructor(private http: HttpClient) { }

  public fetchData(): Observable<Party[]> {
    return this.http
      .get<PartyResponse[]>(this.partiesApiUrl)
      .pipe(
        map(response => {
          return response.map(partyResponse => new Party(partyResponse));
        })
      );
  }

}
