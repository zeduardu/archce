import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterStakeholderComponent } from './register-stakeholder/register-stakeholder.component';

const routes: Routes = [
  { path: 'stakeholder', component: RegisterStakeholderComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
