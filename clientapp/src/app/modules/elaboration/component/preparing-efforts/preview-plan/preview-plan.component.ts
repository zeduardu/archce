import { JsonPipe, NgIf } from '@angular/common';
import {ChangeDetectorRef, Component, Input, OnDestroy, OnInit} from '@angular/core';
import { FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';
import { ButtonModule } from 'primeng/button';
import { Subject, takeUntil } from 'rxjs';
import { ArchitecturePlan } from 'src/app/data/types/architecture-plan';
import { ElaborationComponent } from '../../../elaboration.component';
import {ArchElaborationPlanFormComponent} from "../arch-elaboration-plan-form/arch-elaboration-plan-form.component";

@Component({
  selector: 'app-preview-plan',
  standalone: true,
  imports: [NgIf, JsonPipe, ButtonModule],
  templateUrl: './preview-plan.component.html',
  styleUrl: './preview-plan.component.css',
})
export class PreviewPlanComponent implements OnInit, OnDestroy {
  archPlan: ArchitecturePlan | null = null;
  unsubscribe$ = new Subject<void>();

  constructor(
    private elaborationComponent: ArchElaborationPlanFormComponent,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.elaborationComponent.archPlan$
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe((data) => {
        this.archPlan = data;
        this.changeDetectorRef.detectChanges();
      });
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  render(): void {}
}
