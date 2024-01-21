package com.mes2.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor{

	private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//뭐 해야함?
		//1. 로그인 세션 비교
		// 아니면 날려버려야함
		log.debug("마 호출되나");
		
		HttpSession session = request.getSession(false);
		if(session==null || session.getAttribute("id")==null) {
			response.sendRedirect("/system/login");
			return false;
		}else {
			// 지금부터 시작임
			
			
		}
		
		return true;
	}
	
	

}
