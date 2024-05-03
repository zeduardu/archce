import { JsonPipe, NgIf } from '@angular/common';
import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { Subject, takeUntil } from 'rxjs';
import { Entity } from 'src/app/data/models/entity';
import { ArchElaborationPlanFormComponent } from '../arch-elaboration-plan-form/arch-elaboration-plan-form.component';

@Component({
  selector: 'app-preview-plan',
  standalone: true,
  imports: [NgIf, JsonPipe, ButtonModule],
  templateUrl: './preview-plan.component.html',
  styleUrl: './preview-plan.component.css',
})
export class PreviewPlanComponent implements OnInit, OnDestroy {
  archPlan: Entity | null = null;
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
