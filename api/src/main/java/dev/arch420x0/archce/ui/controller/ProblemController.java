package dev.arch420x0.archce.ui.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import dev.arch420x0.archce.persistence.repositories.ObjectiveRepository;
import dev.arch420x0.archce.persistence.repositories.ProblemRepository;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.arch420x0.archce.domain.entities.Objective;
import dev.arch420x0.archce.domain.entities.Problem;
import dev.arch420x0.archce.domain.entities.Stakeholder;

@Controller
public class ProblemController {

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private ObjectiveRepository objectiveRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/problemregistration")
	public ModelAndView problem() {

		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");

		Iterable<Problem> problemsIt = problemRepository.findAll();

		modelAndView.addObject("problemobj", new Problem());
		modelAndView.addObject("objetivoObj", new Objective());
		modelAndView.addObject("problems", problemsIt);
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());

		return modelAndView;

	}

	// Adicionar problem vinculado ao stakeholder
	@PostMapping("/addProblemStakeholder/{idstakeholder}")
	public ModelAndView addProblemStakeholder(Problem problem, @PathVariable("idstakeholder") Long idstakeholder) {

		Stakeholder stakeholder = stakeholderRepository.findById(idstakeholder).get();

		if (problem != null && problem.getTitle().isEmpty() || problem.getArea().isEmpty()
				|| problem.getAspects().isEmpty() || problem.getRisks().isEmpty() || problem.getOportunities().isEmpty()
				|| problem.getConstraints().isEmpty()) {

			ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
			modelAndView.addObject("stakeholderobj", stakeholder);
			modelAndView.addObject("problemobj", problem);

			List<String> msg = new ArrayList<String>();
			if (problem.getTitle().isEmpty()) {
				msg.add("Title must be informed");
			}

			if (problem.getArea() == null) {
				msg.add("Area must be informed");
			}

			modelAndView.addObject("msg", msg);

			return modelAndView;

		}

		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");

		problemRepository.save(problem);

		modelAndView.addObject("stakeholderobj", stakeholder);
		modelAndView.addObject("problemobj", new Problem());
		modelAndView.addObject("objetivoObj", new Objective());

		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarproblem")
	public ModelAndView salvarProblem(@Valid Problem problem, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			Iterable<Problem> problemsIt = problemRepository.findAll();
			List<String> msg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());

			ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
			modelAndView.addObject("problems", problemsIt);
			modelAndView.addObject("problemobj", problem);
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
			return modelAndView;

		}
		Iterable<Stakeholder> stakeholders = stakeholderRepository.findAll();
		for (Stakeholder st : stakeholders) {
			if (st.getProblems() != null) {
				st.getProblems().remove(problem);
			}
			if (problem.getStakeholders().contains(st)) {
				st.getProblems().add(problem);
			}
		}

		problemRepository.save(problem);
		stakeholderRepository.saveAll(stakeholders);

		return new ModelAndView("redirect:/problemregistration");

	}

	@GetMapping("/editarproblem/{idproblem}")
	public ModelAndView editar(@PathVariable("idproblem") Long idproblem) {

		Problem problem = problemRepository.findById(idproblem).get();
		Iterable<Problem> problemsIt = problemRepository.findAll();

		List<Problem> outrosP = StreamSupport.stream(problemsIt.spliterator(), false)
				.filter(v -> !v.getId().equals(idproblem)).collect(Collectors.toList());
		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
		modelAndView.addObject("problemobj", problem);
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		modelAndView.addObject("objetivoObj", new Objective());
		modelAndView.addObject("problems", outrosP);
		return modelAndView;

	}

	@GetMapping("/removerproblem/{idproblem}")
	public ModelAndView removerproblem(@PathVariable("idproblem") Long idproblem) {

		problemRepository.deleteById(idproblem);

		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
		modelAndView.addObject("problems", problemRepository.findAll());
		modelAndView.addObject("problemobj", new Problem());
		modelAndView.addObject("objetivoObj", new Objective());
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());

		return modelAndView;

	}

	@GetMapping("/removerObjetivo/{id}")
	public ModelAndView removerObjetivo(@PathVariable("id") Long id) {
		Long idproblem = objectiveRepository.findById(id).orElseThrow().getProblem().getId();
		objectiveRepository.deleteById(id);
		return editar(idproblem);
	}

	@GetMapping("/editarObjetivo/{id}")
	public ModelAndView editeObjetivo(@PathVariable("id") Long id) {

		Objective objetivo = objectiveRepository.findById(id).orElseThrow();
		ModelAndView modelAndView = editar(objetivo.getProblem().getId());
		modelAndView.addObject("objetivoObj", objetivo);
		return modelAndView;

	}

	@GetMapping(value = "/report_problem")
	public ModelAndView report() {

		ModelAndView modelAndView = new ModelAndView("cadastro/report_problem");

		Iterable<Problem> problems = problemRepository.findAll();
		modelAndView.addObject("problems", problems);

		return modelAndView;

	}
}