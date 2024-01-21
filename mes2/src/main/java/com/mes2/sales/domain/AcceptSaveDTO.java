package com.mes2.sales.domain;

import java.util.Date;


import lombok.Data;

@Data
public class AcceptSaveDTO {

	 private String order_code;
	 private String company_name;
	 private String company_code;
	 private Date request_date;
	 private String user_id;
	 private String user_name;
	 private String user_department;
	 private String user_position;
	 private String user_auth;
	 private String company_address;
	 private String company_call;
}
