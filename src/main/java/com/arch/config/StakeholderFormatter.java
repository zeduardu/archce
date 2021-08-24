package com.arch.config;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.arch.model.Stakeholder;

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
