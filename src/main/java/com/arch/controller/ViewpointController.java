package com.arch.controller;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import com.arch.model.Concern;
import com.arch.model.Viewpoint;
import com.arch.repository.ConcernRepository;
import com.arch.repository.ViewpointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewpointController {

	/**
	 * Pagina cadastro de viewpoint
	 */
	private static final String CADASTRO_VIEWPOINTREGISTRATION = "cadastro/viewpointregistration";

	@Autowired
	private ConcernRepository concernRepository;

	@Autowired
	private ViewpointRepository viewpointRepository;

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

}
