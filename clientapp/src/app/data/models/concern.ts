import {Viewpoint} from "@models/viewpoint";
import {Stakeholder} from "@models/stakeholder";

export interface Concern {
  id: number;
  matter: string;
  stakeholders: Stakeholder[];
}
