import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ListboxModule } from 'primeng/listbox';
import { ToastModule } from 'primeng/toast';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterStakeholderComponent } from './register-stakeholder/register-stakeholder.component';
import { RippleModule } from 'primeng/ripple';
import {RegisterViewpointComponent} from "@modules/register/register-viewpoint/register-viewpoint.component";
import {FieldsetModule} from "primeng/fieldset";
import {DialogModule} from "primeng/dialog";
import {FileUploadModule} from "primeng/fileupload";

@NgModule({
  declarations: [RegisterStakeholderComponent, RegisterViewpointComponent],
  imports: [
    CommonModule,
    RegisterRoutingModule,
    FormsModule,
    InputTextModule,
    ListboxModule,
    ButtonModule,
    ToastModule,
    RippleModule,
    FieldsetModule,
    ReactiveFormsModule,
    DialogModule,
    FileUploadModule,
  ],
  providers: [MessageService],
  exports: [RegisterViewpointComponent],
})
export class RegisterModule {}
