import { EntityInterest } from "./entity-interest";
import {Concern} from "@models/concern";

export interface Viewpoint {
  name: string;
  overview: string;
  concerns: Concern[];
  aspects: string;
  model: string;
  view: string;
  examples: string;
  problemPitfalls: string;
  applicability: string;
  views: string;
}
