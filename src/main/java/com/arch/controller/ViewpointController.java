package com.arch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.arch.model.Concern;
import com.arch.model.Stakeholder;
import com.arch.model.Viewpoint;
import com.arch.repository.ConcernRepository;
import com.arch.repository.StakeholderRepository;
import com.arch.repository.ViewpointRepository;

@Controller
public class ViewpointController {

	@Autowired
	private StakeholderRepository stakeholderRepository;
	
	@Autowired
	private ConcernRepository concernRepository;
	
	@Autowired
	private ViewpointRepository viewpointRepository;

	
	@RequestMapping(method=RequestMethod.GET, value="/viewpointregistration")
	public ModelAndView inicio () {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/viewpointregistration");
		modelAndView.addObject("viewpointobj", new Viewpoint());
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
		Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
		modelAndView.addObject("viewpoints", viewpointsIt);
		
		modelAndView.addObject("concerns", concernRepository.findAll());
		
		return modelAndView;
		 
	}
	
	
	@GetMapping("/view/{idconcern}")
	public ModelAndView views (@PathVariable("idconcern") Long idconcern) {
		
		Optional<Concern> concern = concernRepository.findById(idconcern);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/viewregistration");
		modelAndView.addObject("concernobj", concern.get());
		modelAndView.addObject("views", viewpointRepository.getViewPorConcern(idconcern));
		
		return modelAndView;
													
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarviewpoint")
	public ModelAndView salvarViewpoint(@Valid Viewpoint viewpoint, BindingResult bindingResult) {
		
		
		
		//problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/viewpointregistration");
			
			Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
			modelAndView.addObject("viewpoints", viewpointsIt);
			modelAndView.addObject("viewpointobj", viewpoint);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("concerns", concernRepository.findAll());
			return modelAndView;
			
		}
		
		viewpointRepository.save(viewpoint);
		ModelAndView andView = new ModelAndView("cadastro/viewpointregistration");
		
		Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
		andView.addObject("viewpoints", viewpointsIt);
				
		andView.addObject("viewpointobj", new Viewpoint());
		
		andView.addObject("concerns", concernRepository.findAll());
		
		return andView;
		
		
		
	}
	
	
	
	//Adicionar view vinculado ao concern
	@PostMapping("**/addViewConcern/{idconcern}")
	public ModelAndView addViewConcern (Viewpoint view, @PathVariable("idconcern") Long idconcern) {
		
		
		Concern concern = concernRepository.findById(idconcern).get();
		
		if (view != null && view.getRationale().isEmpty()  
				|| view.getModel().isEmpty() || view.getConventions().isEmpty() || view.getSource().isEmpty() ) {
			
			ModelAndView modelAndView = new ModelAndView("cadastro/viewregistration");
			modelAndView.addObject("concernobj", concern);
			modelAndView.addObject("views", viewpointRepository.getViewPorConcern(idconcern));
			
			List<String> msg = new ArrayList<String>();
			if (view.getRationale().isEmpty()) {
				msg.add("Rationale must be informed");			
			}
			
			if (view.getModel().isEmpty()) {
				msg.add("Model must be informed");			
			}
			
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
			
		}
		
		ModelAndView modelAndView = new ModelAndView("cadastro/viewregistration");
		
		view.setConcern(concern);
		
		viewpointRepository.save(view);
						
		modelAndView.addObject("concernobj", concern);
		modelAndView.addObject("views", viewpointRepository.getViewPorConcern(idconcern));
		
		return modelAndView;
		
	}
	
	
	@GetMapping("/editarviewpoint/{idview}")
	public ModelAndView editar (@PathVariable("idview") Long idview) {
		    
	Viewpoint viewpoint = viewpointRepository.findById(idview).get();
		    
	ModelAndView modelAndView = new ModelAndView("cadastro/viewpointregistration");
	modelAndView.addObject("viewpointobj", viewpoint);
	modelAndView.addObject("concernobj",  viewpoint.getConcern());  
    modelAndView.addObject("concerns", concernRepository.findAll());
	modelAndView.addObject("viewpoints", viewpointRepository.getViewPorConcern(viewpoint.getConcern().getId())); 
		    
	return modelAndView;
	
	//Concern concern = concernRepository.findById(idconcern).get();
    
	//ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
	//modelAndView.addObject("concernobj", concern);
	//modelAndView.addObject("stakeholderobj",  concern.getStakeholder());  
	//modelAndView.addObject("concerns", concernRepository.getConcernsPorStakeholder(concern.getStakeholder().getId())); 
		    
	//return modelAndView;
	
	}
	
	@GetMapping("/removerviewpoint/{idview}")
	public ModelAndView removerview (@PathVariable("idview") Long idview) {
	 
		
	//	Concern concern = viewpointRepository.findById(idview).get().getConcern();
		
		viewpointRepository.deleteById(idview);
				
		ModelAndView modelAndView = new ModelAndView("cadastro/viewpointregistration");
		
		modelAndView.addObject("viewpoints", viewpointRepository.findAll());

		modelAndView.addObject("viewpointobj", new Viewpoint());

		modelAndView.addObject("concerns", concernRepository.findAll());

			
				
		return modelAndView;
		
	
	}
	
	@PostMapping("**/pesquisarviewpoint")
	public ModelAndView pesquisarViewpoint(@RequestParam("viewpointpesquisa") String rationale) {
		ModelAndView modelAndView = new ModelAndView("cadastro/viewpointregistration");
		modelAndView.addObject("viewpoints", viewpointRepository.findViewpointByName(rationale));
		modelAndView.addObject("viewpointobj", new Concern());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listarviewpoint")
	public ModelAndView listarViewpoints () {
		ModelAndView andView = new ModelAndView("cadastro/viewpointregistration");
		Iterable<Viewpoint> viewpointsIt = viewpointRepository.findAll();
		andView.addObject("viewpoints", viewpointsIt);
		andView.addObject("viewpointsobj", new Viewpoint());
		return andView;		
	}
	
	
}
 