import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder } from '@angular/forms';
import { EntityInterest } from '@models/entity-interest';
import { Stakeholder } from '@models/stakeholder';
import { EntityInterestService } from '@services/entity-interest.service';
import {ViewpointService} from "@services/viewpoint.service";

@Component({
  selector: 'app-register-viewpoint',
  templateUrl: './register-viewpoint.component.html',
  styleUrl: './register-viewpoint.component.css',
})
export class RegisterViewpointComponent implements OnInit {
  @Input() selectedEnityOfInterest!: EntityInterest;

  viewpointFormGroup = this.formBuilder.group({
    name: [''],
    overview: [''],
    concerns: this.formBuilder.array([
      this.formBuilder.group({
        matter: [''],
        selectedStakeholders: ['']
      }),
    ]),
  });

  dialogAddStakeholder: boolean = false;
  stakeholders!: Stakeholder[];

  constructor(
    private entityOfInterestService: EntityInterestService,
    private viewpointService: ViewpointService,
    private formBuilder: FormBuilder,
  ) {}

  ngOnInit(): void {
    this.entityOfInterestService.getEntityInterest(2).subscribe({
      next: (entityOfInterest) => {
        this.selectedEnityOfInterest = entityOfInterest;
        this.stakeholders = entityOfInterest.stakeholders;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  get concerns() {
    return this.viewpointFormGroup.get('concerns') as FormArray;
  }

  addConcern() {
    this.concerns.push(
      this.formBuilder.group({
        matter: [''],
        selectedStakeholders: ['']
      }),
    );
  }

  openDialogAddStakeholder() {
    this.dialogAddStakeholder = true;
  }

  onUpload(event: any): void {
    for (let file of event.files) {
      this.uploadedFile(file);
    }
  }

  uploadedFile(file: any): void {
    const formData = new FormData();
    formData.append('file', file, file.name);

    this.viewpointService.uploadFile(formData);
  }
}
