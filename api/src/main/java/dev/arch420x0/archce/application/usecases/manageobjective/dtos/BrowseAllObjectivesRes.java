package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
@Getter
@Setter
public class BrowseAllObjectivesRes implements Serializable {
  private Long id;
  private String description;
  private String rationale;
  private EntityInterest entityInterest;
}