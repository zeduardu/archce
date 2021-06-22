package com.arch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.arch.repository.ConcernRepository;
import com.arch.repository.StakeholderRepository;

@Controller
public class ConcernController {
	
	@Autowired
	private StakeholderRepository stakeholderRepository;
	
	@Autowired
	private ConcernRepository concernRepository;
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "**/cadastroconcern")
	public ModelAndView concerns () {
		
		//Optional<Stakeholder> stakeholder = stakeholderRepository.findById(idstakeholder);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		
		//Concern concern = new Concern();
		modelAndView.addObject("concernobj", new Concern());
		
		Iterable<Concern> concernsIt = concernRepository.findAll();		
		
		modelAndView.addObject("concerns", concernsIt);
		
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		
		//modelAndView.addObject("stakeholderobj", stakeholder.get());
		//modelAndView.addObject("concerns", concernRepository.getConcernsPorStakeholder(idstakeholder));
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarconcern")
	public ModelAndView salvarConcern(@Valid Concern concern, BindingResult bindingResult) {
		
		
		
		//problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/cadastroconcern");
			
			Iterable<Concern> concernsIt = concernRepository.findAll();
			modelAndView.addObject("concerns", concernsIt);
			modelAndView.addObject("concernobj", concern);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
			return modelAndView;
			
		}
		
		concernRepository.save(concern);
		ModelAndView andView = new ModelAndView("cadastro/cadastroconcern");
		
		Iterable<Concern> concernsIt = concernRepository.findAll();
		andView.addObject("concerns", concernsIt);
				
		andView.addObject("concernobj", new Concern());
		
		andView.addObject("stakeholders", stakeholderRepository.findAll());
		
		return andView;
		
		
		
	}
	
	

	
/**
	
	@GetMapping("/editarconcern/{idconcern}")
	public ModelAndView editar (@PathVariable("idconcern") Long idconcern) {
		
		Optional<Concern> concernOptional = concernRepository.findById(idconcern);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		Concern concern = concernOptional.get(); 
		modelAndView.addObject("concernobj", concern);
		return modelAndView;
		
	}	
	
*/	
	@GetMapping("/editarconcern/{idconcern}")
		public ModelAndView editar (@PathVariable("idconcern") Long idconcern) {
			    
		Concern concern = concernRepository.findById(idconcern).get();
			    
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		modelAndView.addObject("concernobj", concern);
		modelAndView.addObject("stakeholderobj",  concern.getStakeholders());  
	    modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
	    
	    List <Concern> concerns = concern.getStakeholders().stream().flatMap((Stakeholder s ) -> s.getConcerns().stream()).collect(Collectors.toList());  
	    
	    modelAndView.addObject("concerns", concerns);
	    
	      
			    
		return modelAndView;
		
		//Concern concern = concernRepository.findById(idconcern).get();
	    
		//ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		//modelAndView.addObject("concernobj", concern);
		//modelAndView.addObject("stakeholderobj",  concern.getStakeholder());  
		//modelAndView.addObject("concerns", concernRepository.getConcernsPorStakeholder(concern.getStakeholder().getId())); 
			    
		//return modelAndView;
		
	}
	
	
	@GetMapping("/removerconcern/{idconcern}")
	public ModelAndView removerconcern (@PathVariable("idconcern") Long idconcern) {
		
	//	Stakeholder stakeholder = concernRepository.findById(idconcern).get().getStakeholder();
		
	//	Concern concern = concernRepository.findById(idconcern).get();
		
		concernRepository.deleteById(idconcern);
				
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		//Concern concern = new Concern();
		
		modelAndView.addObject("concerns", concernRepository.findAll());
		
		modelAndView.addObject("concernobj", new Concern());
		
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		
		//modelAndView.addObject("concernobj", concern);
		//modelAndView.addObject("stakeholderobj", stakeholder);
		//modelAndView.addObject("concerns", concernRepository.getConcernsPorStakeholder(stakeholder.getId()));
	
		
				
		return modelAndView;
		
	}
	
	
	@PostMapping("**/pesquisarconcern")
	public ModelAndView pesquisarConcern(@RequestParam("concernpesquisa") String description) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroconcern");
		modelAndView.addObject("concerns", concernRepository.findConcernByName(description));
		modelAndView.addObject("concernobj", new Concern());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/listarconcern")
	public ModelAndView listarConcerns () {
		ModelAndView andView = new ModelAndView("cadastro/cadastroconcern");
		Iterable<Concern> concernsIt = concernRepository.findAll();
		andView.addObject("concerns", concernsIt);
		andView.addObject("concernobj", new Concern());
		return andView;
		
	}

	
	
}
