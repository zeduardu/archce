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
      entityInterestId: stakeholder.entityInterestId
    }
    return this.post<Stakeholder>('stakeholders', newStakeholder);
  }

  public editStakeholder(stakeholder: Stakeholder): Observable<Stakeholder> {
    return this.put<Stakeholder>(`stakeholders/${stakeholder.id}`, stakeholder);
  }

  browseAllStakeholderTypes() {
    return this.get<string[]>('stakeholders/types');
  }
}
