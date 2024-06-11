package dev.arch420x0.archce.application.usecases.manageentityinterest.commands;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.arch420x0.archce.application.interfaces.EntityInterestRepository;
import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.BrowseAllEntitiesInterestRes;
import dev.arch420x0.archce.domain.entities.EntityInterest;

@Component
public class BrowseAllEntitiesInterestCommand {
    private final EntityInterestRepository entityInterestRepository;

    public BrowseAllEntitiesInterestCommand(
            EntityInterestRepository entityInterestRepository) {
        this.entityInterestRepository = entityInterestRepository;
    }

    public List<BrowseAllEntitiesInterestRes> execute(RequestModel request) {
        List<EntityInterest> entities = entityInterestRepository.findAll();
        return List.of(
            entities.stream().map(
                entity -> {
                    BrowseAllEntitiesInterestRes res = new BrowseAllEntitiesInterestRes();
                    res.setId(entity.getId());
                    res.setName(entity.getName());
                    res.setBackground(entity.getBackground());
                    res.setPurpose(entity.getPurpose());
                    res.setScope(entity.getScope());
                    res.setApproach(entity.getApproach());
                    res.setSchedule(entity.getSchedule());
                    res.setMilestones(entity.getMilestones());
                    return res;
                }
            ).toArray(BrowseAllEntitiesInterestRes[]::new)
        );
    }

}
