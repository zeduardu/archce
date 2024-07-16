package dev.arch420x0.archce.application.usecases.manageobjective.commands;

import dev.arch420x0.archce.application.usecases.manageobjective.dtos.BrowseAllObjectivesRes;
import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrowseAllObjectivesCommand {
  private final ObjectiveRepository objectiveRepository;

  public BrowseAllObjectivesCommand(ObjectiveRepository objectiveRepository) {
    this.objectiveRepository = objectiveRepository;
  }

  public List<BrowseAllObjectivesRes> execute() {
    List<Objective> objectives = objectiveRepository.findAll();
    return objectives.stream().map(
      objective -> {
        BrowseAllObjectivesRes res = new BrowseAllObjectivesRes();
        res.setId(objective.getId());
        res.setDescription(objective.getDescription());
        res.setRationale(objective.getRationale());
        res.setEntityInterest(objective.getEntityInterest());
        return res;
      }
    ).toList();
  }
}
