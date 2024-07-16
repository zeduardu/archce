import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageConcernComponent } from './manage-concern.component';

describe('ManageConcernComponent', () => {
  let component: ManageConcernComponent;
  let fixture: ComponentFixture<ManageConcernComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageConcernComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageConcernComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
