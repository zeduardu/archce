import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  standalone: true,
  imports: [CardModule, ButtonModule, RouterLink],
})
export class HomeComponent {
  openLink(link: String) {
    window.open(link.toString(), '_blank');
  }
}
