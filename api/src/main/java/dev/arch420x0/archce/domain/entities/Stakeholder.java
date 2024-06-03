package dev.arch420x0.archce.domain.entities;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Stakeholder extends BaseAuditableEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be empty")
	private String nome;
	private String type;
	@ManyToMany(mappedBy = "stakeholders")
	private List<Concern> concerns;
    @ManyToMany(mappedBy = "stakeholders")
	private List<Problem> problems;
	@ManyToOne
	private EntityInterest entityInterest;

    public String getConcernsFormatado() {
		if (concerns == null) return "";
		return concerns.stream().map(Concern::getDescription).sorted().collect(Collectors.joining(","));
	}

	public String getProblemsFormatado() {
		if (concerns == null) return "";
		return problems.stream().map(Problem::getTitle).sorted().collect(Collectors.joining(",  "));
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || this.getClass() != obj.getClass()) return false;
		Class<?> oEffectiveClass = obj instanceof HibernateProxy hibernateproxy ? hibernateproxy.getHibernateLazyInitializer().getPersistentClass() : obj.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy hibernateproxy ? hibernateproxy.getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		Stakeholder that = (Stakeholder) obj;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy hibernateproxy ? hibernateproxy.getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
