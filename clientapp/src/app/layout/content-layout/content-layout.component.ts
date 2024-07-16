import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {MenubarModule} from "primeng/menubar";
import {NavComponent} from "../nav/nav.component";

@Component({
  selector: 'app-content-layout',
  standalone: true,
  imports: [RouterOutlet, NavComponent],
  templateUrl: './content-layout.component.html',
  styleUrl: './content-layout.component.css',
})
export class ContentLayoutComponent {}
