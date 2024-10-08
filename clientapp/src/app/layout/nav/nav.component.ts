import {
  Component,
  OnInit
} from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { RegisterEntityInterestComponent } from '../../shared/component/register-entity-interest/register-entity-interest.component';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [MenubarModule, RegisterEntityInterestComponent],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css',
})
export class NavComponent implements OnInit {
  items: MenuItem[] | undefined;
  showDialog: boolean = false;

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-fw pi-home',
        routerLink: ['/arch'],
      },
      {
        label: 'Manage',
        items: [
          {
            label: 'Entity of Interest',
            routerLink: '/arch/manage-eoi',
          },
          {
            label: 'Objective(s) of EoI',
            routerLink: '/arch/manage-obj',
          },
          {
            label: 'Architecture Viewpoints',
            routerLink: '/arch/manage-vpt',
          }
        ],
      },
      {
        label: 'Quick actions',
        icon: 'pi pi-fw pi-bolt',
        items: [
          {
            label: 'Register entity of interest',
            icon: 'pi pi-fw pi-angle-right',
            command: () => {
              this.showDialog = true;
            },
          },
          {
            label: 'Register stakeholder',
            icon: 'pi pi-users',
            routerLink: '/arch/register/stakeholder',
          },
          {
            label: 'Register viewpoint',
            icon: 'pi pi-map',
            routerLink: '/arch/register/viewpoint',
          },
          {
            label: 'Register view',
            icon: 'pi pi-eye',
            routerLink: '/arch/register/viewpoint',
          }
        ],
      },
    ];
  }

  onSave() {
    // Perform save operation specific to this component
    this.showDialog = false;
  }

  onCancel() {
    // Reset dialog properties or perform necessary actions
    this.showDialog = false;
  }
}
