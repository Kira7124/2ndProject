package com.mes2.sales.domain;


import java.util.List;

import lombok.Data;

@Data
public class PlanRegisterDTO {

	 private String user_id;
	 private List<String> order_code;
	// private Date scheduled_date;
}
