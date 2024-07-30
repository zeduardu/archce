import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PEditorCustomizedComponent } from './p-editor-customized.component';

describe('PEditorCustomizedComponent', () => {
  let component: PEditorCustomizedComponent;
  let fixture: ComponentFixture<PEditorCustomizedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PEditorCustomizedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PEditorCustomizedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
