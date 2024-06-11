package dev.arch420x0.archce.application.usecases.manageviewpoint.commands;

import dev.arch420x0.archce.application.usecases.manageviewpoint.dtos.ViewpointRequest;
import dev.arch420x0.archce.domain.entities.Viewpoint;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import dev.arch420x0.archce.persistence.repositories.ViewpointRepository;
import org.springframework.stereotype.Component;


@Component
public class AddViewpointCommand {
  private final ViewpointRepository viewpointRepository;

  public AddViewpointCommand(ViewpointRepository viewpointRepository) {
    this.viewpointRepository = viewpointRepository;
  }

  public Response execute(ViewpointRequest request) {
    Viewpoint newViewpoint = new Viewpoint();
    return null;
  }
}
