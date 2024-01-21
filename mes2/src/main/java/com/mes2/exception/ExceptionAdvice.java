package com.mes2.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public String CommonException(Exception e, Model model) {
		logger.debug("CommonException() 실행");
		
		e.printStackTrace();
		model.addAttribute("e", e);
		
		return "exception";
	}
}
