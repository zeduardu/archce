import { EntityInterest } from "./entity-interest";
import {Concern} from "@models/concern";

export interface Viewpoint {
  id: number;
  name: string;
  overview: string;
  concerns: Concern[];
  model: string;
  conventions: string;
  rationale: string;
  sources: string;
}
