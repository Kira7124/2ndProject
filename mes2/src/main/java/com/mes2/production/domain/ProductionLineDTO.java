package com.mes2.production.domain;

import java.sql.Date;

public class ProductionLineDTO {

	private int index;
	private int line;
	private String isCode;
	private Date startDate;
	private Date endDate; 
	private String status; //라인 상태 STANDBY,START,CLOSE
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getIsCode() {
		return isCode;
	}
	public void setIsCode(String isCode) {
		this.isCode = isCode;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductionLineDTO [index=" + index + ", line=" + line + ", isCode=" + isCode + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status + "]";
	}
	
	
	//
}
