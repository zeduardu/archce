import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Concern} from "@models/concern";
import {InputTextareaModule} from "primeng/inputtextarea";
import {Stakeholder} from "@models/stakeholder";
import {StakeholderService} from "@services/stakeholder.service";

@Component({
  selector: 'app-manage-concern',
  standalone: true,
  imports: [FormsModule, InputTextareaModule],
  templateUrl: './manage-concern.component.html',
  styleUrl: './manage-concern.component.css',
})
export class ManageConcernComponent implements OnInit {
  concern!: Concern;
  stakeholders!: Stakeholder[];

  constructor(
    private stakeHolderService: StakeholderService,
  ) {}

  ngOnInit(): void {
    this.stakeHolderService.getStakeholdersByEntityInterestId(1).subscribe(stakeholders => {
      this.stakeholders = stakeholders;
    });
  }
}
