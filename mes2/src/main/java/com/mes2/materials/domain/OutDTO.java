package com.mes2.materials.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class OutDTO {
	// 출고 목록 DTO
	
	private int out_index; // 인덱스
	private String out_code; // 출고코드
	private String based_code; // 근거(주문코드 or 작업지시코드)
	private String product_code; // 상품코드
	private int quantity; // 총 출고 수량
	private String status; // 진행상태(requested / complete)
	private String out_type; // 출고 유형(P:생산/S:출하)
	private Date out_request_date; // 출고 요청 일자
	private Date out_regdate; // 출고 등록 일자
	private productDTO pdto; // 상품 정보
	private List<OpDTO> opList; // 출고 품목 리스트
	
	@Override
	public String toString() {
		return "OutDTO [out_index=" + out_index + ", out_code=" + out_code + ", based_code=" + based_code
				+ ", product_code=" + product_code + ", quantity=" + quantity + ", status=" + status + ", out_type="
				+ out_type + ", out_request_date=" + out_request_date + ", out_regdate=" + out_regdate + ", pDTO="
				+ pdto + "]";
	}
	
}
