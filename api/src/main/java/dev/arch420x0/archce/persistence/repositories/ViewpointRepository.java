package dev.arch420x0.archce.persistence.repositories;

import dev.arch420x0.archce.domain.entities.Viewpoint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewpointRepository extends GenericRepository<Viewpoint, Long> {

	// @Query("select v from Viewpoint v where v.concern.id = ?1")
	// public List<Viewpoint> getViewPorConcern (Long idconcern);

	@Query("select v from Viewpoint v where v.rationale like %?1%")
	public List<Viewpoint> findViewpointByName(String rationale);

}
