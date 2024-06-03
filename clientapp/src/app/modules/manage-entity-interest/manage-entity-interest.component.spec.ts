import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageEntityInterestComponent } from './manage-entity-interest.component';

describe('ManageEntityInterestComponent', () => {
  let component: ManageEntityInterestComponent;
  let fixture: ComponentFixture<ManageEntityInterestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageEntityInterestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageEntityInterestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
