package com.arch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.StreamSupport.stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.arch.model.Concern;
import com.arch.model.Stakeholder;
import com.arch.model.Viewpoint;
import com.arch.repository.ConcernRepository;
import com.arch.repository.StakeholderRepository;

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
public class StakeholderController {

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@Autowired
	private ConcernRepository concernRepository;

	@Autowired
	private ReportUtil reportUtil;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastrostakeholder")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholderobj", new Stakeholder());

		Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
		modelAndView.addObject("stakeholders", stakeholdersIt);
		modelAndView.addObject("concerns", concernRepository.findAll());

		return modelAndView;

	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarstakeholder")
	public ModelAndView salvar(@Valid Stakeholder stakeholder, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> msg = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage())
					.collect(Collectors.toList());
			Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();

			ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
			modelAndView.addObject("stakeholders", stakeholdersIt);
			modelAndView.addObject("stakeholderobj", stakeholder);
			modelAndView.addObject("msg", msg);
			return modelAndView;

		}

		Iterable<Concern> allConcerns = concernRepository.findAll();
		for (Concern c : allConcerns) {
			if (c.getStakeholders() != null) {
				c.getStakeholders().remove(stakeholder);
			}
			if (stakeholder.getConcerns().contains(c)) {
				c.getStakeholders().add(stakeholder);
			}
		}

		stakeholderRepository.save(stakeholder);
		concernRepository.saveAll(allConcerns);

		return new ModelAndView("redirect:/cadastrostakeholder");

	}

	@GetMapping(value = "/listarstakeholder")
	public ModelAndView stakeholders() {
		ModelAndView andView = new ModelAndView("cadastro/cadastrostakeholder");
		Iterable<Stakeholder> stakeholdersIt = stakeholderRepository.findAll();
		andView.addObject("stakeholders", stakeholdersIt);
		andView.addObject("stakeholderobj", new Stakeholder());
		return andView;

	}

	@GetMapping(value = "/search")
	public ModelAndView search() {

		ModelAndView modelAndView = new ModelAndView("cadastro/search");
		modelAndView.addObject("stakeholderobj", new Stakeholder());

		return modelAndView;

	}

	@GetMapping(value = "/welcome")
	public ModelAndView welcome() {

		ModelAndView modelAndView = new ModelAndView("welcome");
		modelAndView.addObject("stakeholderobj", new Stakeholder());

		return modelAndView;

	}

	@GetMapping("/editarstakeholder/{idstakeholder}")
	public ModelAndView editar(@PathVariable("idstakeholder") Long idstakeholder) {

		Stakeholder stakeholder = stakeholderRepository.findById(idstakeholder).orElseThrow();
		Iterable<Stakeholder> sts = stakeholderRepository.findAll();
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholderobj", stakeholder).addObject("concerns", concernRepository.findAll());
		List<Stakeholder> outrosSts = stream(sts.spliterator(), false).filter(v -> !v.getId().equals(idstakeholder))
				.collect(Collectors.toList());
		modelAndView.addObject("stakeholders", outrosSts);
		return modelAndView;

	}

	@GetMapping("/removerstakeholder/{idstakeholder}")
	public ModelAndView excluir(@PathVariable("idstakeholder") Long idstakeholder) {

		Stakeholder st = stakeholderRepository.findById(idstakeholder).get();
		for (Concern c : st.getConcerns()) {
			if (c.getStakeholders() != null) {
				c.getStakeholders().remove(st);
			}

		}
		st.getConcerns().clear();

		stakeholderRepository.deleteById(idstakeholder);

		return new ModelAndView("redirect:/cadastrostakeholder");

	}

	@PostMapping("**/pesquisarstakeholder")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrostakeholder");
		modelAndView.addObject("stakeholders", stakeholderRepository.findStakeholderByName(nomepesquisa))
				.addObject("stakeholderobj", new Stakeholder());
		return modelAndView;
	}



	@GetMapping("**/pesquisarstakeholder")
	public void printPDF(@RequestParam("nomepesquisa") String nomepesquisa, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Stakeholder> stakeholders = new ArrayList<Stakeholder>();

		if (nomepesquisa != null && !nomepesquisa.isEmpty()) {

			stakeholders = stakeholderRepository.findStakeholderByName(nomepesquisa);

		} else {
			Iterable<Stakeholder> iterator = stakeholderRepository.findAll();
			for (Stakeholder stakeholder : iterator) {
				stakeholders.add(stakeholder);

			}
		}

		/* chamado do servico que faz a geracao do relatorio */

		byte[] pdf = reportUtil.generateReport(stakeholders, "stakeholder", request.getServletContext());

		/* tamanho da resposta */

		response.setContentLength(pdf.length);

		/* definir na resposta o tipo arquivo */

		response.setContentType("application/octet-stream");

		/* definir o cabeçalho da resposta */

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "report.pdf");

		response.setHeader(headerKey, headerValue);

		/* Finaliza a resposta para o navegador */

		response.getOutputStream().write(pdf);

	}
	
	@GetMapping(value = "/report_stakeholder")
	public ModelAndView report() {

		ModelAndView modelAndView = new ModelAndView("cadastro/report_stakeholder");

		Iterable<Stakeholder> stakeholders = stakeholderRepository.findAll();
		modelAndView.addObject("stakeholders", stakeholders);

		return modelAndView;

	}

}
