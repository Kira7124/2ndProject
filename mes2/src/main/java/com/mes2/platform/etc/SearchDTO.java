package com.mes2.platform.etc;

import lombok.Data;

@Data
public class SearchDTO {
	private String company_code;
	private String sales_status;
	private String startDate;
	private String endDate;
	private Criteria cri;

	@Override
	public String toString() {
		return "searchDTO [company_code=" + company_code + ", sales_status=" + sales_status + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
