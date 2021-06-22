package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Problem;

@Repository
@Transactional
public interface ProblemRepository extends CrudRepository<Problem, Long>{
	
	@Query("select p from Problem p where p.stakeholder.id = ?1")
	public List<Problem> getProblemPorStakeholder (Long idstakeholder);

}
