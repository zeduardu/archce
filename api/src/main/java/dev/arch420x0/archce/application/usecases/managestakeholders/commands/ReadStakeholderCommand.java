package dev.arch420x0.archce.application.usecases.managestakeholders.commands;

import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.ReadStakeholderRequest;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import org.springframework.stereotype.Component;

@Component
public class ReadStakeholderCommand {
  public Response execute(ReadStakeholderRequest request) {
    return null;
  }
}
