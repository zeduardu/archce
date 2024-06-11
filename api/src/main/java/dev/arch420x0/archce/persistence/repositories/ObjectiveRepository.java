package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.domain.entities.Objective;

@Repository
public interface ObjectiveRepository extends GenericRepository<Objective, Long>{
	
	@Query("select o from Objective o where o.problem.id = ?1")
	public List<Objective> getObjectivePorProblem (Long idproblem);

	/**
	 * Find all objectives by entity of interest id
	 * @param entityInterestId
	 * @return List<Objective>
	 */
	@Query("SELECT o FROM Objective o WHERE o.entityInterest.id = ?1")
	public List<Objective> findByEntityInterestId(Long entityInterestId);

}
