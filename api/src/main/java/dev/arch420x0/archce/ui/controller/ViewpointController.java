package dev.arch420x0.archce.ui.controller;

import dev.arch420x0.archce.application.usecases.manageviewpoint.ManageViewpointUseCase;
import dev.arch420x0.archce.application.usecases.manageviewpoint.dtos.ViewpointRequest;
import dev.arch420x0.archce.application.usecases.manageviewpoint.dtos.ViewpointResponse;
import dev.arch420x0.archce.domain.entities.Concern;
import dev.arch420x0.archce.domain.entities.Viewpoint;
import dev.arch420x0.archce.persistence.repositories.ConcernRepository;
import dev.arch420x0.archce.persistence.repositories.ViewpointRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/viewpoints")
@Tag(name = "viewpoints", description = "Software architecture viewpoints API")
@Controller
public class ViewpointController {
	private final ManageViewpointUseCase manageViewpointUseCase;

	/**
	 * Pagina cadastro de viewpoint
	 */
	private static final String CADASTRO_VIEWPOINTREGISTRATION = "cadastro/viewpointregistration";

	@Autowired
	private ConcernRepository concernRepository;

	@Autowired
	private ViewpointRepository viewpointRepository;

  public ViewpointController(ManageViewpointUseCase manageViewpointUseCase) {
    this.manageViewpointUseCase = manageViewpointUseCase;
  }

  @GetMapping("/viewpointregistration")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEWPOINTREGISTRATION);
		modelAndView.addObject("viewpointobj", new Viewpoint());
		modelAndView.addObject("viewpoints", viewpointRepository.findAll());
		modelAndView.addObject("concerns", concernRepository.findAll());

		return modelAndView;
	}

	@PostMapping("/salvarviewpoint")
	public ModelAndView salvarViewpoint(@Valid Viewpoint viewpoint, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			Stream<Object> msg = bindingResult.getAllErrors().stream().map(o -> o.getDefaultMessage());
			ModelAndView modelAndView = inicio();
			modelAndView.addObject("viewpointobj", viewpoint);
			modelAndView.addObject("msg", msg);
			return modelAndView;

		}

		viewpointRepository.save(viewpoint);
		Iterable<Concern> allConcerns = concernRepository.findAll();
		for (Concern c : allConcerns) {
			if (c.getViewpoints() != null) {
				c.getViewpoints().remove(viewpoint);
			}
			if (viewpoint.getConcerns().contains(c)) {
				c.getViewpoints().add(viewpoint);
			}
			concernRepository.saveAndFlush(c);
		}

		return new ModelAndView("redirect:/viewpointregistration");

	}

	@GetMapping("/editarviewpoint/{idview}")
	public ModelAndView editar(@PathVariable("idview") Long idview) {

		Viewpoint viewpoint = viewpointRepository.findById(idview).get();

		Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
		List<Viewpoint> outrasVP = stream(viewpointsIt.spliterator(), false).filter(v -> !v.getId().equals(idview))
				.collect(toList());

		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEWPOINTREGISTRATION);
		modelAndView.addObject("viewpointobj", viewpoint);
		modelAndView.addObject("concerns", concernRepository.findAll());
		modelAndView.addObject("viewpoints", outrasVP);

		return modelAndView;

	}

	@GetMapping("/removerviewpoint/{idview}")
	public ModelAndView removerview(@PathVariable("idview") Long idview) {

		Viewpoint v = viewpointRepository.findById(idview).get();
		for (Concern c : v.getConcerns()) {
			if (c.getViewpoints() != null) {
				c.getViewpoints().remove(v);
			}
		}
		v.getConcerns().clear();

		viewpointRepository.deleteById(idview);

		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEWPOINTREGISTRATION);

		modelAndView.addObject("viewpoints", viewpointRepository.findAll());
		modelAndView.addObject("viewpointobj", new Viewpoint());
		modelAndView.addObject("concerns", concernRepository.findAll());

		return modelAndView;

	}

	@PostMapping("/pesquisarviewpoint")
	public ModelAndView pesquisarViewpoint(@RequestParam("viewpointpesquisa") String rationale) {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEWPOINTREGISTRATION);
		modelAndView.addObject("viewpoints", viewpointRepository.findViewpointByName(rationale));
		modelAndView.addObject("viewpointobj", new Concern());
		return modelAndView;
	}

	@GetMapping("/listarviewpoint")
	public ModelAndView listarViewpoints() {
		ModelAndView andView = new ModelAndView(CADASTRO_VIEWPOINTREGISTRATION);
		Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
		andView.addObject("viewpoints", viewpointsIt);
		andView.addObject("viewpointsobj", new Viewpoint());
		return andView;
	}

	@GetMapping(value = "/report_viewpoint")
	public ModelAndView report() {

		ModelAndView modelAndView = new ModelAndView("cadastro/report_viewpoint");

		Iterable<Viewpoint> viewpoints = viewpointRepository.findAll();
		modelAndView.addObject("viewpoints", viewpoints);

		return modelAndView;

	}

	@PostMapping(produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<ViewpointResponse> addViewpoint(@Parameter(description = "Objective of entity of interest") @Valid @RequestBody ViewpointRequest request) {
		return status(HttpStatus.CREATED).body(manageViewpointUseCase.addViewpoint(request));
	}
}
