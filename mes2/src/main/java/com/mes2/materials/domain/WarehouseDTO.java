package com.mes2.materials.domain;

import lombok.Data;

@Data
public class WarehouseDTO {

	private String warehouse_code; //창고코드 
	private String category; //카테고리 
	private String name; //품목명
	private String manager; //담당자
	private String location; //창고위치
	private int use_status; //창고사용여부 
}
