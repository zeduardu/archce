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
import org.springframework.web.servlet.ModelAndView;

import com.arch.model.Decision;
import com.arch.model.Objective;
import com.arch.repository.ConcernRepository;
import com.arch.repository.DecisionRepository;
import com.arch.repository.ObjectiveRepository;
import com.arch.repository.ProblemRepository;
import com.arch.repository.StakeholderRepository;
import com.arch.repository.ViewpointRepository;

@Controller
public class DecisionController {

	@Autowired
	private StakeholderRepository stakeholderRepository;
	
	@Autowired
	private ConcernRepository concernRepository;
	
	@Autowired
	private ViewpointRepository viewRepository;
	
	@Autowired
	private ObjectiveRepository objectiveRepository;
	
	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private DecisionRepository decisionRepository;
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "**/decisionregistration")
	public ModelAndView decisions () {
		
		
		ModelAndView modelAndView = new ModelAndView("cadastro/decisionregistration");
		
		
		modelAndView.addObject("decisionobj", new Decision());

		Iterable<Decision> decisionsIt = decisionRepository.findAll();		
				
		modelAndView.addObject("decision", decisionsIt);
		
		modelAndView.addObject("objectives", objectiveRepository.findAll());
		
		return modelAndView;

		
		/*
		 * Optional<Objective> objective = objectiveRepository.findById(idobjective);
		 * 
		 * ModelAndView modelAndView = new
		 * ModelAndView("cadastro/decisionregistration");
		 * modelAndView.addObject("objectiveobj", objective.get());
		 * modelAndView.addObject("decisions",
		 * decisionRepository.getDecisionPorObjective(idobjective));
		 * 
		 * return modelAndView;
		 */
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvardecision")
	public ModelAndView salvarDecision(@Valid Decision decision, BindingResult bindingResult) {
		
		
		
		//problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/decisionregistration");
			
			Iterable<Decision> decisionsIt = decisionRepository.findAll();
			modelAndView.addObject("decisions", decisionsIt);
			modelAndView.addObject("decisionobj", decision);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("objectives", objectiveRepository.findAll());
			return modelAndView;
			
		}
		
		decisionRepository.save(decision);
		ModelAndView andView = new ModelAndView("cadastro/decisionregistration");
		
		Iterable<Decision> decisionsIt = decisionRepository.findAll();
		andView.addObject("decisions", decisionsIt);
				
		andView.addObject("decisionobj", new Decision());
		
		andView.addObject("objectives", objectiveRepository.findAll());
		
		return andView;
		
		
		
	}
	
	
	
	//Adicionar decision vinculado ao objective
	@PostMapping("**/addDecisionObjective/{idobjective}")
	public ModelAndView addDecisionObjective (Decision decision, @PathVariable("idobjective") Long idobjective) {
		
		
		Objective objective = objectiveRepository.findById(idobjective).get();
		
		if (decision != null && decision.getDescription().isEmpty() ||
				decision.getSolution().isEmpty() || decision.getRationale().isEmpty()) {
			
			ModelAndView modelAndView = new ModelAndView("cadastro/decisionregistration");
			modelAndView.addObject("objectiveobj", objective);
			modelAndView.addObject("decisions", decisionRepository.getDecisionPorObjective(idobjective));
			
			List<String> msg = new ArrayList<String>();
			if (decision.getDescription().isEmpty()) {
				msg.add("Description must be informed");			
			}
			
						
			modelAndView.addObject("msg", msg);
			
			return modelAndView;
			
		}
		
		ModelAndView modelAndView = new ModelAndView("cadastro/decisionregistration");
		
		decision.setObjective(objective);
		
		decisionRepository.save(decision);
						
		modelAndView.addObject("objectiveobj", objective);
		modelAndView.addObject("decisions", decisionRepository.getDecisionPorObjective(idobjective));
		
		return modelAndView;
		
	}
	
	
	@GetMapping("/editardecision/{iddecision}")
	public ModelAndView editar (@PathVariable("iddecision") Long iddecision) {
	    
	    
		 Decision decision = decisionRepository.findById(iddecision).get();
		    
		 ModelAndView modelAndView = new ModelAndView("cadastro/decisionregistration");
		 modelAndView.addObject("decisionobj", decision);
		 modelAndView.addObject("objectiveobj",  decision.getObjective());  
		 modelAndView.addObject("objectives", objectiveRepository.findAll());
		 modelAndView.addObject("decisions", decisionRepository.getDecisionPorObjective(decision.getObjective().getId())); 
		 
		  
		 
		 return modelAndView;
		
		    
	 }
	
	@GetMapping("/removerdecision/{iddecision}")
	public ModelAndView removerdecision (@PathVariable("iddecision") Long iddecision) {
	 
		
	//	Objective objective = decisionRepository.findById(iddecision).get().getObjective();
		
		decisionRepository.deleteById(iddecision);
				
			
		ModelAndView modelAndView = new ModelAndView("cadastro/decisionregistration");
		modelAndView.addObject("decisions", decisionRepository.findAll());
		modelAndView.addObject("decisionobj", new Decision());
		
		modelAndView.addObject("objectives", objectiveRepository.findAll());
		
				
		return modelAndView;
		
	
	}
	
	
}
 