import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Member} from "../../models/member";

@Component({
  selector: 'app-member-card',
  templateUrl: './member-card.component.html',
  styleUrls: ['./member-card.component.css']
})
export class MemberCardComponent implements OnInit {

  @Input() member!: Member;

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onClick(): void {
    this.router.navigate(['/detail', this.member.id], {relativeTo: this.route});
  }

  getProfilePictureUrl(): string {
    if (this.member.photoUrl.length > 0) {
      return this.member.photoUrl;
    } else {
      return 'assets/dummy-profile-pic.png';
    }
  }

}
