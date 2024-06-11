import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EntityInterest } from '@models/entity-interest';
import { Objective } from '@models/objective';
import { EntityInterestService } from '@services/entity-interest.service';
import { ObjectiveService } from '@services/objective.service';
import {ConfirmationService, MessageService} from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RippleModule } from 'primeng/ripple';
import { NgIf } from '@angular/common';
import {AddObjectiveReq} from "@models/add-objective-req";
import {CardModule} from "primeng/card";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import { BrowseByEntityInterestIdObjectivesRes } from '@models/BrowseByEntityInterestIdObjectivesRes';

@Component({
  selector: 'app-manage-objective',
  standalone: true,
  imports: [
    FormsModule,
    DropdownModule,
    ToastModule,
    ToolbarModule,
    ButtonModule,
    TableModule,
    InputTextModule,
    DialogModule,
    RippleModule,
    NgIf,
    CardModule,
    ConfirmDialogModule,
  ],
  providers: [
    EntityInterestService,
    ObjectiveService,
    MessageService,
    ConfirmationService,
  ],
  templateUrl: './manage-objective.component.html',
  styleUrl: './manage-objective.component.css',
})
export class ManageObjectiveComponent implements OnInit {
  entitiesOfInterest: EntityInterest[] | undefined;
  selectedEntityOfInterest: EntityInterest | undefined;

  objective!: Objective;
  objectivesByEntityInterestId!: BrowseByEntityInterestIdObjectivesRes[];
  selectedObjectives!: BrowseByEntityInterestIdObjectivesRes[] | null;
  displayDialog: boolean = false;
  submitted: boolean = false;

  constructor(
    private objectiveService: ObjectiveService,
    private entityOfInterestService: EntityInterestService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
  ) {}

  ngOnInit(): void {
    this.entityOfInterestService.getEntitiesInterest().subscribe({
      next: (entitiesOfInterest) => {
        this.entitiesOfInterest = entitiesOfInterest;
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  openNewObjectiveDialog() {
    this.displayDialog = true;
    this.submitted = false;
    this.objective = { description: '', entity: null, id: 0, rationale: '' };
  }

  deleteSelectedObjectives() {
    throw new Error('Method not implemented');
  }

  hideDialog() {
    this.displayDialog = false;
    this.submitted = false;
  }

  saveProduct() {
    this.submitted = true;

    if (this.objective.description?.trim()) {
      if (this.objective.id) {
        this.objectivesByEntityInterestId[
          this.findIndexById(this.objective.id.toString())
        ] = this.objective;
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
            this.objectivesByEntityInterestId = [
              ...this.objectivesByEntityInterestId,
              objective,
            ];
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
      this.displayDialog = false;
      this.objective = {
        description: '',
        entity: null,
        id: 0,
        rationale: '',
      };
    }
  }

  findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.objectivesByEntityInterestId.length; i++) {
      if (this.objectivesByEntityInterestId[i].id.toString() === id) {
        index = i;
        break;
      }
    }

    return index;
  }

  fillObjectivesByEntityInterestId(value: any) {
    this.objectiveService.getObjectivesByEntityInterestId(value.id).subscribe({
      next: (objectives) => {
        this.objectivesByEntityInterestId = objectives;
      },
      error: (error) => {
        if (error.status == 404) {
          this.objectivesByEntityInterestId = [];
        }
      },
    });
  }

  editObjective(objective: Objective) {
    this.objective = { ...objective };
    this.displayDialog = true;
  }

  deleteObjective(objective: Objective) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete: ' + objective.description + ' ?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.objectiveService.deleteObjective(objective.id).subscribe({
          next: () => {
            this.objectivesByEntityInterestId =
              this.objectivesByEntityInterestId.filter(
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
}
