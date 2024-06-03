package dev.arch420x0.archce.domain.entities;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class EntityInterest extends BaseAuditableEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private String name;
  private String background;
  private String purpose;
  private String scope;
  @OneToMany(mappedBy = "entityInterest")
  private List<Objective> objectives;
  @OneToMany(mappedBy = "entityInterest")
  private List<Stakeholder> stakeholders;
  private String approach;
  private String schedule;
  private String milestones;
  @OneToMany(mappedBy = "entityInterest")
  private List<Metric> metrics;
}
