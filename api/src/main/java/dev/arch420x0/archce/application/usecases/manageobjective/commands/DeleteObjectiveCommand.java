package dev.arch420x0.archce.application.usecases.manageobjective.commands;

import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteObjectiveCommand {
  private final ObjectiveRepository objectiveRepository;

  public DeleteObjectiveCommand(ObjectiveRepository objectiveRepository) {
    this.objectiveRepository = objectiveRepository;
  }

  public void execute(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null");
    }

    this.objectiveRepository.deleteById(id);
  }
}
