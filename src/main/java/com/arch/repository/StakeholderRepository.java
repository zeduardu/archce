package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Stakeholder;

@Repository
@Transactional 
public interface StakeholderRepository extends CrudRepository <Stakeholder, Long>{

	//2 parametros @Query("select s from Stakeholder s where s.nome like %?1% and s.cargo like %?1%")
	
	//1 parametro
	@Query("select s from Stakeholder s where s.nome like %?1%")
	List<Stakeholder> findStakeholderByName(String nome);
	
	@Query("from Stakeholder as s join s.concerns as c join s.problems as p  left join p.objectives as o ")
	List<Stakeholder> findTodos();
}
