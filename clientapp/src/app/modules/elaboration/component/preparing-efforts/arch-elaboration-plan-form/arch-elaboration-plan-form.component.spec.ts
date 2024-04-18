import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArchElaborationPlanFormComponent } from './arch-elaboration-plan-form.component';

describe('ArchElaborationPlanFormComponent', () => {
  let component: ArchElaborationPlanFormComponent;
  let fixture: ComponentFixture<ArchElaborationPlanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ArchElaborationPlanFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArchElaborationPlanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
