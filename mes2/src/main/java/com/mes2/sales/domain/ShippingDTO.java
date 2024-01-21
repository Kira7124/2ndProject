package com.mes2.sales.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ShippingDTO {

	private String order_code; //주문코드 (sales_order_info)
	private String company_name; // 거래처 회사명 - 수주처 (meta_data_business 테이블 / name)
	private String product_name; // 품목명 (meta_data_product 테이블 / name)
	private Date order_date; //납품요청일 (sales_order_info)
	private Date request_date; // 수주 신청일 (sales_order_info)
	private String product_code; //품목코드 (sales_order_product
	private int sales_quantity; //제품수량 - 수주량 (sales_order_product)
	private String user_id; // 사원id (employees테이블)
	private String user_name; // (employees테이블)
	private String sales_code; //수주코드 ( sales_order_product 테이블 )
	private String product_status; //제품진행상태 (sales_order_product 테이블
	private String processing_reg; //처리등록여부 (sales_order_product 테이블)
	
	private String ship_status;// 출하상태 (shipping 테이블)
	private String ship_code; // 출하코드
	private Date ship_date; //출하일자
	private Date scheduled_date; //출하 예정일 (납품요청일 4일 전)
	private String check; // idpw check , 확인용 check
	
	private int planCnt; // 계획 카운트
	private int waitingCnt;
	private int instructionCnt;
	private int completeCnt;
	
	private String progress_status; // shipping테이블 출하진행상태
	private String sales_status; // 
	private String confirm_status;//**
	private Date complete_date; // 수령완료일
	

	
	
	
}
