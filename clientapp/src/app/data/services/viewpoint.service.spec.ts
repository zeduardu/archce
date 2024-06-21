import { TestBed } from '@angular/core/testing';

import { ViewpointService } from './viewpoint.service';

describe('StakeholderServiceService', () => {
  let service: ViewpointService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewpointService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
