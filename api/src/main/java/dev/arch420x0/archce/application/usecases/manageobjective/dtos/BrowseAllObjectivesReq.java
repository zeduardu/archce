package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.domain.entities.Objective;

import java.util.List;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
public record BrowseAllObjectivesReq() implements RequestModel<List<Objective>> {}
