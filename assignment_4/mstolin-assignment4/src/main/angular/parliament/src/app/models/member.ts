export class Member {
  private _id: number;
  private _name: string;
  private _birthdate?: string; // ToDo: Member birthdate spaeter zu type Date, manchmal protected dann optional
  private _photoUrl: string;
  private _parties: string[];
  private _websites: string[];


  constructor(id: number, name: string, birthdate: string, photoUrl: string, parties: string[], websites: string[]) {
    this._id = id;
    this._name = name;
    this._birthdate = birthdate;
    this._photoUrl = photoUrl;
    this._parties = parties;
    this._websites = websites;
  }

  get id(): number {
    return this._id;
  }

  get name(): string {
    return this._name;
  }

  get birthdate(): string {
    return this._birthdate ?? "";
  }

  get photoUrl(): string {
    return this._photoUrl;
  }

  get parties(): string[] {
    return this._parties;
  }

  get websites(): string[] {
    return this._websites;
  }

}
