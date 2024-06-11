package dev.arch420x0.archce.application.usecases.manageviewpoint.dtos;

import lombok.Value;

@Value
public class ViewpointRequest {
  String name;
  String overview;
  String rationale;
  String model;
  String conventions;
  String source;
}
