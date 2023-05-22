import { TestBed } from '@angular/core/testing';

import { UserInformationValidationService } from './user-information-validation.service';

describe('UserInformationValidationService', () => {
  let service: UserInformationValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInformationValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
