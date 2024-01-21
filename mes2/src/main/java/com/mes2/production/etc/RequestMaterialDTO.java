package com.mes2.production.etc;

import java.sql.Date;

import lombok.Data;

@Data
public class RequestMaterialDTO {
	private int index;
	private String productCode;
	private String materialCode;
	private int amount;
	private String amountUnit;
	private Date registrationDate;
	private int totalAmount;
}
