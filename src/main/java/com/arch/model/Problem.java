package com.arch.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
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

	@JsonIdentityReference(alwaysAsId = true)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "problem_stakeholder", joinColumns = @JoinColumn(name = "id_stakeholder"), inverseJoinColumns = @JoinColumn(name = "id_concern"))
	private List<Stakeholder> stakeholders;

	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
	private List<Objective> objectives;

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

	public List<Stakeholder> getStakeholders() {
		return stakeholders;
	}

	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}

	public String getListaObjetivos() {
		if (this.objectives == null) {
			return "";
		}

		return this.objectives.stream().map(o -> o.getDescription()).sorted().collect(Collectors.joining(", "));
	}

	public String getTypicalStakeholders() {
		if (this.stakeholders == null) {
			return "";
		}
		return this.stakeholders.stream().map(st -> st.getNome()).sorted().collect(Collectors.joining(", "));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Problem other = (Problem) obj;
		return Objects.equals(id, other.id);
	}



}
