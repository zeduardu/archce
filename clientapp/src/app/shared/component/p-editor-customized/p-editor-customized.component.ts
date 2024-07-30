import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { EditorModule } from 'primeng/editor';

@Component({
  selector: 'app-p-editor-customized',
  standalone: true,
  imports: [EditorModule, FormsModule, NgIf],
  templateUrl: './p-editor-customized.component.html',
  styleUrl: './p-editor-customized.component.css'
})
export class PEditorCustomizedComponent {
  @Input() id: string | undefined;
  @Input() ngModel: any;
  @Input() style: any;
  @Input() help?: any;
}
