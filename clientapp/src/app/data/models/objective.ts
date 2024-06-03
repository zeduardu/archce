import { EntityInterest } from "./entity-interest";

export interface Objective {
  id: number;
  description: string;
  rationale: string;
  entity: EntityInterest | null;
}
