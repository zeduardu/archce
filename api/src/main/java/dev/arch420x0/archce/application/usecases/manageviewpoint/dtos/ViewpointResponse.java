package dev.arch420x0.archce.application.usecases.manageviewpoint.dtos;

import java.io.Serializable;

/**
 * DTO for {@link dev.arch420x0.archce.domain.entities.Viewpoint}
 */
public record ViewpointResponse(Long id, String name, String overview, String rationale, String model,
                                String conventions, String source) implements Serializable {
}