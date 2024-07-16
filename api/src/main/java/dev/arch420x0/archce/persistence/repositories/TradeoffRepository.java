package dev.arch420x0.archce.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.arch420x0.archce.domain.entities.Tradeoff;

@Repository
public interface TradeoffRepository extends CrudRepository<Tradeoff, Long>{
	
	@Query("select t from Tradeoff t where t.decision.id = ?1")
	public List<Tradeoff> getTradeoffPorDecision (Long iddecision);

}
