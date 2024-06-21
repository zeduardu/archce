import {Component, Input, OnInit} from '@angular/core';
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {Viewpoint} from "@models/viewpoint";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {InputTextareaModule} from "primeng/inputtextarea";
import {EntityInterest} from "@models/entity-interest";

@Component({
  selector: 'app-manage-viewpoint',
  standalone: true,
  imports: [
    ButtonModule,
    RippleModule,
    SharedModule,
    TableModule,
    InputTextModule,
    FormsModule,
    InputTextareaModule,
  ],
  templateUrl: './manage-viewpoint.component.html',
  styleUrl: './manage-viewpoint.component.css',
})
export class ManageViewpointComponent implements OnInit {
  @Input()
  selectedEntityOfInterest!: EntityInterest;

  viewpoint!: Viewpoint;
  viewpoints!: Viewpoint[];
  ngOnInit(): void {
    this.viewpoint = {
      id: 0,
      name: '',
      overview: '',
      concerns: [
        {
          id: 0,
          matter: '',
          stakeholders: [
            {
              id: 0,
              name: '',
              type: '',
              entityInterest: {
                id: 0,
                name: '',
                background: '',
                purpose: '',
                scope: '',
                approach: '',
                schedule: '',
                milestones: '',
                stakeholders: [],
              },
            },
          ],
        },
      ],
      model: '',
      conventions: '',
      rationale: '',
      sources: '',
    };
    this.viewpoints = [];
  }
  openNewViewpointDialog() {}
}
