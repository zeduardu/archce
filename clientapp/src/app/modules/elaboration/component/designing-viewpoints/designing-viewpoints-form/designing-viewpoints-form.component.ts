import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { NgFor } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Subject } from 'rxjs';
import { EntityService } from 'src/app/data/service/entity.service';
import { Viewpoint } from '../../../../../data/types/viewpoint';
import { CustomizedEditorComponent } from '../../customized-editor/customized-editor.component';
import { PreviewPlanComponent } from '../../preparing-efforts/preview-plan/preview-plan.component';
import { DesigningViewpointsPreviewComponent } from '../designing-viewpoints-preview/designing-viewpoints-preview.component';

@Component({
  selector: 'app-designing-viewpoints-form',
  standalone: true,
  imports: [
    NgFor,
    DesigningViewpointsPreviewComponent,
    PreviewPlanComponent,
    CustomizedEditorComponent,
    FormsModule,
    InputTextModule,
    ReactiveFormsModule,
    ButtonModule
  ],
  templateUrl: './designing-viewpoints-form.component.html',
  styleUrl: './designing-viewpoints-form.component.css',
})
export class DesigningViewpointsFormComponent implements OnInit, OnDestroy {
  designingViewpointsForm = this.formBuilder.group({
    entity: [''],
    viewpoints: this.formBuilder.array([
      this.formBuilder.group({
        name: [''],
        overview: [''],
        concerns: [''],
        stakeholders: [''],
        stakeholdersPerspectives: [''],
        problemPitfalls: [''],
        applicability: [''],
        views: [''],
        models: [''],
      }),
    ]),
  });
  viewpoint: Viewpoint = {
  };
  viewpoint$ = new Subject<Viewpoint>();
  unsubscribe$ = new Subject<void>();
  entityField: string | undefined = '';
  viewpointName: string | undefined = '';

  constructor(
    private entityService: EntityService,
    private formBuilder: FormBuilder,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.entityField = this.entityService.entity?.entity;
    this.designingViewpointsForm.valueChanges.subscribe((data) => {
      this.onArchPlanFormChange(data);
    });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  onArchPlanFormChange(data: any) {
    this.viewpoint = { ...this.viewpoint, ...data };
    this.viewpoint$.next(this.viewpoint);
  }

  get viewpoints() {
    return this.designingViewpointsForm.get('viewpoints') as FormArray;
  }

  addViewpoint() {
    this.viewpoints.push(
      this.formBuilder.group({
        name: [''],
        overview: [''],
        concerns: [''],
        stakeholders: [''],
        stakeholdersPerspectives: [''],
        problemPitfalls: [''],
        applicability: [''],
        views: [''],
        models: [''],
      })
    );
  }
}
