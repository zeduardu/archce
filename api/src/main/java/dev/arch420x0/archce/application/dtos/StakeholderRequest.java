package dev.arch420x0.archce.application.dtos;

import dev.arch420x0.archce.domain.entities.EntityInterest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Stakeholder}
 */
@Data
public class StakeholderRequest implements Serializable {
  Long id;
  @NotNull(message = "Name can't be null")
  @NotBlank(message = "Name can't be empty")
  String nome;
  String type;
  EntityInterestRequest entityInterest;
}