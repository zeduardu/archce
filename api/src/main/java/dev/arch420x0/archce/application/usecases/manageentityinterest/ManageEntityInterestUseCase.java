package dev.arch420x0.archce.application.usecases.manageentityinterest;

import dev.arch420x0.archce.application.dtos.EntityInterestResponse;
import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.application.usecases.manageentityinterest.commands.AddEntityInterestCommand;
import dev.arch420x0.archce.application.usecases.manageentityinterest.commands.BrowseAllEntitiesInterestCommand;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.AddEntityInterestReq;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.BrowseAllEntitiesInterestReq;
import dev.arch420x0.archce.infrastructure.shortbus.RequestHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageEntityInterestUseCase implements RequestHandler<RequestModel, Object> {
  private final EntityInterestRepository entityInterestRepository;
  private final BrowseAllEntitiesInterestCommand browseAllEntitiesInterestCommand;
  private final AddEntityInterestCommand addEntityInterestCommand;
  private final ModelMapper modelMapper;

  public ManageEntityInterestUseCase(
    EntityInterestRepository entityInterestRepository,
    BrowseAllEntitiesInterestCommand browseAllEntitiesInterestCommand,
    AddEntityInterestCommand addEntityInterestCommand
  ) {
    this.entityInterestRepository = entityInterestRepository;
    this.browseAllEntitiesInterestCommand = browseAllEntitiesInterestCommand;
    this.addEntityInterestCommand = addEntityInterestCommand;
    this.modelMapper = new ModelMapper();
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

  public EntityInterestResponse readEntityOfInterestById(Long id) {
    Optional<EntityInterestResponse> response = this.entityInterestRepository.findById(id).map((element) -> modelMapper.map(element, EntityInterestResponse.class));
    return response.orElse(null);
  }
}
