import {
  ChangeDetectorRef,
  Component,
  HostListener,
  OnInit,
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { EditorModule } from 'primeng/editor';
import { InputTextModule } from 'primeng/inputtext';
import { StepperModule } from 'primeng/stepper';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { NgForOf, NgIf } from '@angular/common';
import { PanelModule } from 'primeng/panel';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { BrowseAllEntitiesInterestRes } from '@models/BrowseAllEntitiesInterestRes';
import { EntityInterest } from '@models/entity-interest';
import { BrowseByEntityInterestIdObjectivesRes } from '@models/BrowseByEntityInterestIdObjectivesRes';
import { Objective } from '@models/objective';
import { Stakeholder } from '@models/stakeholder';
import { EntityInterestService } from '@services/entity-interest.service';
import { ObjectiveService } from '@services/objective.service';
import { StakeholderService } from '@services/stakeholder.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { AddObjectiveReq } from '@models/add-objective-req';
import { InputGroupModule } from 'primeng/inputgroup';
import { ViewpointService } from '@services/viewpoint.service';
import { Subject } from 'rxjs';
import { Viewpoint } from '@models/viewpoint';
import { ToolbarModule } from 'primeng/toolbar';

@Component({
  selector: 'app-elaboration',
  standalone: true,
  imports: [
    StepperModule,
    ButtonModule,
    ReactiveFormsModule,
    InputTextModule,
    EditorModule,
    ConfirmDialogModule,
    DialogModule,
    DropdownModule,
    NgForOf,
    NgIf,
    PanelModule,
    RippleModule,
    TableModule,
    ToastModule,
    FormsModule,
    InputGroupModule,
    ToolbarModule,
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './elaboration.component.html',
  styleUrl: './elaboration.component.css',
})
export class ElaborationComponent implements OnInit {
  public screenWidth: any;

  activeIndex: number | undefined = 0;

  entitiesOfInterest!: BrowseAllEntitiesInterestRes[];
  selectedEntityOfInterest!: EntityInterest;
  entityOfInterest!: EntityInterest;

  objectiveSubmitted: boolean = false;
  objectiveDialog: boolean = false;
  objectives!: BrowseByEntityInterestIdObjectivesRes[];
  objective!: Objective;

  stakeholders!: Stakeholder[];

  viewpointDialog: boolean = false;
  viewpointSubmitted: boolean = false;
  viewpoint!: Viewpoint;
  viewpoints!: Viewpoint[];

  designingViewpointsForm = this.formBuilder.group({
    entity: [''],
    viewpoints: this.formBuilder.array([]),
  });
  viewpoint$ = new Subject<Viewpoint>();
  unsubscribe$ = new Subject<void>();
  entityField: string | undefined = '';
  viewpointName: string | undefined = '';

  constructor(
    private entityInterestService: EntityInterestService,
    private objectiveService: ObjectiveService,
    private stakeholderService: StakeholderService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private viewpointService: ViewpointService,
    private formBuilder: FormBuilder,
    private changeDetectorRef: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.screenWidth = window.innerWidth;
    this.entityOfInterest = {
      id: 0,
      name: '',
      background: '',
      purpose: '',
      scope: '',
      approach: '',
      schedule: '',
      milestones: '',
    };
    this.entityInterestService.getEntitiesInterest().subscribe({
      next: (data) => {
        this.entitiesOfInterest = data;
      },
    });
    // this.entityField = this.entityService.entity?.name;
    this.designingViewpointsForm.valueChanges.subscribe((data) => {
      this.onArchPlanFormChange(data);
    });
  }

  activeIndexChange(index: number) {
    this.activeIndex = index;
  }

  removeObjective(objective: Objective) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + objective.description + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.objectives = this.objectives.filter(
          (val) => val.description !== objective.description,
        );
        this.messageService.add({
          severity: 'success',
          summary: 'Sucessful',
          detail: 'Objective Deleted',
          life: 3000,
        });
      },
    });
  }

  fillObjectivesByEntityInterestId(value: any) {
    this.entityOfInterest = value;
    this.objectiveService.getObjectivesByEntityInterestId(value.id).subscribe({
      next: (objectives) => {
        this.objectives = objectives;
      },
      error: (error) => {
        if (error.status == 404) {
          this.objectives = [];
        }
      },
    });
    this.stakeholderService
      .getStakeholdersByEntityInterestId(value.id)
      .subscribe({
        next: (stakeholders) => {
          this.stakeholders = stakeholders;
        },
        error: (error) => {
          if (error.status == 404) {
            this.stakeholders = [];
          }
        },
      });
  }

  openNewObjectiveDialog() {
    this.objectiveDialog = true;
    this.objectiveSubmitted = false;
    this.objective = {
      description: '',
      entity: null,
      id: 0,
      rationale: '',
    };
  }

  openNewStakeholderDialog() {}

  openNewViewpointDialog() {
    this.viewpointDialog = true;
    this.viewpointSubmitted = false;
    this.viewpoint = {
      id: 0,
      name: '',
      overview: '',
      concerns: [],
      conventions: '',
      rationale: '',
      sources: ''
    }
  }

  hideObjectiveDialog() {
    this.objectiveDialog = false;
    this.objectiveSubmitted = false;
  }

  addObjective() {
    this.objectiveSubmitted = true;

    if (this.objective.description?.trim()) {
      if (this.objective.id) {
        this.objectives[this.findIndexById(this.objective.id.toString())] =
          this.objective;
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Objective updated',
          life: 3000,
        });
      } else {
        const addObjectiveReq: AddObjectiveReq = {
          description: this.objective.description,
          rationale: this.objective.rationale,
          entityInterestId: this.selectedEntityOfInterest!.id,
        };
        this.objectiveService.createObjective(addObjectiveReq).subscribe({
          next: (objective) => {
            this.objectives = [...this.objectives, objective];
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Objective created',
              life: 3000,
            });
          },
          error: (error) => {
            console.error(error);
          },
        });
      }
      this.objectiveDialog = false;
      this.objective = {
        description: '',
        entity: null,
        id: 0,
        rationale: '',
      };
    }
  }

  editObjective(objective: Objective) {
    this.objective = { ...objective };
    this.objectiveDialog = true;
  }

  deleteObjective(objective: Objective) {
    this.confirmationService.confirm({
      message:
        'Are you sure you want to delete: ' + objective.description + ' ?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.objectiveService.deleteObjective(objective.id).subscribe({
          next: () => {
            this.objectives = this.objectives.filter(
              (val) => val.id !== objective.id,
            );
            this.objective = {
              description: '',
              entity: null,
              id: 0,
              rationale: '',
            };
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Objective deleted',
              life: 3000,
            });
          },
          error: (error) => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Error deleting objective',
              life: 3000,
            });
          },
        });
      },
    });
  }

  findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.objectives.length; i++) {
      if (this.objectives[i].id.toString() === id) {
        index = i;
        break;
      }
    }

    return index;
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
}
