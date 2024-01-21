package com.mes2.production.etc;

import java.util.List;

import lombok.Data;

@Data
public class RequestMaterialsDTO {

	private String productCode;
	private String name;
	private String productCategory;
	private String unit;
	private double cost;
	private double price;
	private String sopCode;
	private int salesQuantity;
	private String status; //주문요청 상태
	
	private List<RequestMaterialDTO> materialList;
	
	
}
