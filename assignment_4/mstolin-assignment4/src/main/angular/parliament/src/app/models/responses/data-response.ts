import {MemberResponse} from "./member-response";
import {MemberPartyResponse} from "./member-party-response";
import {PartyResponse} from "./party-response";
import {WebsiteResponse} from "./website-response";

export interface DataResponse {

  members: MemberResponse[];
  memberParties: MemberPartyResponse[];
  parties: PartyResponse[];
  websites: WebsiteResponse[];

}
