package com.mes2.production.etc;

import java.sql.Date;

import lombok.Data;


public class ProductionLineSearchParam {
	private String isCode;
	private String status;
	private Date startDate;
	private Date endDate;
	
	private int page;
	private int pageSize;
	
	public int getStartPage() {
		// 페이지 정보를 쿼리사용되는 값(시작인덱스)으로 변경
		return (this.page - 1) * pageSize;
	}

	public String getIsCode() {
		return isCode;
	}

	public void setIsCode(String isCode) {
		this.isCode = isCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
}
