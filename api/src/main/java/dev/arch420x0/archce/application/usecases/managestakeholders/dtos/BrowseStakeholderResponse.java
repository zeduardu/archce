package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BrowseStakeholderResponse extends Response<List<BrowseStakeholderResponse>> {
  private Long id;
  private String name;
  private String type;
  private EntityInterest entityInterest;
}
