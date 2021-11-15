import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { Observable, of } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import {DataService} from "../../services/data/data.service";
import {Member} from "../../models/member";

@Component({
  selector: 'app-member-detail',
  templateUrl: './member-detail.component.html',
  styleUrls: ['./member-detail.component.css']
})
export class MemberDetailComponent implements OnInit {

  private memberId$?: Observable<number>;

  member?: Member;

  constructor(private route: ActivatedRoute, private dataService: DataService) { }

  ngOnInit(): void {
    this.receiveMemberId();

    if (typeof this.memberId$ !== 'undefined') {
      this.memberId$.subscribe(id => {
        this.member = this.dataService.members.find(member => member.PersonID == id);
      });
    }
  }

  private receiveMemberId(): void {
    this.memberId$ = this.route.paramMap.pipe(
      switchMap(params => {
        let memberId = Number(params.get('memberId'));
        return of(memberId);
      })
    );
  }

}
