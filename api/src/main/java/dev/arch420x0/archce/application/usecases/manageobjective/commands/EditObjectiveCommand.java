package dev.arch420x0.archce.application.usecases.manageobjective.commands;

import dev.arch420x0.archce.application.usecases.manageobjective.dtos.EditObjectiveReq;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.EditObjectiveRes;
import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EditObjectiveCommand {
  private final ObjectiveRepository objectiveRepository;

  public EditObjectiveCommand(ObjectiveRepository objectiveRepository) {
    this.objectiveRepository = objectiveRepository;
  }

  public Optional<EditObjectiveRes> execute(EditObjectiveReq request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null");
    }

    Objective findedObjective = this.objectiveRepository.findById(request.id()).orElseThrow(() -> new IllegalArgumentException("Objective not found"));
    findedObjective.setDescription(request.description());
    findedObjective.setRationale(request.rationale());
    findedObjective.setEntityInterest(request.entityInterest());
    findedObjective.setProblem(request.problem());

    Objective updatedObjective = this.objectiveRepository.save(findedObjective);

    return Optional.of(
      new EditObjectiveRes(
        updatedObjective.getId(),
        updatedObjective.getDescription(),
        updatedObjective.getRationale()
      )
    );
  }

}
