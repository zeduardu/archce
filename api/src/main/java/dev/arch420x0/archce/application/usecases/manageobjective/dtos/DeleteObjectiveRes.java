package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.domain.entities.Problem;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class DeleteObjectiveRes extends Response<DeleteObjectiveRes> implements Serializable {
  Long id;
  String description;
  String rationale;
  Problem problem;
  EntityInterest entityInterest;
}