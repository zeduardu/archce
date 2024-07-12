package dev.arch420x0.archce.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Stakeholder}
 */
@Data
public class StakeholderResponse implements Serializable {
  private Long id;
  @NotNull(message = "Name can't be null")
  @NotBlank(message = "Name can't be empty")
  private String name;
  private String type;
  private Long entityInterestId;
}