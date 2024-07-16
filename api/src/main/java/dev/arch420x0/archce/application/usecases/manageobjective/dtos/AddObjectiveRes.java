package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
@Getter
@Setter
@AllArgsConstructor
public class AddObjectiveRes implements Serializable {
  Long id;
  String description;
  String rationale;

  public AddObjectiveRes() {}
}