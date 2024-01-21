package com.mes2.materials.domain;

import lombok.Data;

@Data
public class InsDTO {
	// 생산지시 DTO
	private String mdp_code;
	private String type;
	private String sop_code;
	private String state;
	private int sales_quantity;
}
