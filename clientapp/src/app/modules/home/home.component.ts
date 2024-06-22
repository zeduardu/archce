import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [
    CardModule,
    ButtonModule,
  ]
})
export class HomeComponent {
  openLink(link: String) {
    window.open(link.toString(), '_blank')
  }
}
