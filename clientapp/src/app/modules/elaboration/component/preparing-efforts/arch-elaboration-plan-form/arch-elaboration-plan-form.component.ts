import { Component, OnInit } from '@angular/core';
import { CustomizedEditorComponent } from '../../customized-editor/customized-editor.component';
import { Entity } from '../../../../../data/types/entity';
import { Subject } from 'rxjs';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { PreviewPlanComponent } from '../preview-plan/preview-plan.component';
import { InputTextModule } from 'primeng/inputtext';
import { EntityService } from 'src/app/data/service/entity.service';

@Component({
  selector: 'app-arch-elaboration-plan-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    CustomizedEditorComponent,
    PreviewPlanComponent,
  ],
  templateUrl: './arch-elaboration-plan-form.component.html',
  styleUrl: './arch-elaboration-plan-form.component.css',
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
  archPlan$ = new Subject<Entity | null>();

  constructor(private entityService: EntityService) {}

  ngOnInit(): void {
    if (this.entityService.entity != null) {
      this.archPlanForm.setValue({ ...this.entityService.entity });
    }
    this.archPlanForm.valueChanges.subscribe((data) => {
      this.onArchPlanFormChange(data);
    });
  }

  onArchPlanFormChange(data: any) {
    this.entityService.entity = { ...data };
    this.archPlan$.next(this.entityService.entity);
  }
}
