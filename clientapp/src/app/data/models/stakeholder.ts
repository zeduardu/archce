import { EntityInterest } from "./entity-interest";
import { StakeholderType } from "./stakeholder-type";

export interface Stakeholder {
  id: number;
  name: string;
  type: string;
  entityInterest: EntityInterest | null;
}
