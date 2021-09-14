package com.arch.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.arch.model.Decision;
import com.arch.model.Objective;
import com.arch.model.Problem;
import com.arch.model.Tradeoff;
import com.arch.repository.DecisionRepository;
import com.arch.repository.ObjectiveRepository;
import com.arch.repository.ProblemRepository;
import com.arch.repository.TradeoffRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ObjectiveController {

	@Autowired
	private ObjectiveRepository objectiveRepository;

	@Autowired
	private DecisionRepository decisionRepository;

	@Autowired
	private TradeoffRepository tradeoffRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@RequestMapping(method = RequestMethod.GET, value = "**/objectiveregistration")
	public ModelAndView objectives() {

		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");

		modelAndView.addObject("objectiveobj", new Objective());

		Iterable<Objective> objectivesIt = objectiveRepository.findAll();

		modelAndView.addObject("objectives", objectivesIt);

		modelAndView.addObject("problems", problemRepository.findAll());

		return modelAndView;

		/*
		 * Optional<Problem> problem = problemRepository.findById(idproblem);
		 * 
		 * ModelAndView modelAndView = new
		 * ModelAndView("cadastro/objectiveregistration");
		 * modelAndView.addObject("problemobj", problem.get());
		 * modelAndView.addObject("objectives",
		 * objectiveRepository.getObjectivePorProblem(idproblem));
		 * 
		 * return modelAndView;
		 */

	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarobjective")
	public ModelAndView salvarObjective(@Valid Objective objective, BindingResult bindingResult) {

		// problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));

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

	// Adicionar objective vinculado ao problem
	@PostMapping("**/addObjectiveProblem/{idproblem}")
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
	
}
