package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Decision;

@Repository
@Transactional
public interface DecisionRepository extends CrudRepository<Decision, Long>{
	
	@Query("select d from Decision d where d.objective.id = ?1")
	public List<Decision> getDecisionPorObjective (Long idobjective);

}
