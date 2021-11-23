import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data/data.service";
import {Member} from "../../models/member";

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {

  members: Member[] = [];

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
