package dev.arch420x0.archce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.model.Objective;

@Repository
public interface ObjectiveRepository extends CrudRepository<Objective, Long>{
	
	@Query("select o from Objective o where o.problem.id = ?1")
	public List<Objective> getObjectivePorProblem (Long idproblem);

}
