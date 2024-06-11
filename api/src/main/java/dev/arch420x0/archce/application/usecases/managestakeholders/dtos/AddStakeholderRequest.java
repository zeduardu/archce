package dev.arch420x0.archce.application.usecases.managestakeholders.dtos;

import dev.arch420x0.archce.infrastructure.shortbus.Request;

public record AddStakeholderRequest(
  String nome,
  String type,
  Long entityInterestId
) implements Request<Long> {
}
