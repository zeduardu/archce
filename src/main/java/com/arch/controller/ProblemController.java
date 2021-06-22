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
import com.arch.model.Problem;
import com.arch.model.Stakeholder;
import com.arch.repository.ConcernRepository;
import com.arch.repository.ProblemRepository;
import com.arch.repository.StakeholderRepository;

@Controller
public class ProblemController {

	@Autowired
	private StakeholderRepository stakeholderRepository;
	
	@Autowired
	private ConcernRepository concernRepository;
	
	@Autowired
	private ProblemRepository problemRepository;



		
	
	@RequestMapping(method = RequestMethod.GET, value = "**/problemregistration")
	public ModelAndView problem () {
		
				
		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
		
		
		modelAndView.addObject("problemobj", new Problem());

		Iterable<Problem> problemsIt = problemRepository.findAll();		
				
		modelAndView.addObject("problems", problemsIt);
		
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		
		return modelAndView;
		
	}
	
	//Adicionar problem vinculado ao stakeholder
	@PostMapping("**/addProblemStakeholder/{idstakeholder}")
	public ModelAndView addProblemStakeholder (Problem problem, @PathVariable("idstakeholder") Long idstakeholder) {
		
		Stakeholder stakeholder = stakeholderRepository.findById(idstakeholder).get();
		
		if (problem != null && problem.getTitle().isEmpty() 
				|| problem.getArea().isEmpty() || problem.getAspects().isEmpty() 
				|| problem.getRisks().isEmpty() || problem.getOportunities().isEmpty()
				|| problem.getConstraints().isEmpty()) {
			
			ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
			modelAndView.addObject("stakeholderobj", stakeholder);
			modelAndView.addObject("problemobj", problem);
			modelAndView.addObject("problems", problemRepository.getProblemPorStakeholder(idstakeholder));
			
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
		
		problem.setStakeholder(stakeholder);
		
		problemRepository.save(problem);
						
		modelAndView.addObject("stakeholderobj", stakeholder);
		modelAndView.addObject("problemobj", new Problem());
		modelAndView.addObject("problems", problemRepository.getProblemPorStakeholder(idstakeholder));
		
		return modelAndView;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarproblem")
	public ModelAndView salvarProblem(@Valid Problem problem, BindingResult bindingResult) {
		
		
		
		//problem.setStakeholder(stakeholderRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/problemregistration");
			
			Iterable<Problem> problemsIt = problemRepository.findAll();
			modelAndView.addObject("problems", problemsIt);
			modelAndView.addObject("problemobj", problem);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
			return modelAndView;
			
		}
		
		problemRepository.save(problem);
		ModelAndView andView = new ModelAndView("cadastro/problemregistration");
		
		Iterable<Problem> problemsIt = problemRepository.findAll();
		andView.addObject("problems", problemsIt);
				
		andView.addObject("problemobj", new Problem());
		
		andView.addObject("stakeholders", stakeholderRepository.findAll());
		
		return andView;
		
		
		
	}
	
	
	@GetMapping("/editarproblem/{idproblem}")
	public ModelAndView editar (@PathVariable("idproblem") Long idproblem) {
	    
	    
		 Problem problem = problemRepository.findById(idproblem).get();
		    
		 ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
		 modelAndView.addObject("problemobj", problem);
		 modelAndView.addObject("stakeholderobj",  problem.getStakeholder());  
		 modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		 modelAndView.addObject("problems", problemRepository.getProblemPorStakeholder(problem.getStakeholder().getId())); 
		    
		 return modelAndView;
		
		    
	 }
	
	@GetMapping("/removerproblem/{idproblem}")
	public ModelAndView removerproblem (@PathVariable("idproblem") Long idproblem) {
		
	//	Stakeholder stakeholder = problemRepository.findById(idproblem).get().getStakeholder();
		
	//	Problem problem = problemRepository.findById(idproblem).get();
		
		problemRepository.deleteById(idproblem);
				
		ModelAndView modelAndView = new ModelAndView("cadastro/problemregistration");
		modelAndView.addObject("problems", problemRepository.findAll());
		
		modelAndView.addObject("problemobj", new Problem());
		
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		
	// 2	modelAndView.addObject("problmeobj", new Problem());
	//	Problem problem = new Problem();
	//	modelAndView.addObject("problemobj", problem);
	//	modelAndView.addObject("stakeholderobj", stakeholder);
	//	modelAndView.addObject("problems", problemRepository.getProblemPorStakeholder(stakeholder.getId()));
	
				
		return modelAndView;
		
	}
	
	
}
 