import {Member} from "./member";
import {MemberParty} from "./member-party";
import {Party} from "./party";
import {Website} from "./website";

export interface DataResponse {

  members: Member[];
  memberParties: MemberParty[];
  parties: Party[];
  websites: Website[];

}
