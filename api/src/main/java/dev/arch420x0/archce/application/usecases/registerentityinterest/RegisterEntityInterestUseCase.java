package dev.arch420x0.archce.application.usecases.registerentityinterest;

import java.util.Optional;

import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.RegisterEntityInterestReq;
import dev.arch420x0.archce.domain.entities.EntityInterest;

public interface RegisterEntityInterestUseCase {
  Optional<EntityInterest> execute(RegisterEntityInterestReq entityInterestModel);
}
