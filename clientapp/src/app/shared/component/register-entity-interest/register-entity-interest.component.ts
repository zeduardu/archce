import { NgStyle } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { EditorModule } from 'primeng/editor';
import { InputTextModule } from 'primeng/inputtext';
import { PaginatorModule } from 'primeng/paginator';
import { EntityInterest } from '@models/entity-interest';
import { EntityInterestService } from '@services/entity-interest.service';
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-register-entity-interest',
  standalone: true,
  imports: [
    DialogModule,
    ButtonModule,
    NgStyle,
    EditorModule,
    InputTextModule,
    PaginatorModule,
    FormsModule,
    ToastModule,
  ],
  providers: [MessageService],
  templateUrl: './register-entity-interest.component.html',
  styleUrl: './register-entity-interest.component.css',
})
export class RegisterEntityInterestComponent {
  @Input() visible: boolean = false; // Property to control dialog visibility

  @Output() onSave = new EventEmitter<any>(); // Event emitted when Save button is clicked
  @Output() onCancel = new EventEmitter<any>(); // Event emitted when Cancel button is clicked

  entityOfInterest!: EntityInterest;

  constructor(
    private entityInterestService: EntityInterestService,
    private messageService: MessageService,
  ) {
    this.entityOfInterest = {
      id: 0,
      name: '',
      background: '',
      purpose: '',
      scope: '',
      approach: '',
      schedule: '',
      milestones: '',
      stakeholders: [],
    };
  }

  closeDialog() {
    this.visible = false;
    this.onCancel.emit();
  }

  save() {
    this.entityInterestService.registerEntityInterest(this.entityOfInterest).subscribe({
      next: (response) => {
        this.messageService.add({ severity: 'success', summary: 'Success', detail: `Entity created.` });
      },
      error: (error) => {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: `Failed to create entity.` });
      },
    });
    this.onSave.emit();
    this.visible = false;
  }
}
