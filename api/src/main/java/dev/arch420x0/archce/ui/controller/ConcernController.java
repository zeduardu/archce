package dev.arch420x0.archce.ui.controller;

import java.util.List;
import java.util.stream.Collectors;

import dev.arch420x0.archce.persistence.repositories.ConcernRepository;
import dev.arch420x0.archce.persistence.repositories.StakeholderRepository;
import jakarta.validation.Valid;

import dev.arch420x0.archce.domain.entities.Concern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConcernController {

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@Autowired
	private ConcernRepository concernRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastroconcern")
	public ModelAndView concerns() {

		Iterable<Concern> concernsIt = concernRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		modelAndView.addObject("concernobj", new Concern());
		modelAndView.addObject("concerns", concernsIt);
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());

		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/salvarconcern")
	public ModelAndView salvarConcern(@Valid Concern concern, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			List<String> msg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).sorted()
					.collect(Collectors.toList());

			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
			modelAndView.addObject("concerns", concernRepository.findAll());
			modelAndView.addObject("concernobj", concern);
			modelAndView.addObject("msg", msg);
			return modelAndView;

		}
		concernRepository.save(concern);
		return new ModelAndView("redirect:/cadastroconcern");
	}

	@GetMapping("/editarconcern/{idconcern}")
	public ModelAndView editar(@PathVariable("idconcern") Long idconcern) {

		Concern concern = concernRepository.findById(idconcern).get();
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		modelAndView.addObject("concernobj", concern);
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/removerconcern/{idconcern}")
	public ModelAndView removerconcern(@PathVariable("idconcern") Long idconcern) {
		concernRepository.deleteById(idconcern);
		return new ModelAndView("redirect:/cadastroconcern");
	}

	@PostMapping("/pesquisarconcern")
	public ModelAndView pesquisarConcern(@RequestParam("concernpesquisa") String description) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		modelAndView.addObject("concerns", concernRepository.findConcernByName(description));
		modelAndView.addObject("concernrobj", new Concern());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listarconcern")
	public ModelAndView listarConcerns() {
		ModelAndView andView = new ModelAndView("cadastro/cadastroconcern");
		andView.addObject("concerns", concernRepository.findAll());
		andView.addObject("concernobj", new Concern());
		return andView;
	}

}
