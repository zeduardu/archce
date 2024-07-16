import { Injectable } from '@angular/core';
import {BaseService} from "../../core/service/base.service";
import {HttpClient} from "@angular/common/http";
import {View} from "@models/View";

@Injectable({
  providedIn: 'root'
})
export class ViewService extends BaseService {

  constructor(http: HttpClient) {
    super(http);
  }

  browseAllViewsByENityInterest(entityInterestId: number): View[] {
    return [
      {
        name: 'Information view',
        diagram: 'diagram1',
        rationale: 'rationale1',
        viewpoint: {
          id: 1,
          name: 'Information viewpoint',
          overview: 'overview1',
          concerns: [],
          model: 'model1',
          conventions: 'conventions1',
          rationale: 'rationale1',
          sources: 'sources1'
        }
      },
      {
        name: 'Business process view',
        diagram: 'diagram2',
        rationale: 'rationale2',
        viewpoint: {
          id: 2,
          name: 'Concurrency viewpoint',
          overview: 'overview2',
          concerns: [],
          model: 'model2',
          conventions: 'conventions2',
          rationale: 'rationale2',
          sources: 'sources2'
        },
      },
      {
        name: 'Development view',
        diagram: 'diagram3',
        rationale: 'rationale3',
        viewpoint: {
          id: 3,
          name: 'Development viewpoint',
          overview: 'overview3',
          concerns: [],
          model: 'model3',
          conventions: 'conventions3',
          rationale: 'rationale3',
          sources: 'sources3'
        }
      }
    ];
  }
}
