package com.mes2.sales.domain;



import java.util.Date;

import lombok.Data;

@Data
public class SalesDTO {
	private String order_code; //주문코드 (sales_order_info)
	private String company_name; // 거래처 회사명 - 수주처 (meta_data_business 테이블 / name)
	private String product_name; // 품목명 (meta_data_product 테이블 / name)
	private Date order_date; //납품요청일 (sales_order_info)
	private Date request_date; // 수주 신청일 (sales_order_info)
	private String product_code; //품목코드 (sales_order_product
	private int sales_quantity; //제품수량 - 수주량 (sales_order_product)
	private String user_id; // 사원id (employees테이블)
	private String sales_code; //수주코드 ( sales_order_product 테이블 )
	private String product_status; //제품진행상태 (sales_order_product 테이블
	private String processing_reg; //처리등록여부 (sales_order_product 테이블)
	private int stock_quantity; //현재고 (stock/quantity) ** 
	private int lack_quantity; // 제품수량 - 현재고 //부족수량 ** 
	
	private Date scheduled_date;
	
	private int newCnt;
	private int waitingCnt;
	private int completeCnt;
	
	//private String orderStatus;
	private String instructions;

	
	
	
	
	
	
	
}
