import { Component, HostListener, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { EditorModule } from 'primeng/editor';
import { InputTextModule } from 'primeng/inputtext';
import { StepperModule } from 'primeng/stepper';
import { CustomizedEditorComponent } from './component/customized-editor/customized-editor.component';
import { ArchElaborationPlanFormComponent } from './component/preparing-efforts/arch-elaboration-plan-form/arch-elaboration-plan-form.component';
import { PreviewPlanComponent } from './component/preparing-efforts/preview-plan/preview-plan.component';
import { DesigningViewpointsFormComponent } from './component/designing-viewpoints/designing-viewpoints-form/designing-viewpoints-form.component';

@Component({
  selector: 'app-elaboration',
  standalone: true,
  imports: [
    StepperModule,
    ButtonModule,
    ReactiveFormsModule,
    InputTextModule,
    EditorModule,
    PreviewPlanComponent,
    CustomizedEditorComponent,
    ArchElaborationPlanFormComponent,
    DesigningViewpointsFormComponent
  ],
  templateUrl: './elaboration.component.html',
  styleUrl: './elaboration.component.css',
})
export class ElaborationComponent implements OnInit {
  public screenWidth: any;

  constructor() {}

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
  }

  @HostListener('window:resize', ['$event.target'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
  }
}
