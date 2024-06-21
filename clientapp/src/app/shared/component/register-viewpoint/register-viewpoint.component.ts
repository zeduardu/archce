import { NgFor } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormArray, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EntityInterest } from '@models/entity-interest';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { FieldsetModule } from 'primeng/fieldset';
import { InputTextModule } from 'primeng/inputtext';
import { ListboxModule } from 'primeng/listbox';
import { PanelModule } from 'primeng/panel';

@Component({
  selector: 'app-register-viewpoint',
  standalone: true,
  imports: [
    NgFor,
    ReactiveFormsModule,
    FieldsetModule,
    ButtonModule,
    InputTextModule,
    ListboxModule,
    DialogModule
  ],
  templateUrl: './register-viewpoint.component.html',
  styleUrl: './register-viewpoint.component.css',
})
export class RegisterViewpointComponent {
  @Input() selectedEnityOfInterest!: EntityInterest;

  registerForm = this.formBuilder.group({
    concerns: this.formBuilder.array([this.formBuilder.control('')]),
  });

  dialogAddStakeholder: boolean = false;

  constructor(private formBuilder: FormBuilder) {
    this.selectedEnityOfInterest = {
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

  get concerns() {
    return this.registerForm.get('concerns') as FormArray;
  }

  addConcern() {
    this.concerns.push(this.formBuilder.control(''));
  }

  openDialogAddStakeholder() {
    this.dialogAddStakeholder = true;
  }
}
