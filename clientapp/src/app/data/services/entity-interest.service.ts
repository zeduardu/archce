import { Injectable } from '@angular/core';
import { EntityInterest } from '@models/entity-interest';
import { BaseService } from 'src/app/core/service/base.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BrowseAllEntitiesInterestRes } from '@models/BrowseAllEntitiesInterestRes';

@Injectable({
  providedIn: 'root',
})
export class EntityInterestService extends BaseService {

  constructor(http: HttpClient) {
    super(http);
  }

  getEntitiesInterest(): Observable<BrowseAllEntitiesInterestRes[]> {
    return this.get<BrowseAllEntitiesInterestRes[]>('entities-interest');
  }

  getEntityInterest(id: number): Observable<EntityInterest> {
    return this.get<EntityInterest>(`entities-interest/${id}`);
  }

  createEntityInterest(entityInterest: EntityInterest): Observable<EntityInterest> {
    return this.post<EntityInterest>('entities-interest', entityInterest);
  }

  updateEntityInterest(entityInterest: EntityInterest): Observable<EntityInterest> {
    return this.put<EntityInterest>(
      `entities-interest/${entityInterest.id}`,
      entityInterest,
    );
  }

  deleteEntityInterest(id: number): Observable<any> {
    return this.delete(`entities-interest/${id}`);
  }
}
