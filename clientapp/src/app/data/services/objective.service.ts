import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Objective } from '@models/objective';
import { Observable } from 'rxjs';
import { BaseService } from 'src/app/core/service/base.service';
import {AddObjectiveReq} from "@models/add-objective-req";
import { BrowseByEntityInterestIdObjectivesRes } from '@models/BrowseByEntityInterestIdObjectivesRes';

@Injectable({
  providedIn: 'root'
})
export class ObjectiveService extends BaseService {

  constructor(http: HttpClient) {
    super(http);
  }

  getObjectives(): Observable<Objective[]> {
    return this.get<Objective[]>('objectives');
  }

  getObjectivesByEntityInterestId(entityInterestId: number): Observable<BrowseByEntityInterestIdObjectivesRes[]> {
    return this.get<BrowseByEntityInterestIdObjectivesRes[]>(`objectives/entity-interest/${entityInterestId}`);
  }

  getObjective(id: number): Observable<Objective> {
    return this.get<Objective>(`objectives/${id}`);
  }

  createObjective(objective: AddObjectiveReq): Observable<Objective> {
    return this.post<Objective>('objectives', objective);
  }

  updateObjective(objective: Objective): Observable<Objective> {
    return this.put<Objective>(
      `objectives/${objective.id}`,
      objective,
    );
  }

  deleteObjective(id: number): Observable<any> {
    return this.delete(`objectives/${id}`);
  }
}
