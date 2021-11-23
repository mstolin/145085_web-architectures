import {MemberResponse} from "./responses/member-response";

export class Member {
  private readonly _id: number;
  private readonly _name: string;
  private readonly _birthdate?: Date;
  private readonly _photoUrl: string;


  constructor(memberResponse: MemberResponse) {
    this._id = memberResponse.PersonID;
    this._name = memberResponse.ParliamentaryName;
    this._photoUrl = memberResponse.PhotoURL;

    if (typeof memberResponse.BirthDate !== 'undefined') {
      this._birthdate = new Date(memberResponse.BirthDate);
    }
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get birthdate(): Date | undefined {
    return this._birthdate;
  }

  get photoUrl(): string {
    return this._photoUrl;
  }
}
