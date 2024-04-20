import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
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
    DesigningViewpointsPreviewComponent,
    PreviewPlanComponent,
    CustomizedEditorComponent,
    FormsModule,
    InputTextModule,
    ReactiveFormsModule,
  ],
  templateUrl: './designing-viewpoints-form.component.html',
  styleUrl: './designing-viewpoints-form.component.css',
})
export class DesigningViewpointsFormComponent implements OnInit, OnDestroy {
  designingViewpointsForm = new FormGroup({
    entity: new FormControl(''),
    overview: new FormControl(''),
    purpose: new FormControl(''),
    scope: new FormControl(''),
    approach: new FormControl(''),
    resourcesandschedule: new FormControl(''),
    riskandmitigation: new FormControl(''),
    conclusion: new FormControl(''),
  });
  viewpoint: Viewpoint = {
    entity: '',
    background: '',
    purpose: '',
    scope: '',
    approach: '',
    resourcesandschedule: '',
    riskandmitigation: '',
    conclusion: '',
  };
  viewpoint$ = new Subject<Viewpoint>();
  unsubscribe$ = new Subject<void>();
  entityField: string | undefined = '';

  constructor(
    private entityService: EntityService,
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
}
