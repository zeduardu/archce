package com.arch.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Problem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String area;
	private String aspects;
	private String risks;
	private String oportunities;
	private String constraints;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="stakeholder_id"))
	private Stakeholder stakeholder;
	
	
	@OneToMany(mappedBy="problem")                                                                                                                                                                                                                                                                                                                                                                                   
	private List<Objective> objectives;  
	
	
	public Stakeholder getStakeholder() {
		return stakeholder;
	}
	
	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAspects() {
		return aspects;
	}
	public void setAspects(String aspects) {
		this.aspects = aspects;
	}
	public String getRisks() {
		return risks;
	}
	public void setRisks(String risks) {
		this.risks = risks;
	}
	public String getOportunities() {
		return oportunities;
	}
	public void setOportunities(String oportunities) {
		this.oportunities = oportunities;
	}
	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}

	public List<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(List<Objective> objectives) {
		this.objectives = objectives;
	}

}
