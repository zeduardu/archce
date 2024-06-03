import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ElaborationComponent } from './modules/elaboration/elaboration.component';
import { ContentLayoutComponent } from './layout/content-layout/content-layout.component';
import { HomeComponent } from '@modules/home/home.component';

const routes: Routes = [
  {
    path: '',
    component: ContentLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'elaboration', component: ElaborationComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
