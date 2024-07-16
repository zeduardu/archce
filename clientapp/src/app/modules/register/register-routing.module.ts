import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterStakeholderComponent } from './register-stakeholder/register-stakeholder.component';
import { RegisterViewpointComponent } from '@modules/register/register-viewpoint/register-viewpoint.component';

const routes: Routes = [
  { path: 'stakeholder', component: RegisterStakeholderComponent },
  { path: 'viewpoint', component: RegisterViewpointComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegisterRoutingModule { }
