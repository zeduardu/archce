import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ListboxModule } from 'primeng/listbox';
import { ToastModule } from 'primeng/toast';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterStakeholderComponent } from './register-stakeholder/register-stakeholder.component';

@NgModule({
  declarations: [RegisterStakeholderComponent],
  imports: [
    CommonModule,
    RegisterRoutingModule,
    FormsModule,
    InputTextModule,
    ListboxModule,
    ButtonModule,
    ToastModule
  ],
  providers: [
    MessageService
  ]
})
export class RegisterModule { }
