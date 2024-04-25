import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
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
  _viewpoint: Viewpoint = {};
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
    this._viewpoint = { ...this._viewpoint, ...data };
    this.viewpoint$.next(this._viewpoint);
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
