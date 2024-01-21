package com.mes2.security.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mes2.platform.domain.MdbDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	private static final long serialVersionUID = 1L;

	private MdbDTO mdbDTO;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUser(MdbDTO mdto) {
		super(mdto.getCompany_code(), mdto.getPw(), Collections.singletonList(new SimpleGrantedAuthority(mdto.getAuth())));
		this.mdbDTO = mdto;
	}
	
}
