package dev.arch420x0.archce.application.usecases.manageentityinterest.dtos;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseAllEntitiesInterestRes implements Serializable {
  private Long id;
  private String name;
  private String background;
  private String purpose;
  private String scope;
  private String approach;
  private String schedule;
  private String milestones;
}
