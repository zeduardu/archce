import {Component, OnInit} from '@angular/core';
import {CustomizedEditorComponent} from "../../customized-editor/customized-editor.component";
import {ArchitecturePlan} from "../../../../../data/types/architecture-plan";
import {Subject} from "rxjs";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {PreviewPlanComponent} from "../preview-plan/preview-plan.component";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-arch-elaboration-plan-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    CustomizedEditorComponent,
    PreviewPlanComponent
  ],
  templateUrl: './arch-elaboration-plan-form.component.html',
  styleUrl: './arch-elaboration-plan-form.component.css'
})
export class ArchElaborationPlanFormComponent implements OnInit {
  archPlanForm = new FormGroup({
    entity: new FormControl(''),
    background: new FormControl(''),
    purpose: new FormControl(''),
    scope: new FormControl(''),
    approach: new FormControl(''),
    resourcesandschedule: new FormControl(''),
    riskandmitigation: new FormControl(''),
    conclusion: new FormControl(''),
  });
  archPlan: ArchitecturePlan = {
    entity: '',
    background: '',
    purpose: '',
    scope: '',
    approach: '',
    resourcesandschedule: '',
    riskandmitigation: '',
    conclusion: ''
  };
  archPlan$ = new Subject<ArchitecturePlan>();

  ngOnInit(): void {
    this.archPlanForm.valueChanges.subscribe((data) => {
      this.onArchPlanFormChange(data);
    });
  }

  onArchPlanFormChange(data: any) {
    this.archPlan = { ...this.archPlan, ...data };
    this.archPlan$.next(this.archPlan);
  }
}
