import { TestBed } from '@angular/core/testing';

import { UserInformationPostService } from './user-information-post.service';

describe('UserInformationPostService', () => {
  let service: UserInformationPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserInformationPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
