package com.mes2.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.mapper.PlatformMapper;
import com.mes2.security.domain.CustomUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private PlatformMapper platformMapper;
	
	public void setPlatformMapper(PlatformMapper platformMapper) {
        this.platformMapper = platformMapper;
    }
	
	@Override
	public UserDetails loadUserByUsername(String company_code) throws UsernameNotFoundException {
		logger.debug("UserDetails - loadUserByUsername() 호출");
		logger.debug("@@@ username: " + company_code);
		
		logger.debug("@@platformMapper: " + platformMapper);
		
		MdbDTO mdbDTO = platformMapper.read(company_code);
		
		logger.debug("@@@@mdbDTO: " + mdbDTO);
		
		UserDetails userDetails = User.builder().username(mdbDTO.getCompany_code())
				.password(mdbDTO.getPw())
				.authorities("ROLE_CLIENT")
				.build();
		
		return userDetails;
	}

}
