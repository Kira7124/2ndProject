package com.mes2.materials.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class StockDTO {
	// 재고관리 품목 DTO
	private int stock_index; // 인덱스
	private String product_code; // 품목코드
	private String warehouse_code; // 창고코드
	private int quantity; // 현재 재고
	private String category; // 부자재,원재료,완제품
	private String pd_lot; // 로트번호
	private Date regdate; // 등록일자
	private int useQuantity; // 출고 수량
	
	private String name; // 품목명
	private String ofileName; // 형상정보
	private String unit; // 품목 단위
}
