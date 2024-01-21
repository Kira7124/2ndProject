package com.mes2.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	private Logger log = LoggerFactory.getLogger(WebConfig.class);
	

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.debug("인터셉터 호출되는지 함 보여봐라");
		
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/**")
            .excludePathPatterns(
                    "/login","/logout","/css/**","/js/**","/bootstrap/**", "/dist/**", 
                    		 "/fullcalander/**", "/img/**", "/plugins/**", "/platform/**");
		
	}
	
	

}
