package com.mes2.materials.domain;

import lombok.Data;

@Data
public class OutSearchDTO {
	// 출고 목록 검색 DTO
	private String status;
	private String startDate;
	private String endDate;
	private String product_code;
	private String searchType;
	private String search;
	
	private Criteria cri;
}
