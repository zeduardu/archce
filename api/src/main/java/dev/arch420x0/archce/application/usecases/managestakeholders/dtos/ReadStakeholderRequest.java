package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.domain.entities.Stakeholder;
import dev.arch420x0.archce.infrastructure.shortbus.Request;

public record ReadStakeholderRequest(
  Long stakeholderId
) implements Request<ReadStakeholderResponse> {
}
