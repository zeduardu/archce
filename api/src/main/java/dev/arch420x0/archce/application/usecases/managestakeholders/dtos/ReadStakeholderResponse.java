package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReadStakeholderResponse extends Response<ReadStakeholderRequest> {
  private Long id;
  private String name;
  private String type;
  private EntityInterest entityInterest;
}
