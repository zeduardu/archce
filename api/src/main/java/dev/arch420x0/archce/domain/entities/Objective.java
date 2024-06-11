package dev.arch420x0.archce.domain.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
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
	@ToString.Exclude
	private List<Decision> decisions;

	@ManyToOne
	private EntityInterest entityInterest;

	public Objective() {

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
