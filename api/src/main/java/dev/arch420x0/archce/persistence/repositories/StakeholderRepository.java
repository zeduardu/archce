package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.domain.entities.Stakeholder;

@Repository
public interface StakeholderRepository extends GenericRepository<Stakeholder, Long>{

	//2 parametros @Query("select s from Stakeholder s where s.nome like %?1% and s.cargo like %?1%")
	
	//1 parametro
	@Query("select s from Stakeholder s where s.nome like %?1%")
	List<Stakeholder> findStakeholderByName(String nome);
	
	@Query("from Stakeholder as s join s.concerns as c join s.problems as p  left join p.objectives as o ")
	List<Stakeholder> findTodos();

	@Query("select s from Stakeholder s where s.entityInterest.id = ?1")
	List<Stakeholder> findByEntityInterestId(Long id);
}
