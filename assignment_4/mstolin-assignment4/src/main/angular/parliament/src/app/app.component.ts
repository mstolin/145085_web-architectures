import { Component } from '@angular/core';
import {Member} from "./models/member";
import {MemberService} from "./services/member.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  members: Member[] = [];

  constructor(private memberService: MemberService) {

  }

  ngOnInit(): void {
     this.memberService
       .fetchMembers()
       .subscribe(members => {
         this.members = members;
       });
  }

}
