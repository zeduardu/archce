package dev.arch420x0.archce.application.models;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import lombok.Data;

@Data
public class AddEntityInterestReq implements RequestModel<Long> {

  private String name;
  private String background;
  private String purpose;
  private String scope;
  private String approach;
  private String schedule;
  private String milestones;
}
