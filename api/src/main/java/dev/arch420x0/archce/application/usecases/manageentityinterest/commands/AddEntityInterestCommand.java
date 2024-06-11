package dev.arch420x0.archce.application.usecases.manageentityinterest.commands;

import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.AddEntityInterestReq;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * AddEntityInterestCommand
 * <p> Command to add an entity interest to the repository </p>
 */
@Component
public class AddEntityInterestCommand {
  private final EntityInterestRepository entityInterestRepository;

  public AddEntityInterestCommand(EntityInterestRepository entityInterestRepository) {
    this.entityInterestRepository = entityInterestRepository;
  }

  public Optional<EntityInterest> execute(AddEntityInterestReq request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    EntityInterest newEntity = new EntityInterest();
    newEntity.setName(request.getName());
    newEntity.setBackground(request.getBackground());
    newEntity.setPurpose(request.getPurpose());
    newEntity.setScope(request.getScope());
    newEntity.setApproach(request.getApproach());
    newEntity.setSchedule(request.getSchedule());
    newEntity.setMilestones(request.getMilestones());

    return Optional.of(
      this.entityInterestRepository.save(newEntity)
    );
  }
}
