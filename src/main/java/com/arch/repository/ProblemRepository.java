package com.arch.repository;

import javax.transaction.Transactional;

import com.arch.model.Problem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ProblemRepository extends CrudRepository<Problem, Long> {

	// @Query("select p from Problem p where p.stakeholder.id = ?1")
	// public List<Problem> getProblemPorStakeholder (Long idstakeholder);

}
