import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '@modules/home/home.component';
import { ManageConcernComponent } from '@modules/manage-concern/manage-concern.component';
import { ManageEntityInterestComponent } from '@modules/manage-entity-interest/manage-entity-interest.component';
import { ManageObjectiveComponent } from '@modules/manage-objective/manage-objective.component';
import { ManageViewpointComponent } from '@modules/manage-viewpoint/manage-viewpoint.component';
import { ContentLayoutComponent } from './layout/content-layout/content-layout.component';
import { ElaborationComponent } from '@modules/elaboration/elaboration.component';
import { SplashComponent } from './splash/splash.component';

const routes: Routes = [
  { path: '', component: SplashComponent },
  {
    path: 'arch',
    component: ContentLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'elaboration', component: ElaborationComponent },
      { path: 'manage-eoi', component: ManageEntityInterestComponent },
      { path: 'manage-obj', component: ManageObjectiveComponent },
      { path: 'manage-vpt', component: ManageViewpointComponent },
      { path: 'manage-cer', component: ManageConcernComponent },
      {
        path: 'register',
        loadChildren: () =>
          import('./modules/register/register.module').then(
            (m) => m.RegisterModule,
          ),
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
