package dev.arch420x0.archce.ui;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import dev.arch420x0.archce.application.models.AddEntityInterestRes;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.ui.controller.EntityInterestController;

@Component
public class EntityInterestModelAssembler extends RepresentationModelAssemblerSupport<EntityInterest, AddEntityInterestRes> {
  /**
   * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and
   * resource type.
   */
  public EntityInterestModelAssembler() {
    super(EntityInterestController.class, AddEntityInterestRes.class);
  }

  /**
   * Coverts the Entity of Interest entity to resource
   *
   * @param entity
   */
  @Override
  public AddEntityInterestRes toModel(EntityInterest entity) {
    AddEntityInterestRes resource = createModelWithId(entity.getId(), entity);
    BeanUtils.copyProperties(entity, resource);
    resource.add(
      linkTo(methodOn(EntityInterestController.class).getEntityInterestById(entity.getId().toString())).withSelfRel()
    );
    return resource;
  }
}
