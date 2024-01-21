package com.mes2.metadata.domain;

import lombok.Data;

@Data
public class md_businessDTO {
	private String company_code;
	private String category;
	private String pw;
	private String name; 
	private String manager;
	private String address;
	private String call;
	private String fax;
	private String email;
	private int contract_status;
	private String auth;
}
