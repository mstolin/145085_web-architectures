import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Website} from "../../models/website";
import {WebsiteResponse} from "../../models/responses/website-response";

@Injectable({
  providedIn: 'root'
})
export class WebsiteService {

  private readonly websitesApiUrl: string = 'https://data.parliament.scot/api/websites';

  constructor(private http: HttpClient) { }

  public fetchData(): Observable<Website[]> {
    return this.http
      .get<WebsiteResponse[]>(this.websitesApiUrl)
      .pipe(
        map(response => response.map(websiteResponse => new Website(websiteResponse)))
      );
  }

}
