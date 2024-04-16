import {Component, Input} from '@angular/core';
import {EditorModule} from "primeng/editor";
import {PaginatorModule} from "primeng/paginator";
import {SharedModule} from "primeng/api";

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
export class CustomizedEditorComponent {
  @Input() editorFormControlName!: string;
}
