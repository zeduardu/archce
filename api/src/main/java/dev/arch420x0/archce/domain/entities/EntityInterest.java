package dev.arch420x0.archce.domain.entities;

import dev.arch420x0.archce.domain.common.BaseAuditableEntity;
import jakarta.persistence.Column;
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
  @Column(columnDefinition = "TEXT")
  private String background;
  @Column(columnDefinition = "TEXT")
  private String purpose;
  @Column(columnDefinition = "TEXT")
  private String scope;
  @OneToMany(mappedBy = "entityInterest")
  private List<Objective> objectives;
  @OneToMany(mappedBy = "entityInterest")
  private List<Stakeholder> stakeholders;
  @Column(columnDefinition = "TEXT")
  private String approach;
  @Column(columnDefinition = "TEXT")
  private String schedule;
  @Column(columnDefinition = "TEXT")
  private String milestones;
  @OneToMany(mappedBy = "entityInterest")
  private List<Metric> metrics;
}
