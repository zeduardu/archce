package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Metric;
import com.arch.model.Tradeoff;

@Repository
@Transactional
public interface MetricRepository extends CrudRepository<Metric, Long>{
	
	@Query("select m from Metric m where m.tradeoff.id = ?1")
	public List<Metric> getMetricPorTradeoff (Long idtradeoff);

}
