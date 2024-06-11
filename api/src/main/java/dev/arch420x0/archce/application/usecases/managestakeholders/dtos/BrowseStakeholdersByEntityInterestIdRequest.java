package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.infrastructure.shortbus.Request;

public record BrowseStakeholdersByEntityInterestIdRequest(Long entityInterestId) implements Request<BrowseStakeholdersByEntityInterestIdResponse> {
}
