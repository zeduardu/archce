package dev.arch420x0.archce.application.usecases.manageobjective.commands;

import dev.arch420x0.archce.application.exceptions.RecordNotFoundException;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.BrowseAllObjectivesRes;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.BrowseByEntityInterestIdObjectivesReq;
import dev.arch420x0.archce.application.usecases.manageobjective.dtos.BrowseByEntityInterestIdObjectivesRes;
import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrowseByEntityInterestIdObjectiveCommand {
  private final ObjectiveRepository objectiveRepository;

  public BrowseByEntityInterestIdObjectiveCommand(ObjectiveRepository objectiveRepository) {
    this.objectiveRepository = objectiveRepository;
  }

  /**
   * Browse objectives by entity interest id
   *
   * @param request {@link BrowseByEntityInterestIdObjectivesReq}
   * @return {@link List<BrowseByEntityInterestIdObjectivesRes>}
   */
  public Object execute(BrowseByEntityInterestIdObjectivesReq request) {
    List<Objective> findedObjectives = objectiveRepository.findByEntityInterestId(request.entityInterestId());
    if (findedObjectives.isEmpty()) {
      return null;
    }
    return findedObjectives.stream().map(objective -> {
      BrowseByEntityInterestIdObjectivesRes response = new BrowseByEntityInterestIdObjectivesRes();
      response.setId(objective.getId());
      response.setDescription(objective.getDescription());
      response.setRationale(objective.getRationale());
      return response;
    }).toList();
  }
}
