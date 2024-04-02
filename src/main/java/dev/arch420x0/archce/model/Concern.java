package dev.arch420x0.archce.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Concern {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;

//	private String priority;

	@JsonIdentityReference(alwaysAsId = true)
	@ManyToMany
	@JoinTable(name = "concern_viewpoint", joinColumns = @JoinColumn(name = "id_viewpoint"), inverseJoinColumns = @JoinColumn(name = "id_concern"))
	private List<Viewpoint> viewpoints;

	@JsonIdentityReference(alwaysAsId = true)
	@ManyToMany
	@JoinTable(name = "concern_stakeholder", joinColumns = @JoinColumn(name = "id_stakeholder"), inverseJoinColumns = @JoinColumn(name = "id_concern"))
	private List<Stakeholder> stakeholders;

	public List<Stakeholder> getStakeholders() {
		return this.stakeholders;
	}

	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders = stakeholders;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Viewpoint> getViewpoints() {
		return this.viewpoints;
	}

	public void setViewpoints(List<Viewpoint> viewpoints) {
		this.viewpoints = viewpoints;
	}

/**	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
**/

	public String getTypicalStakeholders() {
		if (this.stakeholders == null) {
			return "";
		}
		return this.stakeholders.stream().map(st -> st.getNome()).distinct().sorted().collect(Collectors.joining(","));
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
		Concern other = (Concern) obj;
		return Objects.equals(id, other.id);
	}

}
