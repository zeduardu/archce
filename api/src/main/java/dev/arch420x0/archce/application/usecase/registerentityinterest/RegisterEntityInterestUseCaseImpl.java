package dev.arch420x0.archce.application.usecase.registerentityinterest;

import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.models.RegisterEntityInterestReq;
import dev.arch420x0.archce.domain.entities.EntityInterest;

@Service
public class RegisterEntityInterestUseCaseImpl implements RegisterEntityInterestUseCase {
  private final EntityInterestRepository entityInterestRepository;

  public RegisterEntityInterestUseCaseImpl(EntityInterestRepository entityInterestRepository) {
    this.entityInterestRepository = entityInterestRepository;
  }

  @Override
  public Optional<EntityInterest> execute(RegisterEntityInterestReq request) {
    return Optional.of(
      this.entityInterestRepository.save(toEntity(request)));
  }

  /**
   * Convert model to entity
   * 
   * @param model
   * @return EntityInterest
   */
  private EntityInterest toEntity(RegisterEntityInterestReq model) {
    EntityInterest entity = new EntityInterest();
    entity.setName(model.getName());
    entity.setBackground(model.getBackground());
    entity.setPurpose(model.getPurpose());
    entity.setScope(model.getScope());
    return entity;

  }
}
