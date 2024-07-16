import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterEntityInterestComponent } from './register-entity-interest.component';

describe('RegisterEntityInterestComponent', () => {
  let component: RegisterEntityInterestComponent;
  let fixture: ComponentFixture<RegisterEntityInterestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterEntityInterestComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegisterEntityInterestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
