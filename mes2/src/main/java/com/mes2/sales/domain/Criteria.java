package com.mes2.sales.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Criteria {

	private int page; //현재 페이지 번호
	private int perPageNum; //한 페이지에 보여줄 글 수 
	
	public Criteria() {
		this.page=1;
		this.perPageNum=10;
	}
	
	// 검색기능에 필요한 변수 
	private String type;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDay;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDay;
	
	private String search;
	
	private String sales_status;
	
	private String newOrder;
	
	private String instructions;
	
	private String userId;
	
	private String shipStatus;
	
	private String progressStatus;
	
	private String progressSta;
	
	private String shipSta;
	
	private String instruct;
	
	private String newO;
	
	// 글 1~10 , 11~20 ...
	public int getPageStart() {
		return (page-1)*perPageNum;
	}
}
