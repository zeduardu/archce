package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.domain.entities.Problem;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
public record EditObjectiveReq(
  Long id,
  String description,
  String rationale,
  Problem problem,
  EntityInterest entityInterest
) implements Serializable, RequestModel<Long> {}