package dev.arch420x0.archce.application.usecases.manageviewpoint;

import dev.arch420x0.archce.application.common.GenericMapper;
import dev.arch420x0.archce.application.usecases.manageviewpoint.dtos.ViewpointRequest;
import dev.arch420x0.archce.application.usecases.manageviewpoint.dtos.ViewpointResponse;
import dev.arch420x0.archce.domain.entities.Viewpoint;
import dev.arch420x0.archce.persistence.repositories.ViewpointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageViewpointUseCase {
  private final ViewpointRepository viewpointRepository;
  private final GenericMapper genericMapper;

  public ManageViewpointUseCase(ViewpointRepository viewpointRepository, GenericMapper genericMapper) {
    this.genericMapper = genericMapper;
    this.viewpointRepository = viewpointRepository;
  }

  public ViewpointResponse addViewpoint(ViewpointRequest request) {
    Viewpoint viewpoint = genericMapper.toEntity(request, Viewpoint.class);
    return genericMapper.toDto(viewpointRepository.save(viewpoint), ViewpointResponse.class);
  }

  public ViewpointResponse editViewpoint(ViewpointRequest request) {
    return null;
  }

  public void deleteViewpoint(ViewpointRequest request) {

  }

  public List<ViewpointResponse> browseViewpoints() {
    return viewpointRepository.findAll().stream().map(
      viewpoint -> genericMapper.toDto(viewpoint, ViewpointResponse.class)
    ).collect(Collectors.toList());
  }
}
