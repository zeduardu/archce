package dev.arch420x0.archce.application.usecases.manageobjective.commands;

import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.AddObjectiveReq;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.AddObjectiveRes;
import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import dev.arch420x0.archce.persistence.repositories.ProblemRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddObjectiveCommand {
  private final ObjectiveRepository objectiveRepository;
  private final ProblemRepository problemRepository;
  private final EntityInterestRepository entityInterestRepository;

  public AddObjectiveCommand(ObjectiveRepository objectiveRepository, ProblemRepository problemRepository, EntityInterestRepository entityInterestRepository) {
    this.objectiveRepository = objectiveRepository;
    this.problemRepository = problemRepository;
    this.entityInterestRepository = entityInterestRepository;
  }

  public AddObjectiveRes execute(AddObjectiveReq request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    Objective createdObjective = this.objectiveRepository.save(
      new Objective(
        request.description(),
        request.rationale(),
        null,
        null,
        entityInterestRepository.findById(request.entityInterestId()).get()
      )
    );

    return new AddObjectiveRes(
      createdObjective.getId(),
      createdObjective.getDescription(),
      createdObjective.getRationale()
    );
  }
}
