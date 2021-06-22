package com.arch.model;


import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Stakeholder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@NotNull(message="Nome não pode ser nulo")
	@NotBlank(message="Nome não pode ser vazio")
	private String nome;
	
	
	@Enumerated(EnumType.STRING)
	private StakeholderType type;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name= "stakeholder_concern",
			joinColumns = @JoinColumn(name = "stakeholder_id",
						  referencedColumnName = "id",
						  table = "Stakeholder"), 
	
			   inverseJoinColumns = @JoinColumn(name="concern_id",
					   				referencedColumnName = "id",
					   				table = "concern"))
			
	private List<Concern> concerns;
	
	public List<Concern> getConcerns() {
		return concerns;
	}

	public void setConcerns(List<Concern> concerns) {
		this.concerns = concerns;
	}
	/**
	@OneToMany(mappedBy="stakeholder", orphanRemoval = true, cascade = CascadeType.ALL)                                                                                                                                                                                                                                                                                                                                                                                   
	private List<Concern> concerns;
	
	public void setConcerns (List<Concern> concerns) {
		this.concerns = concerns;		
	}
	
	public List<Concern> getConcerns(){		
		return concerns;
	}
	
	**/
	
	@OneToMany(mappedBy="stakeholder", orphanRemoval = true, cascade = CascadeType.ALL)                                                                                                                                                                                                                                                                                                                                                                                   
	private List<Problem> problems;
	
	public void setProblems (List<Problem> problems) {
		this.problems = problems;
		
	}
	
	public List<Problem> getProblems(){
		
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
	
	public StakeholderType getType() {
		return type;
	}
	public void setType(StakeholderType type) {
		this.type = type;
	}


	
}
