package dev.arch420x0.archce.application.usecases.managestakeholders.commands;

import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.AddStakeholderRequest;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.AddStakeholderResponse;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.domain.entities.Stakeholder;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import org.springframework.stereotype.Component;

@Component
public class AddStakeholderCommand {
  private final StakeholderRepository stakeholderRepository;
  private final EntityInterestRepository entityInterestRepository;

  public AddStakeholderCommand(StakeholderRepository stakeholderRepository, EntityInterestRepository entityInterestRepository) {
    this.stakeholderRepository = stakeholderRepository;
    this.entityInterestRepository = entityInterestRepository;
  }

  public AddStakeholderResponse execute(AddStakeholderRequest request) {
    EntityInterest entityInterest = entityInterestRepository.findById(request.entityInterestId()).get();
    Stakeholder newStakeholder = new Stakeholder(
      request.nome(),
      request.type(),
      null,
      null,
      entityInterest
    );
    Stakeholder createdStakeholder = stakeholderRepository.save(newStakeholder);
    return new AddStakeholderResponse(
      createdStakeholder.getId(),
      createdStakeholder.getNome(),
      createdStakeholder.getType()
      );
  }
}
