package com.mes2.materials.domain;

import java.sql.Date;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import com.mes2.materials.domain.productDTO;

@Data
public class InDTO { 

	private int in_index; // 입고 인덱스
	private String warehouse_code; // 창고코드 
	private String in_code; // 입고코드 ** code_group + code_group_name
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date in_regdate; //입고등록일 
	
	private String product_code; //품목코드 **
	private int quantity; //품목수량 ** stock
	private String unit; // 단위 ** meta_data_product
	private String status; // 상태변경 
	
	private String pd_lot; //로트번호 **  product
	private int pd_quantity; //로트수량 **  product
	
	private String category; //카테고리 ** meta_data_product
	private String name; //품목명 ** meta_data_product
	private String cost; // 
	
	private String user_id; // 담당자 ** employees 

	private PageVO pageVo;
	
	
	
}
