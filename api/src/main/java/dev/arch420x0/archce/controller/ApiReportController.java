package dev.arch420x0.archce.controller;

import dev.arch420x0.archce.model.Viewpoint;
import dev.arch420x0.archce.repository.ViewpointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiReportController {

	@Autowired
	private ViewpointRepository viewpointRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/report")
	public Iterable<Viewpoint> report() {
		Iterable<Viewpoint> viewpoints = viewpointRepository.findAll();
		return viewpoints;
	}

}
