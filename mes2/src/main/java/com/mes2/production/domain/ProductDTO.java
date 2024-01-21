package com.mes2.production.domain;

import java.sql.Date;

import lombok.Data;

public class ProductDTO {
	
	private String pd_lot;
	private String pd_mdp_code;
	private int pd_quantity;
	private Date pd_period;
	private Date pd_date;
	private String pd_soi_id;
	private int pd_qc_result;
	private int pd_count;
	private String pd_is_code;
	
	public String getPd_lot() {
		return pd_lot;
	}
	public void setPd_lot(String pd_lot) {
		this.pd_lot = pd_lot;
	}

	
	
	public String getPd_mdp_code() {
		return pd_mdp_code;
	}
	public void setPd_mdp_code(String pd_mdp_code) {
		this.pd_mdp_code = pd_mdp_code;
	}
	public int getPd_quantity() {
		return pd_quantity;
	}
	public void setPd_quantity(int pd_quantity) {
		this.pd_quantity = pd_quantity;
	}
	public Date getPd_period() {
		return pd_period;
	}
	public void setPd_period(Date pd_period) {
		this.pd_period = pd_period;
	}
	public Date getPd_date() {
		return pd_date;
	}
	public void setPd_date(Date pd_date) {
		this.pd_date = pd_date;
	}
	public String getPd_soi_id() {
		return pd_soi_id;
	}
	public void setPd_soi_id(String pd_soi_id) {
		this.pd_soi_id = pd_soi_id;
	}
	public int getPd_qc_result() {
		return pd_qc_result;
	}
	public void setPd_qc_result(int pd_qc_result) {
		this.pd_qc_result = pd_qc_result;
	}
	public int getPd_count() {
		return pd_count;
	}
	public void setPd_count(int pd_count) {
		this.pd_count = pd_count;
	}
	public String getPd_is_code() {
		return pd_is_code;
	}
	public void setPd_is_code(String pd_is_code) {
		this.pd_is_code = pd_is_code;
	}
	
	
	
	

	
}
