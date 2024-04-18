import { Component, Input } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { EditorModule } from 'primeng/editor';

@Component({
  selector: 'app-customized-editor',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    EditorModule
  ],
  templateUrl: './customized-editor.component.html',
  styleUrl: './customized-editor.component.css'
})
export class CustomizedEditorComponent {
  @Input() passedFormGroup!: FormGroup
  @Input() passedFormControlName!: string
}
