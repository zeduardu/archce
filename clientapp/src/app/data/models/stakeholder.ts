import { Entity } from "./entity";
import { StakeholderType } from "./stakeholder-type";

export interface Stakeholder {
  name: string;
  type: StakeholderType;
  entities: Entity[];
}
