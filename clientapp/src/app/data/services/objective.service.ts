import { Injectable } from '@angular/core';
import { Objective } from '@models/objective';

@Injectable({
  providedIn: 'root'
})
export class ObjectiveService {
  private objectives: Objective[] = [];

  constructor() { }

  //BREAD
  public browse(): Objective[] {
    return this.objectives;
  }

  public add(objective: Objective): void {
    this.objectives.push(objective);
  }

}
