import {MemberResponse} from "./responses/member-response";

export class Member {
  private readonly _id: number;
  private readonly _firstName: string;
  private readonly _lastName: string;
  private readonly _birthdate?: Date;
  private readonly _photoUrl?: string;

  constructor(memberResponse: MemberResponse) {
    this._id = memberResponse.PersonID;

    let splitName = this.getSplitName(memberResponse.ParliamentaryName)
    this._firstName = splitName.firstName;
    this._lastName = splitName.lastName;

    if (memberResponse.PhotoURL.length > 0) {
      this._photoUrl = memberResponse.PhotoURL;
    } else {
      this._photoUrl = undefined;
    }

    if (typeof memberResponse.BirthDate !== 'undefined') {
      this._birthdate = new Date(memberResponse.BirthDate);
    }
  }

  get id(): number {
    return this._id;
  }

  get firstName(): string {
    return this._firstName;
  }

  get lastName(): string {
    return this._lastName;
  }

  get fullName(): string {
    return `${this.firstName} ${this.lastName}`;
  }

  get birthdate(): Date | undefined {
    return this._birthdate;
  }

  get photoUrl(): string | undefined {
    return this._photoUrl;
  }

  private getSplitName(name: string): {firstName: string, lastName: string} {
    let splitName = name.split(',');
    return {
      firstName: splitName[1],
      lastName: splitName[0]
    };
  }
}
