import {Component, Input, OnInit} from '@angular/core';
import {Member} from "../../models/member";
import {ActivatedRoute, Router} from "@angular/router";

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
    this.router.navigate(['/detail', this.member.PersonID], {relativeTo: this.route});
  }

}
