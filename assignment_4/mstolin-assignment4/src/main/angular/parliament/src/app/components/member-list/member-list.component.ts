import { Component, OnInit } from '@angular/core';
import {Member} from "../../models/member";
import {DataService} from "../../services/data/data.service";

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
      .then(members => {
        this.members = members;
      })
      .catch(error => {
        console.log('ERROR', error);
      });
  }

}
