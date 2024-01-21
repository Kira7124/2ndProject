package com.mes2.metadata.domain;

import java.sql.Date;

import lombok.Data;
@Data
public class md_amountDTO {
	
	private int index;
	private String product_code;
	private String material_code;
	private int amount; 
	private String amount_unit;
	private Date registration_date;
	private Date modification_date;
}
