import { Component, Input } from '@angular/core';
import { EntityInterest } from '@models/entity-interest';
import { Stakeholder } from '@models/stakeholder';
import { StakeholderService } from '@services/stakeholder.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-register-stakeholder',
  //standalone: true,
  //imports: [RegisterModule],
  templateUrl: './register-stakeholder.component.html',
  styleUrl: './register-stakeholder.component.css'
})
export class RegisterStakeholderComponent {
  @Input() selectedEntityOfInterest: EntityInterest | undefined;
  stakeholder!: Stakeholder;
  stakeholderTypes: string[] = [];

  constructor(
    private stakeholderService: StakeholderService,
    private messageService: MessageService
  ) {}

  addStakeholder(): void {
    this.stakeholderService.addStakeholder(this.stakeholder).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Stakeholder added',
        });
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
}
