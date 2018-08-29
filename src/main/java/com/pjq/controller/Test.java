package com.pjq.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

	private static final Logger log = LoggerFactory.getLogger(Test.class);

	@RequestMapping(value = "/ce", method = RequestMethod.GET)
	public String ce() {
		log.error("123123123");
		return "asdassdasdasd";
	}
}
