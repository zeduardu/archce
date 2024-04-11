package dev.arch420x0.archce.repository;

import java.util.List;

import dev.arch420x0.archce.model.Concern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcernRepository extends JpaRepository<Concern, Long> {

	// @Query("select c from Concern c where c.stakeholder.id = ?1")
	// public List<Concern> getConcernsPorStakeholder (Long idstakeholder);

	@Query("select c from Concern c where c.description like %?1%")
	public List<Concern> findConcernByName(String description);

}
