package dev.arch420x0.archce.domain.entities;

import java.io.Serial;
import java.io.Serializable;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Metric extends BaseAuditableEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	
	private String valor;
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="tradeoff_id"))
	private Tradeoff tradeoff;
	@ManyToOne
	private EntityInterest entityInterest;
}
