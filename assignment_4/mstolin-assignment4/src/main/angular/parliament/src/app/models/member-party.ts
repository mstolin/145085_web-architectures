import {MemberPartyResponse} from "./responses/member-party-response";

export class MemberParty {

  private readonly _id: number;
  private readonly _partyId: number;
  private readonly _personId: number;
  private readonly _validFrom: Date;
  private readonly _validUntil?: Date;

  constructor(memberPartyResponse: MemberPartyResponse) {
    this._id = memberPartyResponse.ID;
    this._partyId = memberPartyResponse.PartyID;
    this._personId = memberPartyResponse.PersonID;
    this._validFrom = new Date(memberPartyResponse.ValidFromDate);

    if (typeof memberPartyResponse.ValidUntilDate !== 'undefined') {
      this._validUntil = new Date(memberPartyResponse.ValidUntilDate);
    }
  }

  get id(): number {
    return this._id;
  }

  get partyId(): number {
    return this._partyId;
  }

  get personId(): number {
    return this._personId;
  }

  get validFrom(): Date {
    return this._validFrom;
  }

  get validUntil(): Date | undefined {
    return this._validUntil;
  }

}
