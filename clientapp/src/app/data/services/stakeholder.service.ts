import { Injectable } from '@angular/core';
import {BaseService} from "../../core/service/base.service";
import {HttpClient} from "@angular/common/http";
import {Stakeholder} from "@models/stakeholder";
import {BrowseByEntityInterestIdObjectivesRes} from "@models/BrowseByEntityInterestIdObjectivesRes";
import {Observable} from "rxjs";

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
}
