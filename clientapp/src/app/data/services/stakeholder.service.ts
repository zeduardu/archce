import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { Stakeholder } from "@models/stakeholder";
import { Observable } from "rxjs";
import { BaseService } from "../../core/service/base.service";

@Injectable({
  providedIn: 'root'
})
export class StakeholderService extends BaseService{

  constructor(http: HttpClient) {
    super(http);
  }

  public getStakeholdersByEntityInterestId(entityInterestId: number): Observable<Stakeholder[]> {
    return this.get<Stakeholder[]>(`stakeholders/entity-interest/${entityInterestId}`);
  }

  public addStakeholder(stakeholder: Stakeholder): Observable<Stakeholder> {
    const newStakeholder = {
      nome: stakeholder.name,
      type: stakeholder.type,
      entityInterestId: stakeholder.entityInterest!.id
    }
    return this.post<Stakeholder>('stakeholders', newStakeholder);
  }
}
