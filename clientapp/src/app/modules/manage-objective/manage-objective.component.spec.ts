import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageObjectiveComponent } from './manage-objective.component';

describe('ManageObjectiveComponent', () => {
  let component: ManageObjectiveComponent;
  let fixture: ComponentFixture<ManageObjectiveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageObjectiveComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageObjectiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
