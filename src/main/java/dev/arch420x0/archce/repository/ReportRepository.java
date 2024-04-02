package dev.arch420x0.archce.repository;

import java.sql.ResultSet;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepository {
	
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Collection<ItemRelatorio> fillReport(){
    String sql = "select \r\n" +  
    		"	s.nome, \r\n" + 
    		"	s.type,\r\n" + 
    		"	c.description,\r\n" + 
    		"	c.priority,\r\n" + 
    		"	p.title,\r\n" + 
    		"	p.area,\r\n" + 
    		"	p.aspects,\r\n" + 
    		"	p.constraints,\r\n" + 
    		"	p.oportunities,\r\n" + 
    		"	p.risks,\r\n" + 
    		"	o.description,\r\n" + 
    		"	o.rationale,\r\n" + 
    		"	d.description,\r\n" + 
    		"	d.rationale,\r\n" + 
    		"	d.solution,\r\n" + 
    		"	t.description,\r\n" + 
    		"	t.type,\r\n" + 
    		"	t.rationale\r\n" + 
    		"from stakeholder s\r\n" + 
    		"left join concern c\r\n" + 
    		"	ON s.id = c.stakeholder_id\r\n" + 
    		"left join problem p\r\n" + 
    		"	ON s.id = p.stakeholder_id\r\n" + 
    		"left join objective o\r\n" + 
    		"	ON p.id = o.problem_id\r\n" + 
    		"left join decision d\r\n" + 
    		"	ON o.id = d.objective_id\r\n" + 
    		"left join tradeoff t\r\n" + 
    		"	ON d.id = t.decision_id\r\n" + 
    		"order by s.nome;" ;  
    Collection<ItemRelatorio> lista = jdbcTemplate.query(sql, new Object[] {}, 
             (ResultSet rs, int rowNum) -> {
                ItemRelatorio ir = new ItemRelatorio(); 
                ir.setStakeholderNome(rs.getString(1));
                ir.setStakeholderType(rs.getString(2));
                ir.setConcernDescription(rs.getString(3));
                ir.setConcernPriority(rs.getString(4));
                ir.setProblemTitle(rs.getString(5));
                ir.setProblemArea(rs.getString(6));
                ir.setProblemAspects(rs.getString(7));
                ir.setProblemConstraints(rs.getString(8));
                ir.setProblemOportunities(rs.getString(9));
                ir.setProblemRisks(rs.getString(10));
                ir.setObjectiveDescription(rs.getString(11));
                ir.setObjectiveRationale(rs.getString(12));
                ir.setDecisionDescription(rs.getString(13));
                ir.setDecisionRationale(rs.getString(14));
                ir.setDecisionSolution(rs.getString(15));
                ir.setTradeoffDescription(rs.getString(16));
                ir.setTradeoffType(rs.getString(17));
                ir.setTradeoffRationale(rs.getString(18));
                
                
                
                
        return ir;
      }
    );
    return lista;
  }

	
	
}
