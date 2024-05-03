import { Entity } from "./entity";

export interface Viewpoint {
  entity: Entity;
  title: string;
  overview: string;
  concerns: string;
  stakeholders: string;
  stakeholdersPerspectives: string;
  problemPitfalls: string;
  applicability: string;
  views: string;
  models: string;
}
