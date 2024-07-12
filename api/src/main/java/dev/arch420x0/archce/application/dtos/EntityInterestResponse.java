package dev.arch420x0.archce.application.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.EntityInterest}
 */
@NoArgsConstructor
@Getter
@Setter
public class EntityInterestResponse implements Serializable {
  Long id;
  String name;
  String background;
  String purpose;
  String scope;
  String approach;
  String schedule;
  String milestones;

  List<StakeholderResponse> stakeholders;
}