package dev.arch420x0.archce.application.common;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import dev.arch420x0.archce.domain.entities.Stakeholder;

public class StakeholderFormatter implements Formatter<Stakeholder> {

	@Override
	public String print(Stakeholder c, Locale locale) {
		return c.getId().toString();
	}

	@Override
	public Stakeholder parse(String id, Locale locale) throws ParseException {
		Stakeholder c = new Stakeholder();
		c.setId(Long.valueOf(id));
		return c;
	}

}
