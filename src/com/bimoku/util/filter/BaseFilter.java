package com.bimoku.util.filter;

import org.springframework.stereotype.Component;


@Component("fildFilter")
public class BaseFilter extends FieldFilter{

	@Override
	protected String fieldPriority(String field) {
		return null;
	}

}
