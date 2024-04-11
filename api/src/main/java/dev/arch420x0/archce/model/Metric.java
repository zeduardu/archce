package dev.arch420x0.archce.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
