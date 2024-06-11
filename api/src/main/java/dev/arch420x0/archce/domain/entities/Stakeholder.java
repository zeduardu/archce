package dev.arch420x0.archce.domain.entities;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

	public Stakeholder() {}

    public String getConcernsFormatado() {
		if (concerns == null) return "";
		return concerns.stream().map(Concern::getDescription).sorted().collect(Collectors.joining(","));
	}

	public String getProblemsFormatado() {
		if (concerns == null) return "";
		return problems.stream().map(Problem::getTitle).sorted().collect(Collectors.joining(",  "));
	}
}
