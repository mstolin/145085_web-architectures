import { Component, OnInit } from '@angular/core';
import {MemberResponse} from "../../models/responses/member-response";
import {DataService} from "../../services/data/data.service";

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {

  members: MemberResponse[] = [];

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService
      .fetchData()
      .then(dataResponse => {
        this.members = dataResponse.members;
      })
      .catch(error => {
        console.log('ERROR', error);
      });
  }

}
