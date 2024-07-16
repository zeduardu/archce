package dev.arch420x0.archce.ui.controller;

import dev.arch420x0.archce.application.usecases.manageobjective.dtos.*;
import dev.arch420x0.archce.application.usecases.manageobjective.ManageObjectiveUseCase;
import dev.arch420x0.archce.domain.entities.Decision;
import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.domain.entities.Problem;
import dev.arch420x0.archce.domain.entities.Tradeoff;
import dev.arch420x0.archce.infrastructure.shortbus.Response;
import dev.arch420x0.archce.persistence.repositories.DecisionRepository;
import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import dev.arch420x0.archce.persistence.repositories.ProblemRepository;
import dev.arch420x0.archce.persistence.repositories.TradeoffRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@Controller
@RestController
@RequestMapping("/api/v1/objectives")
@Tag(name = "objectives", description = "The objectives API")
public class ObjectiveController {
	private final ManageObjectiveUseCase manageObjectiveUseCase;

	@Autowired
	private ObjectiveRepository objectiveRepository;

	@Autowired
	private DecisionRepository decisionRepository;

	@Autowired
	private TradeoffRepository tradeoffRepository;

	@Autowired
	private ProblemRepository problemRepository;

  public ObjectiveController(ManageObjectiveUseCase manageObjectiveUseCase) {
    this.manageObjectiveUseCase = manageObjectiveUseCase;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/objectiveregistration")
	public ModelAndView objectives() {

		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");

		modelAndView.addObject("objectiveobj", new Objective());

		Iterable<Objective> objectivesIt = objectiveRepository.findAll();

		modelAndView.addObject("objectives", objectivesIt);

		modelAndView.addObject("problems", problemRepository.findAll());

		return modelAndView;

		/*
		 * Optional<Problem> problemId = problemRepository.findById(idproblem);
		 * 
		 * ModelAndView modelAndView = new
		 * ModelAndView("cadastro/objectiveregistration");
		 * modelAndView.addObject("problemobj", problemId.get());
		 * modelAndView.addObject("objectives",
		 * objectiveRepository.getObjectivePorProblem(idproblem));
		 * 
		 * return modelAndView;
		 */

	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarobjective")
	public ModelAndView salvarObjective(@Valid Objective objective, BindingResult bindingResult) {

		// problemId.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");

			Iterable<Objective> objectivesIt = objectiveRepository.findAll();
			modelAndView.addObject("objectives", objectivesIt);
			modelAndView.addObject("objectiveobj", objective);

			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // vem das anotacoes @NotEmpty e outras
			}

			modelAndView.addObject("msg", msg);
			modelAndView.addObject("problems", problemRepository.findAll());
			return modelAndView;

		}

		objectiveRepository.save(objective);
		ModelAndView andView = new ModelAndView("cadastro/objectiveregistration");

		Iterable<Objective> objectivesIt = objectiveRepository.findAll();
		andView.addObject("objectives", objectivesIt);

		andView.addObject("objectiveobj", new Objective());

		andView.addObject("problems", problemRepository.findAll());

		return andView;

	}

	// Adicionar objective vinculado ao problemId
	@PostMapping("/addObjectiveProblem/{idproblem}")
	public ModelAndView addObjectiveProblem(Objective objective, @PathVariable("idproblem") Long idproblem) {

		Problem problem = problemRepository.findById(idproblem).get();

		if (objective != null && objective.getDescription().isEmpty()) {

			ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");
			modelAndView.addObject("problemobj", problem);
			modelAndView.addObject("objectives", objectiveRepository.getObjectivePorProblem(idproblem));

			List<String> msg = new ArrayList<String>();
			if (objective.getDescription().isEmpty()) {
				msg.add("Description must be informed");
			}

			modelAndView.addObject("msg", msg);

			return modelAndView;

		}

		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");

		objective.setProblem(problem);

		objectiveRepository.save(objective);

		modelAndView.addObject("problemobj", problem);
		modelAndView.addObject("objectives", objectiveRepository.getObjectivePorProblem(idproblem));

		return modelAndView;

	}

	@PostMapping(value = "/salvarObjetivo")
	public ModelAndView salvarObjetivo(Long problemId, Objective objective) {

		Problem problem = problemRepository.findById(problemId).get();
		objective.setProblem(problem);
		if (!problem.getObjectives().contains(objective)) {
			problem.getObjectives().add(objective);
		}
		// objective.setDecisions(null);
		objectiveRepository.save(objective);
		problemRepository.save(problem);
		Iterable<Problem> problemsIt = problemRepository.findAll();

		ModelAndView andView = new ModelAndView("cadastro/problemregistration");
		andView.addObject("problems", problemsIt);
		andView.addObject("problemobj", problem);
		andView.addObject("objetivoObj", new Objective());
		return andView;

	}

	@PostMapping(value = "/salvarDecision")
	public ModelAndView salvarDecision(Long objectiveId, Decision decision) {

		Objective objective = objectiveRepository.findById(objectiveId).get();
		decision.setObjective(objective);
		if (!objective.getDecisions().contains(decision)) {
			objective.getDecisions().add(decision);
		}
		// objective.setDecisions(null);
//		objectiveRepository.save(objective);
		decisionRepository.save(decision);

		return initEditar(objective);

	}

	@PostMapping(value = "/salvarTradeoff")
	public ModelAndView salvarTradeoff(Long decisionId, Tradeoff tradeoff) {

		Decision decision = decisionRepository.findById(decisionId).get();
		tradeoff.setDecision(decision);
		if (!decision.getTradeoffs().contains(tradeoff)) {
			decision.getTradeoffs().add(tradeoff);
		}
		// objective.setDecisions(null);
	//	decisionRepository.save(decision);
		tradeoffRepository.save(tradeoff);

		return initEditar(decision.getObjective());

	}

