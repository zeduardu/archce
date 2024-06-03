package dev.arch420x0.archce.application.models;
import java.util.List;

import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrowseAllEntitiesInterestRes extends Response<List<BrowseAllEntitiesInterestRes>> {
  private String name;
  private String background;
  private String purpose;
  private String scope;
  private String approach;
  private String schedule;
  private String milestones;
}
