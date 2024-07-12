import { Component, Input, OnInit } from '@angular/core';
import { EntityInterest } from '@models/entity-interest';
import { Stakeholder } from '@models/stakeholder';
import { EntityInterestService } from '@services/entity-interest.service';
import { StakeholderService } from '@services/stakeholder.service';
import { MessageService } from 'primeng/api';
import {StakeholderType} from "@models/stakeholder-type";
import {AddObjectiveReq} from "@models/add-objective-req";

@Component({
  selector: 'app-register-stakeholder',
  //standalone: true,
  //imports: [RegisterModule],
  templateUrl: './register-stakeholder.component.html',
  styleUrl: './register-stakeholder.component.css'
})
export class RegisterStakeholderComponent implements OnInit{
  @Input() selectedEntityOfInterest: EntityInterest | undefined;
  stakeholder!: Stakeholder;
  stakeholderTypes: string[] = [];
  empyStakeholder: Stakeholder = {
    id: 0,
    name: '',
    type: '',
    entityInterestId: 0,
  };

  constructor(
    private entityOfInterestService: EntityInterestService,
    private stakeholderService: StakeholderService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.entityOfInterestService.getEntityInterest(2).subscribe({
      next: (response) => {
        this.selectedEntityOfInterest = response;
      },
    });
    this.stakeholder = this.empyStakeholder;
    this.stakeholderService.browseAllStakeholderTypes().subscribe({
      next: (response) => {
        this.stakeholderTypes = response;
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error,
        });
      },
    });
  }

  addStakeholder(): void {
    if (this.stakeholder.name?.trim()) {
      if (this.stakeholder.id) {
        this.stakeholderService.editStakeholder(this.stakeholder).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Stakeholder updated',
              life: 3000,
            });
          },
          error: (error) => {
            console.error(error);
          },
        });
      } else {
        this.stakeholder.entityInterestId = <number>this.selectedEntityOfInterest?.id;
        this.stakeholderService.addStakeholder(this.stakeholder).subscribe({
          next: (stakeholder) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Stakeholder created',
              life: 3000,
            });
          },
          error: (error) => {
            console.error(error);
          },
        });
      }
      this.stakeholder = this.empyStakeholder;
    }
  }
}
