package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Concern;
import com.arch.model.Stakeholder;

@Repository
@Transactional
public interface ConcernRepository extends CrudRepository<Concern, Long>{
	
	

	
	@Query("select c from Concern c where c.description like %?1%")
	public List<Concern> findConcernByName(String description);
	
	
	
}
