import {Component, Input, OnInit} from '@angular/core';
import {MemberResponse} from "../../models/responses/member-response";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-member-card',
  templateUrl: './member-card.component.html',
  styleUrls: ['./member-card.component.css']
})
export class MemberCardComponent implements OnInit {

  @Input() member!: MemberResponse;

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onClick(): void {
    this.router.navigate(['/detail', this.member.PersonID], {relativeTo: this.route});
  }

}
