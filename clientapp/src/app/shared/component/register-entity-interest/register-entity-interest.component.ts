import { NgStyle } from "@angular/common";
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from "@angular/forms";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { EditorModule } from "primeng/editor";
import { InputTextModule } from "primeng/inputtext";
import { PaginatorModule } from "primeng/paginator";

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
    ReactiveFormsModule,
  ],
  templateUrl: './register-entity-interest.component.html',
  styleUrl: './register-entity-interest.component.css',
})
export class RegisterEntityInterestComponent {
  @Input() visible: boolean = false; // Property to control dialog visibility

  @Output() onSave = new EventEmitter<any>(); // Event emitted when Save button is clicked
  @Output() onCancel = new EventEmitter<any>(); // Event emitted when Cancel button is clicked

  entityFormGroup = new FormGroup({
    entityContext: new FormGroup({
      name: new FormControl(''),
      background: new FormControl(''),
      purpose: new FormControl(''),
      scope: new FormControl(''),
    }),
  });

  constructor() {  }

  closeDialog() {
    this.visible = false;
    this.onCancel.emit();
  }

  save() {
    this.onSave.emit();
    this.visible = false;
  }

  addEntityContext() {
    const parcialEntity = {
      name: this.entityFormGroup.get('name')?.value || '',
      background: this.entityFormGroup.get('background')?.value || '',
      purpose: this.entityFormGroup.get('purpose')?.value || '',
      scope: this.entityFormGroup.get('scope')?.value || '',
      objectives: [],
      approach: '',
      resourcesandschedule: '',
      riskandmitigation: '',
    };
  }
}
