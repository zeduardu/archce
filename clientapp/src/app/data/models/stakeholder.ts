import { EntityInterest } from "./entity-interest";
import { StakeholderType } from "./stakeholder-type";

export interface Stakeholder {
  name: string;
  type: StakeholderType;
  entities: EntityInterest[];
}
