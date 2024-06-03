import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ObjectiveService } from '@services/objective.service';
import { AccordionModule } from "primeng/accordion";
import {ConfirmationService, MessageService} from 'primeng/api';
import { ButtonModule } from "primeng/button";
import { EditorModule } from 'primeng/editor';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from "primeng/inputtextarea";
import { TableModule } from "primeng/table";
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { PreviewPlanComponent } from '../preview-plan/preview-plan.component';
import { Objective } from '@models/objective';
import {DialogModule} from "primeng/dialog";
import {RippleModule} from "primeng/ripple";
import {ConfirmDialogModule} from "primeng/confirmdialog";

@Component({
  selector: 'app-arch-elaboration-plan-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    PreviewPlanComponent,
    AccordionModule,
    InputTextareaModule,
    ButtonModule,
    TableModule,
    EditorModule,
    ToastModule,
    ToolbarModule,
    DialogModule,
    RippleModule,
    ConfirmDialogModule,
  ],
  providers: [
    MessageService,
    ConfirmationService
  ],
  templateUrl: './arch-elaboration-plan-form.component.html',
  styleUrl: './arch-elaboration-plan-form.component.css',
})
export class ArchElaborationPlanFormComponent implements OnInit {
  entityFormGroup = new FormGroup({
    approach: new FormControl(''),
  });
  objectiveFormGroup = new FormGroup({
    description: new FormControl(''),
    rationale: new FormControl('')
  });
  submitted: boolean = false;
  objectiveDialog: boolean = false;
  objectives!: Objective[];

  // archPlan$ = new Subject<Entity | null>();
  activeIndex: number | undefined = 0;

  constructor(
    // private entityService: EntityService,
    private objectiveService: ObjectiveService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    // if (this.entityService.entity != null) {
    //   this.entityFormGroup.setValue({ ...this.entityService.entity });
    // }
    // this.entityFormGroup.valueChanges.subscribe((data) => {
    //   this.onArchPlanFormChange(data);
    // });
    this.objectives = this.objectiveService.browse();
  }

  // onArchPlanFormChange(data: any): void {
  //   this.entityService.entity = { ...data };
  //   // this.archPlan$.next(this.entityService.entity);
  // }

  activeIndexChange(index: number) {
    this.activeIndex = index;
  }

  // addEntityContext() {
  //   const parcialEntity = {
  //     name: this.entityFormGroup.get('name')?.value || '',
  //     background: this.entityFormGroup.get('background')?.value || '',
  //     purpose: this.entityFormGroup.get('purpose')?.value || '',
  //     scope: this.entityFormGroup.get('scope')?.value || '',
  //     objectives: [],
  //     approach: '',
  //     resourcesandschedule: '',
  //     riskandmitigation: '',
  //   };
  //   this.entityService.add(parcialEntity);
  //   this.activeIndexChange(1);
  // }

  openObjectiveDialog(): void {
    this.submitted = false;
    this.objectiveDialog = true;
  }

  hideObjectiveDialog(): void {
    this.objectiveDialog = false;
    this.submitted = false;
  }

  saveObjective(): void {
    this.submitted = true;
    const objective = {
      id: 0,
      description: this.objectiveFormGroup.get('description')?.value || '',
      rationale: this.objectiveFormGroup.get('rationale')?.value || '',
      entity: null
    }
    this.objectiveService.add(objective);
    this.objectiveDialog = false;
    this.objectiveFormGroup.reset();
  }

  removeObjective(objective: Objective) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + objective.description + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.objectives = this.objectives.filter((val) => val.description !== objective.description);
        this.messageService.add({severity: 'success', summary: 'Sucessful', detail: 'Objective Deleted', life: 3000})
      }
    });
  }
}
