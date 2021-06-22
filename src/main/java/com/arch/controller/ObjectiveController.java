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

import com.arch.model.Concern;
import com.arch.model.Objective;
import com.arch.model.Problem;
import com.arch.repository.ConcernRepository;
import com.arch.repository.ObjectiveRepository;
import com.arch.repository.ProblemRepository;
import com.arch.repository.StakeholderRepository;
import com.arch.repository.ViewpointRepository;

@Controller
public class ObjectiveController {

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

	
	
	@RequestMapping(method = RequestMethod.GET, value = "**/objectiveregistration")
	public ModelAndView objectives () {
		
				
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
	
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarobjective")
	public ModelAndView salvarObjective(@Valid Objective objective, BindingResult bindingResult) {
		
		
		
		//problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/objectiveregistration");
			
			Iterable<Objective> objectivesIt = objectiveRepository.findAll();
			modelAndView.addObject("objectives", objectivesIt);
			modelAndView.addObject("objectiveobj", objective);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
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
	
	
	//Adicionar objective vinculado ao problem
	@PostMapping("**/addObjectiveProblem/{idproblem}")
	public ModelAndView addObjectiveProblem (Objective objective, @PathVariable("idproblem") Long idproblem) {
		
		
		Problem problem = problemRepository.findById(idproblem).get();
		
		if (objective != null && objective.getDescription().isEmpty() ) {
			
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
	
	
	
	
	@GetMapping("/editarobjective/{idobjective}")
	public ModelAndView editar (@PathVariable("idobjective") Long idobjective) {
	    
	    
		 Objective objective = objectiveRepository.findById(idobjective).get();
		    
		 ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");
		 modelAndView.addObject("objectiveobj", objective);
		 modelAndView.addObject("problemobj",  objective.getProblem());  
		 modelAndView.addObject("problems", problemRepository.findAll());
		 modelAndView.addObject("objectives", objectiveRepository.getObjectivePorProblem(objective.getProblem().getId())); 
		 
		  
		 
		 return modelAndView;
		
		    
	 }
	
	
	
	@GetMapping("/removerobjective/{idobjective}")
	public ModelAndView removerobjective (@PathVariable("idobjective") Long idobjective) {
	 
		
	//	Problem problem = objectiveRepository.findById(idobjective).get().getProblem();
		
		objectiveRepository.deleteById(idobjective);
					
		ModelAndView modelAndView = new ModelAndView("cadastro/objectiveregistration");
		modelAndView.addObject("objectives", objectiveRepository.findAll());
		modelAndView.addObject("objectiveobj", new Objective());
		
		modelAndView.addObject("problems", problemRepository.findAll());
		
				
		return modelAndView;
		
		
		/*
		 * Problem problem =
		 * objectiveRepository.findById(idobjective).get().getProblem();
		 * 
		 * objectiveRepository.deleteById(idobjective);
		 * 
		 * ModelAndView modelAndView = new
		 * ModelAndView("cadastro/objectiveregistration");
		 * modelAndView.addObject("problemobj", problem);
		 * modelAndView.addObject("objectives",
		 * objectiveRepository.getObjectivePorProblem(problem.getId()));
		 * 
		 * 
		 * return modelAndView;
		 */		
	
	}
	
	
}
 