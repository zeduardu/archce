package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Concern;
import com.arch.model.Viewpoint;

@Repository
@Transactional
public interface ViewpointRepository extends CrudRepository<Viewpoint, Long>{
	
	@Query("select v from Viewpoint v where v.concern.id = ?1")
	public List<Viewpoint> getViewPorConcern (Long idconcern);
	
	@Query("select v from Viewpoint v where v.rationale like %?1%")
	public List<Viewpoint> findViewpointByName(String rationale);

}
