import { Component } from '@angular/core';
import {StepperModule} from "primeng/stepper";
import {ButtonModule} from "primeng/button";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-elaboration',
  standalone: true,
  imports: [
    StepperModule,
    ButtonModule,
    ReactiveFormsModule,

  ],
  templateUrl: './elaboration.component.html',
  styleUrl: './elaboration.component.css'
})
export class ElaborationComponent {
  stepForm1 = new FormGroup({
    entity: new FormControl(''),
    background: new FormControl(''),
    purpose: new FormControl(''),
    scope: new FormControl(''),
    approach: new FormControl(''),
    resources: new FormControl(''),
    schedule: new FormControl(''),
    risksandmitigation: new FormControl(''),
    conclusion: new FormControl(''),
  });

}
