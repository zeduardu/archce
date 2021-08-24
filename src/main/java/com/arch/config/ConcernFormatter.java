package com.arch.config;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.arch.model.Concern;

public class ConcernFormatter implements Formatter<Concern> {

	@Override
	public String print(Concern c, Locale locale) { 
		return c.getId().toString();
	}

	@Override
	public Concern parse(String id, Locale locale) throws ParseException {
		Concern c = new Concern();
		c.setId(Long.valueOf(id));
		return c;
	}

}
