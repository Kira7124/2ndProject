package com.mes2.materials.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class productDTO {
	private String product_code; // 품목코드
	private String name; // 품목명
	private String category; // 카테고리(완제품/부자재/원재료)
	private String unit; // 단위
	private double cost; // 원가
	private double price; // 단가(판매가)
	private int production_status; // 생산유무
	private Date regdate; // 등록일자
}
