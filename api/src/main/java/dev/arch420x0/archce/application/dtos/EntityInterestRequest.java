package dev.arch420x0.archce.application.dtos;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.EntityInterest}
 */
@Value
public class EntityInterestRequest implements Serializable {
  Long id;
  String name;
  String background;
  String purpose;
  String scope;
  String approach;
  String schedule;
  String milestones;
}