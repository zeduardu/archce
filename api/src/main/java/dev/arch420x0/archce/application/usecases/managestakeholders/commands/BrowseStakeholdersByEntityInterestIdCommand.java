package dev.arch420x0.archce.application.usecases.managestakeholders.commands;

import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.BrowseStakeholdersByEntityInterestIdRequest;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.BrowseStakeholdersByEntityInterestIdResponse;
import dev.arch420x0.archce.domain.entities.Stakeholder;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrowseStakeholdersByEntityInterestIdCommand {
  private final StakeholderRepository stakeholderRepository;

  public BrowseStakeholdersByEntityInterestIdCommand(StakeholderRepository stakeholderRepository) {
    this.stakeholderRepository = stakeholderRepository;
  }

  public Object execute(BrowseStakeholdersByEntityInterestIdRequest request) {

    List<Stakeholder> findedStakeholders = this.stakeholderRepository.findByEntityInterestId(request.entityInterestId());
    return findedStakeholders.stream().map(
      stakeholder -> {
        BrowseStakeholdersByEntityInterestIdResponse stakeholdersResponse = new BrowseStakeholdersByEntityInterestIdResponse();
        stakeholdersResponse.setId(stakeholder.getId());
        stakeholdersResponse.setName(stakeholder.getNome());
        stakeholdersResponse.setType(stakeholder.getType());
        return stakeholdersResponse;
      }
    ).toList();
  }
}
