package dev.arch420x0.archce.ui.controller;

import static java.util.stream.StreamSupport.stream;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;
import java.util.stream.Collectors;

import dev.arch420x0.archce.application.dtos.StakeholderRequest;
import dev.arch420x0.archce.application.usecases.managestakeholders.ManageStakeholderUseCase;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.AddStakeholderRequest;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.AddStakeholderResponse;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.BrowseStakeholdersByEntityInterestIdRequest;
import dev.arch420x0.archce.application.usecases.managestakeholders.dtos.BrowseStakeholdersByEntityInterestIdResponse;
import dev.arch420x0.archce.persistence.repositories.ConcernRepository;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import dev.arch420x0.archce.domain.entities.Concern;
import dev.arch420x0.archce.domain.entities.Problem;
import dev.arch420x0.archce.domain.entities.Stakeholder;

@RestController
@RequestMapping("/api/v1/stakeholders")
@Tag(name = "stakeholders", description = "The stakeholders API")
@Controller
public class StakeholderController {
  private final ManageStakeholderUseCase manageStakeholderUseCase;

  @Autowired
  private StakeholderRepository stakeholderRepository;

  @Autowired
  private ConcernRepository concernRepository;

  public StakeholderController(ManageStakeholderUseCase manageStakeholderUseCase) {
    this.manageStakeholderUseCase = manageStakeholderUseCase;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/cadastrostakeholder")
  public ModelAndView inicio() {

    ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
    modelAndView.addObject("stakeholderobj", new Stakeholder());

    Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
    modelAndView.addObject("stakeholders", stakeholdersIt);
    modelAndView.addObject("concerns", concernRepository.findAll());

    return modelAndView;

  }

  @RequestMapping(method = RequestMethod.POST, value = "/salvarstakeholder")
  public ModelAndView salvar(@Valid Stakeholder stakeholder, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      List<String> msg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
        .collect(Collectors.toList());
      Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();

      ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
      modelAndView.addObject("stakeholders", stakeholdersIt);
      modelAndView.addObject("stakeholderobj", stakeholder);
      modelAndView.addObject("msg", msg);
      return modelAndView;

    }

    Iterable<Concern> allConcerns = concernRepository.findAll();
    for (Concern c : allConcerns) {
      if (c.getStakeholders() != null) {
        c.getStakeholders().remove(stakeholder);
      }
      if (stakeholder.getConcerns().contains(c)) {
        c.getStakeholders().add(stakeholder);
      }
    }

    stakeholderRepository.save(stakeholder);
    concernRepository.saveAll(allConcerns);

    return new ModelAndView("redirect:/cadastrostakeholder");

  }

  @GetMapping(value = "/listarstakeholder")
  public ModelAndView stakeholders() {
    ModelAndView andView = new ModelAndView("cadastro/cadastrostakeholder");
    Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
    andView.addObject("stakeholders", stakeholdersIt);
    andView.addObject("stakeholderobj", new Stakeholder());
    return andView;

  }

  @GetMapping(value = "/search")
  public ModelAndView search() {

    ModelAndView modelAndView = new ModelAndView("cadastro/search");
    modelAndView.addObject("stakeholderobj", new Stakeholder());

    return modelAndView;

  }

  @GetMapping(value = "/welcome")
  public ModelAndView welcome() {

    ModelAndView modelAndView = new ModelAndView("welcome");
    modelAndView.addObject("stakeholderobj", new Stakeholder());

    return modelAndView;

  }

  @GetMapping("/editarstakeholder/{idstakeholder}")
  public ModelAndView editar(@PathVariable("idstakeholder") Long idstakeholder) {

    Stakeholder stakeholder = stakeholderRepository.findById(idstakeholder).orElseThrow();
    Iterable<Stakeholder> sts = stakeholderRepository.findAll();
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
    modelAndView.addObject("stakeholderobj", stakeholder).addObject("concerns", concernRepository.findAll());
    List<Stakeholder> outrosSts = stream(sts.spliterator(), false).filter(v -> !v.getId().equals(idstakeholder))
      .collect(Collectors.toList());
    modelAndView.addObject("stakeholders", outrosSts);
    return modelAndView;

  }

  @GetMapping("/removerstakeholder/{idstakeholder}")
  public ModelAndView excluir(@PathVariable("idstakeholder") Long idstakeholder) {

    Stakeholder st = stakeholderRepository.findById(idstakeholder).get();
    for (Concern c : st.getConcerns()) {
      if (c.getStakeholders() != null) {
        c.getStakeholders().remove(st);
      }

    }
    st.getConcerns().clear();

    for (Problem p : st.getProblems()) {

      if (p.getStakeholders() != null) {
        p.getStakeholders().remove(st);
      }
    }
    st.getProblems().clear();

    stakeholderRepository.deleteById(idstakeholder);

    return new ModelAndView("redirect:/cadastrostakeholder");

  }

  @PostMapping("/pesquisarstakeholder")
  public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
    ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
    modelAndView.addObject("stakeholders", stakeholderRepository.findStakeholderByName(nomepesquisa))
      .addObject("stakeholderobj", new Stakeholder());
    return modelAndView;
  }

  @GetMapping(value = "/report_stakeholder")
  public ModelAndView report() {

    ModelAndView modelAndView = new ModelAndView("cadastro/report_stakeholder");

    Iterable<Stakeholder> stakeholders = stakeholderRepository.findAll();
    modelAndView.addObject("stakeholders", stakeholders);

    return modelAndView;

  }

  @Operation(
    summary = "Returns all stakeholders based on given entity of interest id.",
    responses = {
      @ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = BrowseStakeholdersByEntityInterestIdResponse.class))
      })
    }
  )
  @GetMapping(value = "/entity-interest/{entityInterestId}")
  public ResponseEntity<List<BrowseStakeholdersByEntityInterestIdResponse>> browseStakeholdersByEntityInterestId(
    @Parameter(description = "The entity of interest id") @Valid @PathVariable Long entityInterestId
  ) {
    return status(HttpStatus.OK).body(
      (List<BrowseStakeholdersByEntityInterestIdResponse>) manageStakeholderUseCase.handle(new BrowseStakeholdersByEntityInterestIdRequest(entityInterestId))
    );
  }

  @Operation(
    summary = "Returns all stakeholders.",
    responses = {
      @ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = StakeholderRequest.class))
      })
    }
  )
  @GetMapping()
  public ResponseEntity<List<StakeholderRequest>> browseAllStakeholders() {
    return status(HttpStatus.OK).body(
      manageStakeholderUseCase.browseAllStakeholders()
    );
  }

  @Operation(
    summary = "Adds a new stakeholder.",
    responses = {
      @ApiResponse(responseCode = "201", description = "For successful creation.", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = AddStakeholderResponse.class))
      })
    }
  )
  @PostMapping
  public ResponseEntity<AddStakeholderResponse> addStakeholder(@Parameter(description = "Necessary data to add one stakeholder") @Valid @RequestBody AddStakeholderRequest addStakeholderRequest) {
    return status(HttpStatus.CREATED).body(
      (AddStakeholderResponse) manageStakeholderUseCase.handle(addStakeholderRequest)
    );
  }
}
