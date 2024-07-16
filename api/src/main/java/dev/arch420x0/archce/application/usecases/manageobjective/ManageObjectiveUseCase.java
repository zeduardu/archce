package dev.arch420x0.archce.application.usecases.manageobjective;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.application.usecases.manageobjective.commands.*;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.*;
import dev.arch420x0.archce.infrastructure.shortbus.RequestHandler;
import org.springframework.stereotype.Service;

@Service
public class ManageObjectiveUseCase implements RequestHandler<RequestModel, Object> {
  private final BrowseAllObjectivesCommand browseAllObjectivesCommand;
  private final AddObjectiveCommand addObjectiveCommand;
  private final EditObjectiveCommand editObjectiveCommand;
  private final DeleteObjectiveCommand deleteObjectiveCommand;
  private final BrowseByEntityInterestIdObjectiveCommand browseByEntityInterestIdObjectiveCommand;

  public ManageObjectiveUseCase(
    BrowseAllObjectivesCommand browseAllObjectivesCommand,
    AddObjectiveCommand addObjectiveCommand,
    EditObjectiveCommand editObjectiveCommand,
    DeleteObjectiveCommand deleteObjectiveCommand, BrowseByEntityInterestIdObjectiveCommand browseByEntityInterestIdObjectiveCommand) {
    this.browseAllObjectivesCommand = browseAllObjectivesCommand;
    this.addObjectiveCommand = addObjectiveCommand;
    this.editObjectiveCommand = editObjectiveCommand;
    this.deleteObjectiveCommand = deleteObjectiveCommand;
    this.browseByEntityInterestIdObjectiveCommand = browseByEntityInterestIdObjectiveCommand;
  }

  @Override
  public Object handle(RequestModel request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    if (request instanceof BrowseAllObjectivesReq) {
      return this.browseAllObjectivesCommand.execute();
    }
    if (request instanceof BrowseByEntityInterestIdObjectivesReq) {
      return this.browseByEntityInterestIdObjectiveCommand.execute((BrowseByEntityInterestIdObjectivesReq) request);
    }
    if (request instanceof AddObjectiveReq) {
      return this.addObjectiveCommand.execute((AddObjectiveReq) request);
    }
    if (request instanceof EditObjectiveReq) {
      return this.editObjectiveCommand.execute((EditObjectiveReq) request);
    }
    if (request instanceof DeleteObjectiveReq) {
      this.deleteObjectiveCommand.execute(((DeleteObjectiveReq) request).id());
    }
    return "";
  }
}
