import { Component } from '@angular/core';
import {StepperModule} from "primeng/stepper";
import {ButtonModule} from "primeng/button";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-elaboration',
  standalone: true,
  imports: [
    StepperModule,
    ButtonModule,

  ],
  templateUrl: './elaboration.component.html',
  styleUrl: './elaboration.component.css'
})
export class ElaborationComponent {
  stepForm1 = new FormGroup({

  });

}
