package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
public record DeleteObjectiveReq(
  @NotNull Long id
) implements Serializable, RequestModel<Long> {}