import {Viewpoint} from "@models/viewpoint";

export interface View {
  name: string;
  diagram: string;
  rationale: string;

  viewpoint: Viewpoint;
}
