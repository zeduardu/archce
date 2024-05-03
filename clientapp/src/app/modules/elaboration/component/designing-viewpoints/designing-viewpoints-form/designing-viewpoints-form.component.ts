import { NgFor, NgIf } from '@angular/common';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Viewpoint } from '@models/viewpoint';
import { CustomizedEditorComponent } from '@modules/elaboration/component/customized-editor/customized-editor.component';
import { EntityService } from '@services/entity.service';
import { ViewpointService } from '@services/viewpoint.service';
import { ButtonModule } from 'primeng/button';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from 'primeng/inputtext';
import { Subject } from 'rxjs';
import { PreviewPlanComponent } from '../../preparing-efforts/preview-plan/preview-plan.component';
import { DesigningViewpointsPreviewComponent } from '../designing-viewpoints-preview/designing-viewpoints-preview.component';

@Component({
  selector: 'app-designing-viewpoints-form',
  standalone: true,
  imports: [
    NgIf,
    NgFor,
    DesigningViewpointsPreviewComponent,
    PreviewPlanComponent,
    CustomizedEditorComponent,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
    InputGroupModule,
    InputGroupAddonModule,
  ],
  templateUrl: './designing-viewpoints-form.component.html',
  styleUrl: './designing-viewpoints-form.component.css',
})
export class DesigningViewpointsFormComponent implements OnInit, OnDestroy {
  designingViewpointsForm = this.formBuilder.group({
    entity: [''],
    viewpoints: this.formBuilder.array([]),
  });
  viewpoint$ = new Subject<Viewpoint>();
  unsubscribe$ = new Subject<void>();
  entityField: string | undefined = '';
  viewpointName: string | undefined = '';

  constructor(
    private entityService: EntityService,
    private viewpointService: ViewpointService,
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
    this.viewpointService.viewpoint = {
      ...this.viewpointService.viewpoint,
      ...data,
    };
    if (this.viewpointService.viewpoint) {
      this.viewpoint$.next(this.viewpointService.viewpoint);
    }
  }

  get viewpoints() {
    return this.designingViewpointsForm.get('viewpoints') as FormArray;
  }

  addViewpoint(name: string) {
    console.log('addViewpoint');
    this.viewpoints.push(
      this.formBuilder.group({
        name: [name],
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

  removeViewpoint(index: number) {
    this.viewpoints.removeAt(index);
  }
}
