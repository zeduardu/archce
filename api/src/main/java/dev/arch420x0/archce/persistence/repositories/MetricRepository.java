package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import dev.arch420x0.archce.domain.entities.Metric;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends CrudRepository<Metric, Long> {

	@Query("select m from Metric m where m.tradeoff.id = ?1")
	public List<Metric> getMetricPorTradeoff(Long idtradeoff);

}
