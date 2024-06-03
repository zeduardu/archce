package dev.arch420x0.archce.application.usecase.registerentityinterest;

import java.util.Optional;

import dev.arch420x0.archce.application.models.RegisterEntityInterestReq;
import dev.arch420x0.archce.domain.entities.EntityInterest;

public interface RegisterEntityInterestUseCase {
  Optional<EntityInterest> execute(RegisterEntityInterestReq entityInterestModel);
}
