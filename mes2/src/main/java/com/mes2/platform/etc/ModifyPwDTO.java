package com.mes2.platform.etc;

import lombok.Data;

@Data
public class ModifyPwDTO {
	private String company_code;
	private String pw;
	private String checkPw;
	
	@Override
	public String toString() {
		return "modifyPwDTO [company_code=" + company_code + ", pw=" + pw + ", checkPw=" + checkPw + "]";
	}
}
