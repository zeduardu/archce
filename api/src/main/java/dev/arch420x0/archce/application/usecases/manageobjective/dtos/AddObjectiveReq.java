package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.domain.entities.Problem;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
public record AddObjectiveReq(
  String description,
  String rationale,
  Long entityInterestId
) implements Serializable, RequestModel<Long> {
}