import { Objective } from "@models/objective";

export interface EntityInterest {
  id: number
  name: string;
  background: string;
  purpose: string;
  scope: string;
  objectives: Objective[];
  approach: string;
  resourcesandschedule: string;
  riskandmitigation: string;
}
