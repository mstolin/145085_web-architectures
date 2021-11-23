import {WebsiteResponse} from "./responses/website-response";

export class Website {

  private readonly _id: number;
  private readonly _personId: number;
  private readonly _url: string;

  constructor(websiteResponse: WebsiteResponse) {
    this._id = websiteResponse.ID;
    this._personId = websiteResponse.PersonID;
    this._url = websiteResponse.WebURL;
  }

  get id(): number {
    return this._id;
  }

  get personId(): number {
    return this._personId;
  }

  get url(): string {
    return this._url;
  }

}
