import {Viewpoint} from "@models/viewpoint";
import {Stakeholder} from "@models/stakeholder";

export interface Concern {
  id: number;
  matter: string;
  viewpoints: Viewpoint[];
  stakeholders: Stakeholder[];
}
