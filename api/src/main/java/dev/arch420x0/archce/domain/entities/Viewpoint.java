package dev.arch420x0.archce.domain.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.proxy.HibernateProxy;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Viewpoint extends BaseAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	private String overview;

	@ManyToMany(mappedBy = "viewpoints", fetch = FetchType.EAGER)
	private List<Concern> concerns;

	// Stakeholder perspectives:
	// In the report/document get stakeholder perspectives from concerns entity if exist.

	//TODO In the future add "Aspects" field to store a listing of the aspects refining the above concerns [ISO/IEC/IEEE 42010:2022, per 8.1 item c)] or encompassing potential concerns.

	// Typical stakeholders:
	// In the report/document get a listing of the stakeholders expected to be users or audiences
	// for views prepared using this architecture viewpoint [ISO/IEC/IEEE 42010:2022, per 8.1 item d)]

	// (B.2.9 - Specification of model kinds) The architecture viewpoint identifies each model kind [ISO/IEC/IEEE 42010:2022, per 8.1 item d)].
	private String model;

	// For each model kind used, describe its conventions, language or modelling techniques. These are
	// key modelling resources which the specification of the architecture viewpoint makes available that
	// establish the vocabularies for constructing the architecture views.
	private String conventions;

	private String rationale;

	// Identify the sources for this specification, if any, including author, history,
	// literature references and prior art [per 8.1 item g)].
	private String source;

	public String getConcernsFormatado() {
		if (concerns == null) {
			return "";
		}
		try {
			String aux = concerns.stream().map(c -> c.getDescription()).sorted().collect(Collectors.joining(", "));
			return aux;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public String getTypicalStakeholders() {
		if (this.concerns == null) {
			return "";
		}
		return this.concerns.stream().flatMap(c -> Stream.ofNullable(c.getStakeholders())).flatMap(st -> st.stream())
				.map(st -> st.getName()).distinct().sorted().collect(Collectors.joining(", "));
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy
				? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
				: o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy
				? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
				: this.getClass();
		if (thisEffectiveClass != oEffectiveClass)
			return false;
		Viewpoint viewpoint = (Viewpoint) o;
		return getId() != null && Objects.equals(getId(), viewpoint.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy
				? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
				: getClass().hashCode();
	}
}
