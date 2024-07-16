package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
public record BrowseByEntityInterestIdObjectivesReq(Long entityInterestId) implements Serializable, RequestModel<Long> {
}