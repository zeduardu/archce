package dev.arch420x0.archce.application.usecases.manageobjective.dtos;

import dev.arch420x0.archce.application.interfaces.RequestModel;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.domain.entities.Problem;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Objective}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class EditObjectiveRes extends Response<List<EditObjectiveRes>> implements Serializable {
  Long id;
  String description;
  String rationale;
}