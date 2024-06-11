package dev.arch420x0.archce.application.common;

import dev.arch420x0.archce.domain.entities.Viewpoint;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

  private final ModelMapper modelMapper;

  public GenericMapper() {
    this.modelMapper = new ModelMapper();
  }

  public <D, E> D toDto(E entity, Class<D> dtoClass) {
    return modelMapper.map(entity, dtoClass);
  }

  public <D, E> E toEntity(D dto, Class<E> entityClass) {
    return modelMapper.map(dto, entityClass);
  }
}
