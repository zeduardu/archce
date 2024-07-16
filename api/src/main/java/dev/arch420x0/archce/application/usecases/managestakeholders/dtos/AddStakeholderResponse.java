package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddStakeholderResponse {
  private Long id;
  private String name;
  private String type;
}
