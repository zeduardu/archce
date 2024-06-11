import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageViewpointComponent } from './manage-viewpoint.component';

describe('ManageViewpointComponent', () => {
  let component: ManageViewpointComponent;
  let fixture: ComponentFixture<ManageViewpointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageViewpointComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageViewpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
