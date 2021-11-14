import { Component } from '@angular/core';
import {Member} from "./models/member";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  members: Member[] = [
      new Member(
        1735,
        "Constance, Angela",
        "1970-07-15T00:00:00",
        "http://scottishparliament.thirdlight.com/file/35820129812",
        [],
        []
      ),
      new Member(
        1741,
        "Goldie, Annabel",
        "",
        "",
        [],
        []
      ),
      new Member(
        1742,
        "Ewing, Annabelle",
        "1960-08-20T00:00:00",
        "http://scottishparliament.thirdlight.com/file/35960261933",
        [],
        []
      )
  ];

}
