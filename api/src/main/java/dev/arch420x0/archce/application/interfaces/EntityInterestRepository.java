package dev.arch420x0.archce.application.interfaces;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.persistence.repositories.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityInterestRepository extends GenericRepository<EntityInterest, Long> {
}
