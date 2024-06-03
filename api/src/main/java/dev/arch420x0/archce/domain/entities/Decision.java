package dev.arch420x0.archce.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Decision implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;
	private String solution;
	private String rationale;

	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "objective_id"))
	private Objective objective;

	@OneToMany(mappedBy = "decision", cascade = CascadeType.ALL)
	private List<Tradeoff> tradeoffs;

	public List<Tradeoff> getTradeoffs() {
		return tradeoffs;
	}

	public void setTradeoffs(List<Tradeoff> tradeoffs) {
		this.tradeoffs = tradeoffs;
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
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

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getRationale() {
		return rationale;
	}

	public void setRationale(String rationale) {
		this.rationale = rationale;
	}
	
	public String getListaTradeoffs() {
		if (this.tradeoffs == null) {
			return "";
		}

		return this.tradeoffs.stream().map(td -> td.getType()).sorted().collect(Collectors.joining(", "));
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
		Decision other = (Decision) obj;
		return Objects.equals(id, other.id);
	}

}
