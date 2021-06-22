package com.arch.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Concern {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String description;
	
	
	private String priority;
	
	
	@OneToMany(mappedBy="concern", orphanRemoval = true, cascade = CascadeType.ALL)                                                                                                                                                                                                                                                                                                                                                                                   
	private List<Viewpoint> viewpoint;
	
	
//	@Enumerated(EnumType.STRING)
//	private ConcernPriority priority;
	
	
	
	
//	@ForeignKey(name="stakeholder_id")
	
//	@JoinColumn(name = "id", foreignKey = @ForeignKey(name = "id"))
	


	public List<Viewpoint> getViewpoint() {
		return viewpoint;
	}

	public void setViewpoint(List<Viewpoint> viewpoint) {
		this.viewpoint = viewpoint;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@JsonIdentityReference(alwaysAsId = true) 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey=@ForeignKey(name="stakeholder_id"))
	private List<Stakeholder> stakeholders;
	

	public List<Stakeholder> getStakeholders() {
		return stakeholders;
	}

	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	




	

	
}
 