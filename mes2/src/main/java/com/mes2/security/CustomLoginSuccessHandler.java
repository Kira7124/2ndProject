package com.mes2.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.service.PlatformService;

@Configuration
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomLoginSuccessHandler.class);

	@Inject
	PlatformService pService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws IOException, ServletException {
		logger.debug("CustomLoginSuccessHandler - onAuthenticationSuccess() 호출");
		
		// 사용자 권한 정보 저장
		List<String> roleNames = new ArrayList<String>();
		auth.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		logger.debug("@@@@@ roleName: " + roleNames);
		
		// 페이지 이동
		if(roleNames.contains("ROLE_CLIENT")) {
			logger.debug("고객사 로그인 성공");
			
			// 사용자 아이디(회사코드)로 정보 받기
			String company_code = ((UserDetails) auth.getPrincipal()).getUsername();
			
			MdbDTO mdbDTO = new MdbDTO();
			mdbDTO.setCompany_code(company_code);
			try {
				mdbDTO = pService.customerLogin(mdbDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 세션에 회사 정보 저장
			request.getSession().setAttribute("company_code", company_code);
			request.getSession().setAttribute("mdto", mdbDTO);
			
			response.sendRedirect("/platform/orderList");
			return;
		}
		
		// 나머지
		response.sendRedirect("/platform/login");
	}

}
