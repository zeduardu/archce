package dev.arch420x0.archce.application.usecases.manageviewpoint.dtos;

import dev.arch420x0.archce.domain.entities.Concern;
import lombok.Value;

import java.util.List;

@Value
public class ViewpointRequest {
  String name;
  String overview;
  String rationale;
  String model;
  String conventions;
  String source;
}
