import {Component, Input, OnInit} from '@angular/core';
import {Viewpoint} from "@models/viewpoint";

@Component({
  selector: 'app-register-view',
  standalone: true,
  imports: [],
  templateUrl: './register-view.component.html',
  styleUrl: './register-view.component.css'
})
export class RegisterViewComponent implements OnInit {
  @Input() selectedViewpoint!: Viewpoint;

  ngOnInit() {

  }
}
