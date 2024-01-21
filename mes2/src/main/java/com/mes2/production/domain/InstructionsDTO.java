package com.mes2.production.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class InstructionsDTO {
	
	private String code;
	private int line;
	private String mdpCode;
	private String type; // 생산타입 ( 최초생산 F / 재생산 R)
	private String sopCode;
	private Date dueDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private int empNum;
	private String empId;
	private String empName;
	private String state; //상태 ( 요청 REQUESTED, 
	private int quantity;
	private int fault;
	private int salesQuantity;
	private int targetQuantity; //실제 생산 량 = salesQuantity * 1.1(수주 물량 + 10%)
	private String materialStatus;
	
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getMdpCode() {
		return mdpCode;
	}
	public void setMdpCode(String mdpCode) {
		this.mdpCode = mdpCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	

	public String getSopCode() {
		return sopCode;
	}
	public void setSopCode(String sopCode) {
		this.sopCode = sopCode;
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
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFault() {
		return fault;
	}
	public void setFault(int fault) {
		this.fault = fault;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	
	public int getSalesQuantity() {
		return salesQuantity;
	}
	public void setSalesQuantity(int salesQuantity) {
		this.salesQuantity = salesQuantity;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	public int getTargetQuantity() {
		return targetQuantity;
	}
	public void setTargetQuantity(int targetQuantity) {
		this.targetQuantity = targetQuantity;
	}
	
	public String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}
	@Override
	public String toString() {
		return "InstructionsDTO [code=" + code + ", line=" + line + ", mdpCode=" + mdpCode + ", type=" + type
				+ ", soiCode=" + sopCode + ", dueDate=" + dueDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", empNum=" + empNum + ", state=" + state + ", requestQuantity=" + ", quantity="
				+ quantity + ", fault=" + fault + "]";
	}

	
}
