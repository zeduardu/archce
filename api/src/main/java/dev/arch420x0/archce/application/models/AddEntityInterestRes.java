package dev.arch420x0.archce.application.models;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class AddEntityInterestRes extends RepresentationModel<AddEntityInterestRes> {
    private Long id;
}
