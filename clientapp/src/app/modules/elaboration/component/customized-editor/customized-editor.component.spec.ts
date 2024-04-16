import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomizedEditorComponent } from './customized-editor.component';

describe('CustomizedEditorComponent', () => {
  let component: CustomizedEditorComponent;
  let fixture: ComponentFixture<CustomizedEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomizedEditorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CustomizedEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
