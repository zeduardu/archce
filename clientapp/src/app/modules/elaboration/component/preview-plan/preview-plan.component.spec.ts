import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreviewPlanComponent } from './preview-plan.component';

describe('PreviewPlanComponent', () => {
  let component: PreviewPlanComponent;
  let fixture: ComponentFixture<PreviewPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PreviewPlanComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PreviewPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
