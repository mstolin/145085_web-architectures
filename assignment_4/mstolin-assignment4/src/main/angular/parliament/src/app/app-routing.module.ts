import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MemberListComponent} from "./components/member-list/member-list.component";
import {MemberDetailComponent} from "./components/member-detail/member-detail.component";

const routes: Routes = [
  { path: '', component: MemberListComponent },
  { path: 'detail', component: MemberDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
