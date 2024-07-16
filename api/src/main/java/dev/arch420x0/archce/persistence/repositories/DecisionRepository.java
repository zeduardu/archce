package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.domain.entities.Decision;

@Repository
public interface DecisionRepository extends CrudRepository<Decision, Long>{
	
	@Query("select d from Decision d where d.objective.id = ?1")
	public List<Decision> getDecisionPorObjective (Long idobjective);

}
