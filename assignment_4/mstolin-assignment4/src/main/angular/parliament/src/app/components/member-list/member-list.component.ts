import { Component, OnInit } from '@angular/core';
import {DataCacheService} from "../../services/data/data-cache.service";
import {Member} from "../../models/member";

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {

  members: Member[] = [];

  constructor(private dataCacheService: DataCacheService) { }

  ngOnInit(): void {
    this.dataCacheService
      .fetchData()
      .then(dataResponse =>
        dataResponse.members$.subscribe(member => this.members.push(member))
      )
      .catch(error => console.log('ERROR', error));
  }

}
