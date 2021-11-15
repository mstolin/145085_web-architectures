import {Member} from "./member";
import {MemberParty} from "./member-party";

export interface DataResponse {

  members: Member[];
  memberParties: MemberParty[];

}
