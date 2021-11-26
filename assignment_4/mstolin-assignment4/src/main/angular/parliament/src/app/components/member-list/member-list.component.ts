import { Component, OnInit } from '@angular/core';
import {DataCacheService} from "../../services/data/data-cache.service";
import {Member} from "../../models/member";

@Component({
  selector: 'app-member-list',
  templateUrl: './member-list.component.html',
  styleUrls: ['./member-list.component.css']
})
export class MemberListComponent implements OnInit {

  private readonly cols = 3;
  private countOfMembers = 0;

  grid: Member[][] = [];

  constructor(private dataCacheService: DataCacheService) { }

  ngOnInit(): void {
    this.dataCacheService
      .fetchData()
      .then(dataResponse =>
        dataResponse.members$.subscribe(member => this.addMemberToGrid(member))
      )
      .catch(error => console.log('ERROR', error));
  }

  private addMemberToGrid(member: Member): void {
    let colIndex = Math.floor(this.countOfMembers / this.cols);

    if (typeof this.grid[colIndex] !== 'undefined') {
      this.grid[colIndex].push(member);
    } else {
      let row: Member[] = [member];
      this.grid[colIndex] = row;
    }
    this.countOfMembers = this.countOfMembers + 1;
  }

}
