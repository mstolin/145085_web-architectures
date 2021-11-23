import { TestBed } from '@angular/core/testing';

import { MemberPartyService } from './member-party.service';

describe('MemberPartyServiceService', () => {
  let service: MemberPartyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MemberPartyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
