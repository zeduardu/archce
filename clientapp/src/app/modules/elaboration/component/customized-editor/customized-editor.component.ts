import {Component, Input, OnInit} from '@angular/core';
import {EditorModule} from "primeng/editor";
import {PaginatorModule} from "primeng/paginator";
import {SharedModule} from "primeng/api";
import { FormArray, FormControl, FormControlName, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-customized-editor',
  standalone: true,
    imports: [
      EditorModule,
      PaginatorModule,
      SharedModule
    ],
  templateUrl: './customized-editor.component.html',
  styleUrl: './customized-editor.component.css'
})
export class CustomizedEditorComponent implements OnInit {
  @Input() formControlName!: String;
  @Input() control!: FormControl;
  ngOnInit(): void {
  }
}
