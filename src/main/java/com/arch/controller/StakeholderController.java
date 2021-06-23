package com.arch.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.arch.model.Stakeholder;
import com.arch.repository.ConcernRepository;
import com.arch.repository.ItemRelatorio;
import com.arch.repository.ReportRepository;
import com.arch.repository.StakeholderRepository;

@Controller
public class StakeholderController {

	@Autowired
	private StakeholderRepository stakeholderRepository;
	
	@Autowired
	private ConcernRepository concernRepository;
	
	@Autowired
	private ReportRepository reportRepository;
	
	

/**	antigo inicio
	@RequestMapping(method=RequestMethod.GET, value="/cadastrostakeholder")
	public String inicio () {
		return "cadastro/cadastrostakeholder";
		
	}
	**/
	
	
	

	@RequestMapping(method=RequestMethod.GET, value="/cadastrostakeholder")
	public ModelAndView inicio () {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholderobj", new Stakeholder());
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
		Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
		modelAndView.addObject("stakeholders", stakeholdersIt);
		
		return modelAndView;
		 
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/salvarstakeholder")
	public ModelAndView salvar(@Valid Stakeholder stakeholder, BindingResult bindingResult) {
		
		
		
	//	stakeholder.setConcerns(concernRepository.getConcernsPorStakeholder(stakeholder.getId()));
		
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView ("cadastro/cadastrostakeholder");
			
			Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
			modelAndView.addObject("stakeholders", stakeholdersIt);
			modelAndView.addObject("stakeholderobj", stakeholder);
			
			List<String> msg = new ArrayList<String>();
			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage()); //vem das anotacoes @NotEmpty e outras
			}
			
			modelAndView.addObject("msg", msg);
			return modelAndView;
			
		}
		
		stakeholderRepository.save(stakeholder);
		ModelAndView andView = new ModelAndView("cadastro/cadastrostakeholder");
		Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
		andView.addObject("stakeholders", stakeholdersIt);
		
		andView.addObject("stakeholderobj", new Stakeholder());
		
		return andView;
		
		
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/listarstakeholder")
	public ModelAndView stakeholders () {
		ModelAndView andView = new ModelAndView("cadastro/cadastrostakeholder");
		Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
		andView.addObject("stakeholders", stakeholdersIt);
		andView.addObject("stakeholderobj", new Stakeholder());
		return andView;
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public ModelAndView search () {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/search");
		modelAndView.addObject("stakeholderobj", new Stakeholder());
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											
		return modelAndView;
		 
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/welcome")
	public ModelAndView welcome () {
		
		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("stakeholderobj", new Stakeholder());
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																											
		return modelAndView;
		 
	}
	
	
	
	@GetMapping("/editarstakeholder/{idstakeholder}")
	public ModelAndView editar (@PathVariable("idstakeholder") Long idstakeholder) {
		
		Optional<Stakeholder> stakeholderOptional = stakeholderRepository.findById(idstakeholder);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		Stakeholder stakeholder = stakeholderOptional.get(); 
		modelAndView.addObject("stakeholderobj", stakeholder);
		return modelAndView;
		
	}
	
	@GetMapping("/removerstakeholder/{idstakeholder}")
	public ModelAndView excluir (@PathVariable("idstakeholder") Long idstakeholder) {
		
		stakeholderRepository.deleteById(idstakeholder);
				
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholders", stakeholderRepository.findAll());
		modelAndView.addObject("stakeholderobj", new Stakeholder());
		return modelAndView;
		
	}
	
	@PostMapping("**/pesquisarstakeholder")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholders", stakeholderRepository.findStakeholderByName(nomepesquisa));
		modelAndView.addObject("stakeholderobj", new Stakeholder());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/report")
	  public ModelAndView report () {
	    
	    ModelAndView modelAndView = new ModelAndView("cadastro/report");

		  List<Stakeholder> stakeholdersIt = stakeholderRepository.findTodos();
		modelAndView.addObject("stakeholders", stakeholdersIt);

	    return modelAndView;
	     
	}
	 
	

	
		
	
}
 