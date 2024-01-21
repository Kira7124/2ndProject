package com.mes2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomnoopPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		// 암호화 동작 수행
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// 암호화 된 비밀번호를 기존 비밀번호와 비교
		return rawPassword.equals(encodedPassword);
	}

}
