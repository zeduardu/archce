package dev.arch420x0.archce.application.usecase.manageentityinterest;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.application.models.AddEntityInterestReq;
import dev.arch420x0.archce.application.models.BrowseAllEntitiesInterestReq;
import dev.arch420x0.archce.application.usecase.manageentityinterest.commands.AddEntityInterestCommand;
import dev.arch420x0.archce.application.usecase.manageentityinterest.commands.BrowseAllEntitiesInterestCommand;
import dev.arch420x0.archce.infrastructure.shortbus.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class ManageEntityInterestUseCase implements RequestHandler<RequestModel, Object> {
  private final BrowseAllEntitiesInterestCommand browseAllEntitiesInterestCommand;
  private final AddEntityInterestCommand addEntityInterestCommand;

  public ManageEntityInterestUseCase(BrowseAllEntitiesInterestCommand browseAllEntitiesInterestCommand, AddEntityInterestCommand addEntityInterestCommand) {
    this.browseAllEntitiesInterestCommand = browseAllEntitiesInterestCommand;
    this.addEntityInterestCommand = addEntityInterestCommand;
  }

  @Override
  public Object handle(RequestModel request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    if (request instanceof BrowseAllEntitiesInterestReq) {
      return this.browseAllEntitiesInterestCommand.execute(request);
    }
    if (request instanceof AddEntityInterestReq) {
      return this.addEntityInterestCommand.execute((AddEntityInterestReq) request);
    }
    return "";
  }
}
