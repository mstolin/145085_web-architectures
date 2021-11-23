import {PartyResponse} from "./responses/party-response";

export class Party {

  private readonly _id: number;
  private readonly _abbreviation: string;
  private readonly _name: string;
  private readonly _preferredName: string;

  constructor(partyResponse: PartyResponse) {
    this._id = partyResponse.ID;
    this._name = partyResponse.ActualName;
    this._preferredName = partyResponse.PreferredName;
    this._abbreviation = partyResponse.Abbreviation;
  }

  get id(): number {
    return this._id;
  }

  get abbreviation(): string {
    return this._abbreviation;
  }

  get name(): string {
    return this._name;
  }

  get preferredName(): string {
    return this._preferredName;
  }

}
