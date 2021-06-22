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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.arch.model.Stakeholder;
import com.arch.repository.ConcernRepository;
import com.arch.repository.ItemRelatorio;
import com.arch.repository.ReportRepository;
import com.arch.repository.StakeholderRepository;

@RestController
@RequestMapping("/api")
public class ApiReportController {

	@Autowired
	private StakeholderRepository stakeholderRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/report")
	public List<Stakeholder> report() {
		List<Stakeholder> stakeholdersIt = stakeholderRepository.findTodos();
		return stakeholdersIt;
	}

}
