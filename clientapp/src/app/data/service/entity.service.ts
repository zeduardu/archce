import { Injectable } from '@angular/core';
import { Entity } from '../types/entity';

@Injectable({
  providedIn: 'root',
})
export class EntityService {
  public entity: Entity | null = null;
  private entities: Entity[] = [];

  constructor() {}

  //BREAD
  browse(): Entity[] {
    return this.entities;
  }

  add(entity: Entity): number {
    return this.entities.push(entity)
  }
}
