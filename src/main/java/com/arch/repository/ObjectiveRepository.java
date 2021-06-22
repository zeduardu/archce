package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Objective;

@Repository
@Transactional
public interface ObjectiveRepository extends CrudRepository<Objective, Long>{
	
	@Query("select o from Objective o where o.problem.id = ?1")
	public List<Objective> getObjectivePorProblem (Long idproblem);

}
