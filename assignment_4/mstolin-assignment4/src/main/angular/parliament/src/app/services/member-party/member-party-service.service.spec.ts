import { TestBed } from '@angular/core/testing';

import { MemberPartyServiceService } from './member-party-service.service';

describe('MemberPartyServiceService', () => {
  let service: MemberPartyServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MemberPartyServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
