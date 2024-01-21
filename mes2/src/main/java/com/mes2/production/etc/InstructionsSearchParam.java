package com.mes2.production.etc;

import java.sql.Date;
import java.sql.Timestamp;

import com.mes2.production.vo.InstructionsState;

public class InstructionsSearchParam {
	
	private String code;
	private String state;
	private Timestamp startTime;
	private Timestamp endTime;
	private String searchType;
	private String requestCode;
	
	private int page;
	private int pageSize;
	
	// 날짜 검색 전용
	private Date startDate;
	private Date endDate;
	
	
	public int getStartPage() {
		// 페이지 정보를 쿼리사용되는 값(시작인덱스)으로 변경
		return (this.page - 1) * pageSize;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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

	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	@Override
	public String toString() {
		return "InstructionsSearchParam [code=" + code + ", state=" + state + ", startTime=" + startTime + ", endTime="
				+ endTime + ", searchType=" + searchType + ", requestCode=" + requestCode + ", page=" + page
				+ ", pageSize=" + pageSize + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	
	
	
	
	

}
