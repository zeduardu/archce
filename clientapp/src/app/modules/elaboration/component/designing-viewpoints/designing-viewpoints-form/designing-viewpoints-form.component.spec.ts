import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesigningViewpointsFormComponent } from './designing-viewpoints-form.component';

describe('DesigningViewpointsFormComponent', () => {
  let component: DesigningViewpointsFormComponent;
  let fixture: ComponentFixture<DesigningViewpointsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesigningViewpointsFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DesigningViewpointsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
