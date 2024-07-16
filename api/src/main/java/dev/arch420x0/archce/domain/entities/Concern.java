package dev.arch420x0.archce.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Concern extends BaseAuditableEntity implements Serializable {

	private String description;

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

	public String getTypicalStakeholders() {
		if (this.stakeholders == null) {
			return "";
		}
		return this.stakeholders.stream().map(st -> st.getName()).distinct().sorted().collect(Collectors.joining(","));
	}

}
