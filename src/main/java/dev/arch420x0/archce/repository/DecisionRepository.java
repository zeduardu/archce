package dev.arch420x0.archce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.model.Decision;

@Repository
public interface DecisionRepository extends CrudRepository<Decision, Long>{
	
	@Query("select d from Decision d where d.objective.id = ?1")
	public List<Decision> getDecisionPorObjective (Long idobjective);

}
