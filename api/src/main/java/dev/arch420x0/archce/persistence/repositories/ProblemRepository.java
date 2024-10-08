package dev.arch420x0.archce.persistence.repositories;

import dev.arch420x0.archce.domain.entities.Problem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Long> {

	// @Query("select p from Problem p where p.stakeholder.id = ?1")
	// public List<Problem> getProblemPorStakeholder (Long idstakeholder);

}
