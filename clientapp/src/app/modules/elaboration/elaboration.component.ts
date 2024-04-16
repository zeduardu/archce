import { JsonPipe } from "@angular/common";
import { Component, HostListener, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { EditorModule } from "primeng/editor";
import { InputTextModule } from "primeng/inputtext";
import { StepperModule } from "primeng/stepper";
import { CustomizedEditorComponent } from "./component/customized-editor/customized-editor.component";
import { PreviewPlanComponent } from "./component/preview-plan/preview-plan.component";

@Component({
  selector: 'app-elaboration',
  standalone: true,
  imports: [
    StepperModule,
    ButtonModule,
    ReactiveFormsModule,
    InputTextModule,
    EditorModule,
    CustomizedEditorComponent,
    PreviewPlanComponent,
    JsonPipe
  ],
  templateUrl: './elaboration.component.html',
  styleUrl: './elaboration.component.css'
})
export class ElaborationComponent implements OnInit{
  public screenWidth: any;
  archPlanForm = this.formBuilder.group({
    entity: [''],
    background: [''],
    purpose: [''],
    scope: [''],
    approach: [''],
    resources:[''],
    schedule: [''],
    riskandmitigation: [''],
    conclusion: [''],
  })

  constructor(private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
      this.screenWidth = window.innerWidth;
  }

  @HostListener('window:resize', ['$event.target'])
  onResize(event: any) {
    this.screenWidth = window.innerWidth;
  }
}
