import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { ElaborationComponent } from './modules/elaboration/elaboration.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'elaboration', component: ElaborationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
