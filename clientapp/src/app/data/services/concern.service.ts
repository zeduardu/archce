import { Injectable } from '@angular/core';
import {Concern} from "@models/concern";

@Injectable({
  providedIn: 'root'
})
export class ConcernService {
  concerns!: Concern[];

  constructor() {
  }
}
