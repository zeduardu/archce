package dev.arch420x0.archce.ui.controller;

import java.util.Optional;

import dev.arch420x0.archce.domain.entities.Metric;
import dev.arch420x0.archce.domain.entities.Tradeoff;
import dev.arch420x0.archce.persistence.repositories.MetricRepository;
import dev.arch420x0.archce.persistence.repositories.TradeoffRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MetricController {

	private final TradeoffRepository tradeoffRepository;

	private final MetricRepository metricRepository;

	public MetricController(TradeoffRepository tradeoffRepository, MetricRepository metricRepository) {
		this.tradeoffRepository = tradeoffRepository;
		this.metricRepository = metricRepository;
	}

	@GetMapping("/metric/{idtradeoff}")
	public ModelAndView metric(@PathVariable("idtradeoff") Long idtradeoff) {

		Optional<Tradeoff> tradeoff = tradeoffRepository.findById(idtradeoff);
		ModelAndView modelAndView = new ModelAndView("cadastro/metricregistration");
		modelAndView.addObject("tradeoffobj", tradeoff.get());
		modelAndView.addObject("metrics", metricRepository.getMetricPorTradeoff(idtradeoff));
		return modelAndView;

	}

	// Adicionar metric vinculado a tradeoff
	@PostMapping("/addMetricTradeoff/{idtradeoff}")
	public ModelAndView addMetricTradeoff(Metric metric, @PathVariable("idtradeoff") Long idtradeoff) {

		Tradeoff tradeoff = tradeoffRepository.findById(idtradeoff).get();

		if (metric != null && metric.getValor().isEmpty()) {

			ModelAndView modelAndView = new ModelAndView("cadastro/metricregistration");
			modelAndView.addObject("tradeoffobj", tradeoff);
			modelAndView.addObject("metrics", metricRepository.getMetricPorTradeoff(idtradeoff));

			return modelAndView;

		}

		metric.setTradeoff(tradeoff);
		metricRepository.save(metric);
		ModelAndView modelAndView = new ModelAndView("cadastro/metricregistration");
		modelAndView.addObject("tradeoffobj", tradeoff);
		modelAndView.addObject("metrics", metricRepository.getMetricPorTradeoff(idtradeoff));

		return modelAndView;

	}

	@GetMapping("/removermetric/{idmetric}")
	public ModelAndView removermetric(@PathVariable("idmetric") Long idmetric) {

		Tradeoff tradeoff = metricRepository.findById(idmetric).get().getTradeoff();
		metricRepository.deleteById(idmetric);

		ModelAndView modelAndView = new ModelAndView("cadastro/metricregistration");
		modelAndView.addObject("tradeoffobj", tradeoff);
		modelAndView.addObject("metrics", metricRepository.getMetricPorTradeoff(tradeoff.getId()));

		return modelAndView;

	}

}
