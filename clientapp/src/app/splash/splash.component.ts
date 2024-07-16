import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCopyright } from '@fortawesome/free-regular-svg-icons';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';

@Component({
  selector: 'app-splash',
  standalone: true,
  imports: [ButtonModule, RippleModule, FontAwesomeModule, RouterModule],
  templateUrl: './splash.component.html',
  styleUrl: './splash.component.css'
})
export class SplashComponent {
  faCopyright = faCopyright;
}
