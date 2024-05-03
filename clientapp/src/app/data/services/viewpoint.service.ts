import { Injectable } from '@angular/core';
import { Viewpoint } from '../models/viewpoint';

@Injectable({
  providedIn: 'root',
})
export class ViewpointService {
  public viewpoint: Viewpoint | null = null;
  private viewpoints: Viewpoint[] = [];

  constructor() {}
}
