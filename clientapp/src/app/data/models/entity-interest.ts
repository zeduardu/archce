import { Stakeholder } from "./stakeholder";

export interface EntityInterest {
  id: number
  name: string;
  background: string;
  purpose: string;
  scope: string;
  approach: string;
  schedule: string;
  milestones: string;

  stakeholders: Stakeholder[];
}
