import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {JsonPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-preview-plan',
  standalone: true,
  imports: [
    NgIf,
    JsonPipe
  ],
  templateUrl: './preview-plan.component.html',
  styleUrl: './preview-plan.component.css'
})
export class PreviewPlanComponent implements OnInit{
  @Input() archPlanForm!: FormGroup;

  ngOnInit(): void {
  }
}