	@GetMapping("/editarobjective/{idobjective}")
	public ModelAndView editar(@PathVariable("idobjective") Long idobjective) {
		Objective objective = objectiveRepository.findById(idobjective).get();
		return initEditar(objective);
	}

	@GetMapping("/editarTradeoff/{id}")
	public ModelAndView editarTradeoff(@PathVariable("id") Long id) {
		Tradeoff tradeoff = tradeoffRepository.findById(id).orElseThrow();
		Objective objetivo = tradeoff.getDecision().getObjective();
		ModelAndView modelAndView = initEditar(objetivo);
		modelAndView.addObject("decision", tradeoff.getDecision());
		modelAndView.addObject("tradeoff", tradeoff);
		return modelAndView;
	}

	@GetMapping("/editarDecision/{id}")
	public ModelAndView editarDecision(@PathVariable("id") Long id) {
		Decision d = decisionRepository.findById(id).orElseThrow();
		ModelAndView modelAndView = initEditar(d.getObjective());
		modelAndView.addObject("decision", d);
		return modelAndView;
	}

	private ModelAndView initEditar(Objective objective) {

		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");
		modelAndView.addObject("objectiveobj", objective);
		modelAndView.addObject("objectives",
				objectiveRepository.getObjectivePorProblem(objective.getProblem().getId()));

		modelAndView.addObject("decision", new Decision());
		modelAndView.addObject("tradeoff", new Tradeoff());
		return modelAndView;
	}

	@GetMapping("/removerobjective/{idobjective}")
	public ModelAndView removerobjective(@PathVariable("idobjective") Long idobjective) {

		objectiveRepository.deleteById(idobjective);

		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");
		modelAndView.addObject("objectives", objectiveRepository.findAll());
		modelAndView.addObject("objectiveobj", new Objective());
		modelAndView.addObject("problems", problemRepository.findAll());

		return modelAndView;

	}

	@GetMapping("/removerTradeoff/{id}")
	public ModelAndView removerTradeoff(@PathVariable("id") Long id) {
		Tradeoff tradeoff = tradeoffRepository.findById(id).orElseThrow();
		Objective objetivo = tradeoff.getDecision().getObjective();
		tradeoffRepository.deleteById(id);
		return initEditar(objetivo);
	}

	@GetMapping("/removerDecision/{id}")
	public ModelAndView removerDecision(@PathVariable("id") Long id) {
		Decision d = decisionRepository.findById(id).orElseThrow();
		decisionRepository.delete(d);
		return initEditar(d.getObjective());
	}
	
	@GetMapping(value = "/report_objective")
	public ModelAndView report() {

		ModelAndView modelAndView = new ModelAndView("cadastro/report_objective");

		Iterable<Objective> objectives = objectiveRepository.findAll();
		modelAndView.addObject("objectives", objectives);

		return modelAndView;

	}

	/**
	 * GET /api/v1/objectives : Get all objectives
	 * @return List of objectives
	 */
	@Operation(
		summary = "Get all objectives",
		responses = {
			@ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = BrowseAllObjectivesRes.class))
			})
		}
	)
	@GetMapping
	public ResponseEntity<List<BrowseAllObjectivesRes>> getAllObjectives() {
		return status(HttpStatus.OK).body(
			(List<BrowseAllObjectivesRes>)this.manageObjectiveUseCase.handle(new BrowseAllObjectivesReq())
		);
	}

	/**
	 * GET /api/v1/objectives/{entityInterestId} : Get all objectives by "Entity of Interest"
	 * @param entityInterestId
	 * @return List of objectives
	 */
	@Operation(
		summary = "Get all objectives by 'Entity of Interest'",
		responses = {
			@ApiResponse(responseCode = "200", description = "For successful fetch.", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = BrowseByEntityInterestIdObjectivesRes.class))
			})
		}
	)
	@GetMapping("/entity-interest/{entityInterestId}")
	public ResponseEntity<List<BrowseByEntityInterestIdObjectivesRes>> getAllObjectivesByEntityInterestId(@Parameter(description = "Entity of interest ID") @Valid @PathVariable Long entityInterestId) {
		Object response = this.manageObjectiveUseCase.handle(new BrowseByEntityInterestIdObjectivesReq(entityInterestId));
		if (response == null) return status(HttpStatus.NOT_FOUND).body(null);
		return status(HttpStatus.OK).body((List<BrowseByEntityInterestIdObjectivesRes>) response);
	}

	/**
	 * POST /api/v1/objectives : Add a objective into "Entity of Interest"
	 *
	 * @param request
	 * @return
	 */
	@PostMapping(produces = {"application/json"}, consumes = {"application/json"})
	public ResponseEntity<AddObjectiveRes> createObjective(@Parameter(description = "Objective of entity of interest") @Valid @RequestBody AddObjectiveReq request) {
		return status(HttpStatus.CREATED).body(
			(AddObjectiveRes) this.manageObjectiveUseCase.handle(request)
		);
	}

	/**
	 * PUT /api/v1/objectives/{id} : Delete an objective
	 *
	 * @param id
	 * @return {@link DeleteObjectiveRes}
	 */
	@Operation(
		summary = "Delete an objective",
		responses = {
			@ApiResponse(responseCode = "200", description = "For successful delete.", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = EditObjectiveRes.class))
			})
		}
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<DeleteObjectiveRes> deleteObjectiveResResponseEntity (@Parameter(description = "Objective ID") @Valid @PathVariable Long id) {
		return status(HttpStatus.OK).body(
			(DeleteObjectiveRes) this.manageObjectiveUseCase.handle(new DeleteObjectiveReq(id))
		);
	}
}
