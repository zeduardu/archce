package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import dev.arch420x0.archce.domain.entities.Viewpoint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewpointRepository extends CrudRepository<Viewpoint, Long> {

	// @Query("select v from Viewpoint v where v.concern.id = ?1")
	// public List<Viewpoint> getViewPorConcern (Long idconcern);

	@Query("select v from Viewpoint v where v.rationale like %?1%")
	public List<Viewpoint> findViewpointByName(String rationale);

}
