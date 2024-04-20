import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DesigningViewpointsPreviewComponent } from './designing-viewpoints-preview.component';

describe('DesigningViewpointsPreviewComponent', () => {
  let component: DesigningViewpointsPreviewComponent;
  let fixture: ComponentFixture<DesigningViewpointsPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DesigningViewpointsPreviewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DesigningViewpointsPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
