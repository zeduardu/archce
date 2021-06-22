package com.arch.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Metric implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String valor;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="tradeoff_id"))
	private Tradeoff tradeoff;
	
	
	public Tradeoff getTradeoff() {
		return tradeoff;
	}
	public void setTradeoff(Tradeoff tradeoff) {
		this.tradeoff = tradeoff;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
