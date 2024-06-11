package dev.arch420x0.archce.application.usecases.managestakeholders.commands;

import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.DeleteStakeholderRequest;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import org.springframework.stereotype.Component;

@Component
public class DeleteStakeholderCommand {
  public Response execute(DeleteStakeholderRequest request) {
    return null;
  }
}
