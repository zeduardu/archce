package dev.arch420x0.archce.ui.controller;

import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.AddEntityInterestReq;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.AddEntityInterestRes;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.BrowseAllEntitiesInterestReq;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.BrowseAllEntitiesInterestRes;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.ReadEntityInterestByIdRes;
import dev.arch420x0.archce.application.usecases.manageentityinterest.dtos.RegisterEntityInterestReq;
import dev.arch420x0.archce.application.usecases.manageentityinterest.ManageEntityInterestUseCase;
import dev.arch420x0.archce.application.usecases.registerentityinterest.RegisterEntityInterestUseCase;
import dev.arch420x0.archce.domain.entities.EntityInterest;
import dev.arch420x0.archce.ui.EntityInterestModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/v1/entities-interest")
@Tag(name = "entities-interest", description = "The entities of interest API")
public class EntityInterestController {
  private final RegisterEntityInterestUseCase registerEntityInterestUseCase;
  private final ManageEntityInterestUseCase manageEntityInterestUseCase;
  private final EntityInterestModelAssembler entityInterestModelAssembler;

  public EntityInterestController(
    RegisterEntityInterestUseCase registerEntityInterestUseCase,
    ManageEntityInterestUseCase manageEntityInterestUseCase,
    EntityInterestModelAssembler entityInterestModelAssembler) {
    this.registerEntityInterestUseCase = registerEntityInterestUseCase;
    this.manageEntityInterestUseCase = manageEntityInterestUseCase;
    this.entityInterestModelAssembler = entityInterestModelAssembler;
  }

  //BREAD - Browse, Read, Edit, Add, Delete

  /**
   * GET /api/v1/entities-interest : Returns all entities of interest.
   * Returns all entities of interest.
   *
   * @return For successful fetch. (status code 200)
   */
  @Operation(
    summary = "Returns all entities of interest",
    responses = {
      @ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BrowseAllEntitiesInterestRes.class))
      })
    }
  )
  @GetMapping(
    produces = {"application/json"}
  )
  public ResponseEntity<List<BrowseAllEntitiesInterestRes>> getAllEntitiesInterest() {
    List<BrowseAllEntitiesInterestRes> response = (List<BrowseAllEntitiesInterestRes>) this.manageEntityInterestUseCase.handle(new BrowseAllEntitiesInterestReq());
    return status(HttpStatus.OK).body(response);
  }

    /**
   * GET /api/v1/entities-interest/{id} : Returns entity of interest based on given ID.
   * Returns entity of interest based on given ID.
   *
   * @param id address Identifier (required)
   * @return For successful fetch. (status code 200)
   */
  @Operation(
    summary = "Returns entity of interest based on given ID",
    responses = {
      @ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ReadEntityInterestByIdRes.class))
      })
    }
  )
  @GetMapping(
    value = "/{id}",
    produces = {"application/json"}
  )
  public ResponseEntity<ReadEntityInterestByIdRes> getEntityInterestById(@Parameter(name = "id", description = "Entity identifier", required = true) @PathVariable("id") String id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  /**
   * POST /api/v1/entities-interest : Creates a new entity of interest
   * Creates a new entity od interest. Does nothing if entity already exists.
   *
   * @param request
   * @return For successful fetch. (status code 200)
   */
  @PostMapping(
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  public ResponseEntity<AddEntityInterestRes> addEntityInterest(@Parameter(description = "Entity of interest to register") @Valid @RequestBody AddEntityInterestReq request) {
    Optional<EntityInterest> response = (Optional<EntityInterest>) this.manageEntityInterestUseCase.handle(request);

    return status(HttpStatus.OK).body(
      response.map(this.entityInterestModelAssembler::toModel).get()
    );
  }

  /**
   * POST /api/v1/entities-interest : Creates a new entity of interest
   * Creates a new entity od interest. Does nothing if entity already exists.
   *
   * @param request
   * @return For successful fetch. (status code 200)
   */
  @PostMapping(
    value = "register-entity",
    produces = {"application/json"},
    consumes = {"application/json"}
  )
  public ResponseEntity<AddEntityInterestRes> registerEntityInterest(@Parameter(description = "Entity of interest to register") @Valid @RequestBody RegisterEntityInterestReq request) {
    return status(HttpStatus.CREATED).body(
      this.registerEntityInterestUseCase.execute(request).map(this.entityInterestModelAssembler::toModel).get()
    );
  }
}
