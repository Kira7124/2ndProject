package com.mes2.materials.domain;

import lombok.Data;

@Data
public class OpDTO {
	// 출고 품목 DTO
	
	private int out_product_index; // 인덱스
	private String out_code; // 출고코드
	private String product_code; // 품목코드
	private String pd_lot; // 로트번호
	private int out_quantity; // 출고수량

	
	private String warehouse_code; // 창고코드
	private String name; // 품목이름
}
