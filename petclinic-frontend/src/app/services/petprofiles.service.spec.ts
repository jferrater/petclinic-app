import { TestBed } from '@angular/core/testing';

import { PetprofilesService } from './petprofiles.service';

describe('Petprofiles.ServiceService', () => {
  let service: PetprofilesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetprofilesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
