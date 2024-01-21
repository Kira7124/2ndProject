package com.mes2.platform.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/sample/*")
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@GetMapping("/all")
	public void doAll() {
		logger.debug("doAll() 호출");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		logger.debug("doAdmin() 호출");
	}
}
