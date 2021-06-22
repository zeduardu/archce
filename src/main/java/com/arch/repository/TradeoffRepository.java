package com.arch.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arch.model.Tradeoff;

@Repository
@Transactional
public interface TradeoffRepository extends CrudRepository<Tradeoff, Long>{
	
	@Query("select t from Tradeoff t where t.decision.id = ?1")
	public List<Tradeoff> getTradeoffPorDecision (Long iddecision);

}
