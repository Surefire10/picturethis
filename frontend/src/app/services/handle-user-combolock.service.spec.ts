import { TestBed } from '@angular/core/testing';

import { ConstructUserCombolockService } from './construct-user-combo-lock.service';

describe('ConstructUserCombolockService', () => {
  let service: ConstructUserCombolockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConstructUserCombolockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
