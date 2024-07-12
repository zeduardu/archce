package dev.arch420x0.archce.application.usecases.managestakeholders;

import dev.arch420x0.archce.application.common.GenericMapper;
import dev.arch420x0.archce.application.dtos.StakeholderRequest;
import dev.arch420x0.archce.application.dtos.StakeholderResponse;
import dev.arch420x0.archce.application.usecases.managestakeholders.commands.*;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.*;
import dev.arch420x0.archce.domain.entities.Stakeholder;
import dev.arch420x0.archce.domain.enums.StakeholderType;
import dev.arch420x0.archce.infrastructure.shortbus.Request;
import dev.arch420x0.archce.infrastructure.shortbus.RequestHandler;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class ManageStakeholderUseCase implements RequestHandler<Request, Object> {
  private final EditStakeholderCommand editStakeholderCommand;
  private final AddStakeholderCommand addStakeholderCommand;
  private final DeleteStakeholderCommand deleteStakeholderCommand;

  private final BrowseStakeholdersByEntityInterestIdCommand browseStakeholdersByEntityInterestIdCommand;
  private final ReadStakeholderCommand browseByIdStakeholderCommand;

  private final StakeholderRepository stakeholderRepository;
  private final GenericMapper genericMapper;

  public ManageStakeholderUseCase(
    EditStakeholderCommand editStakeholderCommand,
    AddStakeholderCommand addStakeholderCommand,
    DeleteStakeholderCommand deleteStakeholderCommand,
    BrowseStakeholdersByEntityInterestIdCommand browseStakeholdersByEntityInterestIdCommand,
    ReadStakeholderCommand browseByIdStakeholderCommand,
    StakeholderRepository stakeholderRepository,
    GenericMapper genericMapper) {
    this.editStakeholderCommand = editStakeholderCommand;
    this.addStakeholderCommand = addStakeholderCommand;
    this.deleteStakeholderCommand = deleteStakeholderCommand;
    this.browseStakeholdersByEntityInterestIdCommand = browseStakeholdersByEntityInterestIdCommand;
    this.browseByIdStakeholderCommand = browseByIdStakeholderCommand;
    this.stakeholderRepository = stakeholderRepository;
    this.genericMapper = genericMapper;
  }

  @Override
  public Object handle(Request request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    if (request instanceof EditStakeholderRequest) {
      return editStakeholderCommand.execute((EditStakeholderRequest) request);
    } else if (request instanceof AddStakeholderRequest) {
      return addStakeholderCommand.execute((AddStakeholderRequest) request);
    } else if (request instanceof DeleteStakeholderRequest) {
      return deleteStakeholderCommand.execute((DeleteStakeholderRequest) request);
    } else if (request instanceof BrowseStakeholdersByEntityInterestIdRequest) {
      return browseStakeholdersByEntityInterestIdCommand.execute((BrowseStakeholdersByEntityInterestIdRequest) request);
    } else if (request instanceof ReadStakeholderRequest) {
      return browseByIdStakeholderCommand.execute((ReadStakeholderRequest) request);
    } else {
      throw new IllegalArgumentException("Request not supported");
    }
  }

  public List<StakeholderResponse> browseAllStakeholders() {
    return stakeholderRepository.findAll().stream().map(
      stakeholder -> genericMapper.toDto(stakeholder, StakeholderResponse.class)
    ).toList();
  }

  // List stakeholder types trough the StakeholdetType Enum class
  public List<String> browseAllStakeholderTypes() {
    return Stream.of(StakeholderType.values()).map(Enum::toString).toList();
  }

  public StakeholderResponse editStakeholer(StakeholderRequest request) {
    Stakeholder editedStakeholder = genericMapper.toEntity(request, Stakeholder.class);
    Stakeholder updatedStakeholder = stakeholderRepository.save(editedStakeholder);
    return genericMapper.toDto(updatedStakeholder, StakeholderResponse.class);
  }
}
