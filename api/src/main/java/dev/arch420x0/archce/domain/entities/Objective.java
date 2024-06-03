package dev.arch420x0.archce.domain.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Objective extends BaseAuditableEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String description;
	private String rationale;
	@JsonIdentityReference(alwaysAsId = true)
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "problem_id"))
	private Problem problem;
    @OneToMany(mappedBy = "objective", cascade = CascadeType.ALL)
	private List<Decision> decisions;
	@ManyToOne
	private EntityInterest entityInterest;

	@Override
	public String toString() {
		return "Objective [id=" + getId() + ", description=" + description + ", rationale=" + rationale + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objective other = (Objective) obj;
		if (getId() == null) {
            return other.getId() == null;
		} else return getId().equals(other.getId());
    }
}
