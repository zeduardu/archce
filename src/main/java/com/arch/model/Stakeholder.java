package com.arch.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Stakeholder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be empty")
	private String nome;

	private String type;

	@ManyToMany(mappedBy = "stakeholders")
	private List<Concern> concerns;

	public void setConcerns(List<Concern> concerns) {
		this.concerns = concerns;
	}

	public List<Concern> getConcerns() {
		return concerns;
	}

	@ManyToMany(mappedBy = "stakeholders")
	private List<Problem> problems;

	public void setProblems(List<Problem> problems) {
		this.problems = problems;

	}

	public List<Problem> getProblems() {

		return problems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConcernsFormatado() {
		if (concerns == null) {
			return "";
		}

		return concerns.stream().map(c -> c.getDescription()).sorted().collect(Collectors.joining(","));
	}

	public String getProblemsFormatado() {
		if (concerns == null) {
			return "";
		}

		return problems.stream().map(c -> c.getTitle()).sorted().collect(Collectors.joining(",  "));
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
		Stakeholder other = (Stakeholder) obj;
		return Objects.equals(id, other.id);
	}

}
