package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.infrastructure.shortbus.Request;

public record EditStakeholderRequest(Long id) implements Request<Long> {
}
