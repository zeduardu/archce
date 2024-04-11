package dev.arch420x0.archce.controller;

import java.util.ArrayList;
import java.util.List;

import dev.arch420x0.archce.repository.DecisionRepository;
import dev.arch420x0.archce.repository.TradeoffRepository;
import jakarta.validation.Valid;

import dev.arch420x0.archce.model.Tradeoff;

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
public class TradeoffController {

	@Autowired
	private DecisionRepository decisionRepository;

	@Autowired
	private TradeoffRepository tradeoffRepository;

	// @GetMapping("/tradeoff/{iddecision}")

	@RequestMapping(method = RequestMethod.GET, value = "/tradeoffregistration")
	public ModelAndView tradeoff() {

		ModelAndView modelAndView = new ModelAndView("cadastro/tradeoffregistration");

		modelAndView.addObject("tradeoffobj", new Tradeoff());

		Iterable<Tradeoff> tradeoffsIt = tradeoffRepository.findAll();

		modelAndView.addObject("tradeoff", tradeoffsIt);

		modelAndView.addObject("decisions", decisionRepository.findAll());

		return modelAndView;

		/*
		 * Optional<Decision> decision = decisionRepository.findById(iddecision);
		 * 
		 * ModelAndView modelAndView = new
		 * ModelAndView("cadastro/tradeoffregistration");
		 * modelAndView.addObject("decisionobj", decision.get());
		 * modelAndView.addObject("tradeoffs",
		 * tradeoffRepository.getTradeoffPorDecision(iddecision));
		 * 
		 * return modelAndView;
		 */

	}

	// @RequestMapping(method=RequestMethod.POST, value="**/salvartradeoff")
	@PostMapping("salvartradeoff")
	public ModelAndView salvarTradeoff(@Valid Tradeoff tradeoff, BindingResult bindingResult) {

		// problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/tradeoffregistration");

			Iterable<Tradeoff> tradeoffsIt = tradeoffRepository.findAll();
			modelAndView.addObject("tradeoffs", tradeoffsIt);
			modelAndView.addObject("tradeoffobj", tradeoff);

			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); // vem das anotacoes @NotEmpty e outras
			}

			modelAndView.addObject("msg", msg);
			modelAndView.addObject("decisions", decisionRepository.findAll());
			return modelAndView;

		}

		tradeoffRepository.save(tradeoff);
		ModelAndView andView = new ModelAndView("cadastro/tradeoffregistration");

		Iterable<Tradeoff> tradeoffsIt = tradeoffRepository.findAll();
		andView.addObject("tradeoffs", tradeoffsIt);

		andView.addObject("tradeoffobj", new Tradeoff());

		andView.addObject("decisions", decisionRepository.findAll());

		return andView;

	}



	@GetMapping("/editartradeoff/{idtradeoff}")
	public ModelAndView editar(@PathVariable("idtradeoff") Long idtradeoff) {

		Tradeoff tradeoff = tradeoffRepository.findById(idtradeoff).get();

		ModelAndView modelAndView = new ModelAndView("cadastro/tradeoffregistration");
		modelAndView.addObject("tradeoffobj", tradeoff);
		modelAndView.addObject("decisionobj", tradeoff.getDecision());
		modelAndView.addObject("decisions", decisionRepository.findAll());
		modelAndView.addObject("tradeoffs", tradeoffRepository.getTradeoffPorDecision(tradeoff.getDecision().getId()));

		return modelAndView;

	}

	@GetMapping("/removertradeoff/{idtradeoff}")
	public ModelAndView removertradeoff(@PathVariable("idtradeoff") Long idtradeoff) {

		tradeoffRepository.deleteById(idtradeoff);

		ModelAndView modelAndView = new ModelAndView("cadastro/tradeoffregistration");
		modelAndView.addObject("tradeoffs", tradeoffRepository.findAll());
		modelAndView.addObject("tradeoffobj", new Tradeoff());

		modelAndView.addObject("decisions", decisionRepository.findAll());

		return modelAndView;



	}
	
	@GetMapping(value = "/report_tradeoff")
	public ModelAndView report() {

		ModelAndView modelAndView = new ModelAndView("cadastro/report_tradeoff");

		Iterable<Tradeoff> tradeoffs = tradeoffRepository.findAll();
		modelAndView.addObject("tradeoffs", tradeoffs);

		return modelAndView;

	}

}
