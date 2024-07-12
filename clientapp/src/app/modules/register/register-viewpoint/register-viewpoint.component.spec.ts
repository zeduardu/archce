import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterViewpointComponent } from './register-viewpoint.component';

describe('RegisterViewpointComponent', () => {
  let component: RegisterViewpointComponent;
  let fixture: ComponentFixture<RegisterViewpointComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterViewpointComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterViewpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
