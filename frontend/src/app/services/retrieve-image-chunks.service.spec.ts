import { TestBed } from '@angular/core/testing';

import { RetrieveImageChunksService } from './retrieve-image-chunks.service';

describe('RetrieveImageChunksService', () => {
  let service: RetrieveImageChunksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RetrieveImageChunksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
