import { CurrencyPipe, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowseAllEntitiesInterestRes } from '@models/BrowseAllEntitiesInterestRes';
import { EntityInterest } from '@models/entity-interest';
import { EntityInterestService } from '@services/entity-interest.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { EditorModule } from 'primeng/editor';
import { FileUploadModule } from 'primeng/fileupload';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { RadioButtonModule } from 'primeng/radiobutton';
import { RatingModule } from 'primeng/rating';
import { RippleModule } from 'primeng/ripple';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'app-manage-entity-interest',
  standalone: true,
  imports: [
    ToastModule,
    ToolbarModule,
    ButtonModule,
    FileUploadModule,
    TableModule,
    RatingModule,
    TagModule,
    DialogModule,
    DropdownModule,
    RadioButtonModule,
    InputNumberModule,
    ConfirmDialogModule,
    FormsModule,
    CurrencyPipe,
    InputTextModule,
    NgIf,
    InputTextareaModule,
    RippleModule,
    EditorModule,
    TooltipModule,
  ],
  providers: [MessageService, ConfirmationService, EntityInterestService],
  templateUrl: './manage-entity-interest.component.html',
  styleUrl: './manage-entity-interest.component.css',
})
export class ManageEntityInterestComponent {
  eoiDialog: boolean = false;

  entitiesInterest!: BrowseAllEntitiesInterestRes[];

  entityInterest!: EntityInterest;

  selectedEsoI!: BrowseAllEntitiesInterestRes[] | null;

  submitted: boolean = false;

  statuses!: any[];

  constructor(
    private entityInterestService: EntityInterestService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
  ) {}

  ngOnInit() {
    this.entityInterestService
      .getEntitiesInterest()
      .subscribe((data) => (this.entitiesInterest = data));
  }

  addNewEntityInterest(): void {
    this.entityInterest = {
      id: 0,
      name: '',
      background: '',
      purpose: '',
      scope: '',
      approach: '',
      schedule: '',
      milestones: '',
    };
    this.submitted = false;
    this.eoiDialog = true;
  }

  deleteSelectedEntities() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entitiesInterest = this.entitiesInterest.filter(
          (val) => !this.selectedEsoI?.includes(val),
        );
        this.selectedEsoI = null;
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Products Deleted',
          life: 3000,
        });
      },
    });
  }

  editProduct(entityInterest: EntityInterest) {
    this.entityInterest = { ...entityInterest };
    this.eoiDialog = true;
  }

  deleteProduct(entityInterest: EntityInterest) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + entityInterest.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.entitiesInterest = this.entitiesInterest.filter(
          (val) => val.id !== entityInterest.id,
        );
        this.entityInterest = {
          id: 0,
          name: '',
          background: '',
          purpose: '',
          scope: '',
          approach: '',
          schedule: '',
          milestones: '',
        };
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Product Deleted',
          life: 3000,
        });
      },
    });
  }

  hideDialog() {
    this.eoiDialog = false;
    this.submitted = false;
  }

  addEntityInterest() {
    this.submitted = true;

    if (this.entityInterest.name?.trim()) {
      if (this.entityInterest.id) {
        this.entitiesInterest[
          this.findIndexById(this.entityInterest.id.toString())
        ] = this.entityInterest;
        this.messageService.add({
          severity: 'success',
          summary: 'Successful',
          detail: 'Entity of Interest Updated',
          life: 3000,
        });
      } else {
        this.entityInterestService
          .createEntityInterest(this.entityInterest)
          .subscribe({
            next: (data) => {
              this.entitiesInterest.push(data);
              this.messageService.add({
                severity: 'success',
                summary: 'Successful',
                detail: 'Product Created',
                life: 3000,
              });
            },
            error: (error) => {
              console.error(error);
            },
          });
      }

      this.entitiesInterest = [...this.entitiesInterest];
      this.eoiDialog = false;
      this.entityInterest = {
        id: 0,
        name: '',
        background: '',
        purpose: '',
        scope: '',
        approach: '',
        schedule: '',
        milestones: '',
      };
    }
  }

  findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.entitiesInterest.length; i++) {
      if (this.entitiesInterest[i].id.toString() === id) {
        index = i;
        break;
      }
    }

    return index;
  }

  createId(): string {
    let id = '';
    var chars =
      'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for (var i = 0; i < 5; i++) {
      id += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return id;
  }

  getSeverity(status?: string) {
    switch (status) {
      case 'INSTOCK':
        return 'success';
      case 'LOWSTOCK':
        return 'warning';
      case 'OUTOFSTOCK':
        return 'danger';
      default:
        return '';
    }
  }

  protected readonly HTMLInputElement = HTMLInputElement;
}
