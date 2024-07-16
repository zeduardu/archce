import { Injectable } from '@angular/core';
import { Viewpoint } from '@models/viewpoint';
import {EntityInterest} from "@models/entity-interest";
import {Observable} from "rxjs";
import {BaseService} from "../../core/service/base.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class ViewpointService extends BaseService {
  public viewpoint: Viewpoint | null = null;
  private viewpoints: Viewpoint[] = [];

  constructor(http: HttpClient) {
    super(http);
  }

  createViewpoint(viewpoint: Viewpoint): Observable<Viewpoint> {
    return this.post<Viewpoint>('viewpoints', viewpoint);
  }

  uploadFile(formData: FormData): void {
    this.post('viewpoints/upload', formData);
  }
}
