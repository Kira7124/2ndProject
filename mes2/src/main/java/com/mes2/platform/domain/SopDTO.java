package com.mes2.platform.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class SopDTO {
	private int sales_product_index;
	private String sales_code; // 수주코드
	private String order_code; // 주문번호
	private String product_code; // 품목코드
	private int sales_quantity; // 주문수량
	private String product_status; // 생산 진행상태
	private String processing_reg; // 처리형태(출하지시, 생산지시, 복합처리)
	private MdpDTO mdpDTO; // 품목코드에 해당하는 품목 정보(품목명, 단가 등)
	@Override
	public String toString() {
		return "SopDTO [sales_product_index=" + sales_product_index + ", sales_code=" + sales_code + ", order_code="
				+ order_code + ", product_code=" + product_code + ", sales_quantity=" + sales_quantity
				+ ", product_status=" + product_status + ", processing_reg=" + processing_reg + ", mdpDTO=" + mdpDTO
				+ "]";
	}

	
}
