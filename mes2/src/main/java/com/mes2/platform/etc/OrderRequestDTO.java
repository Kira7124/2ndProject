package com.mes2.platform.etc;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.mes2.platform.domain.SopDTO;

import lombok.Data;

@Data
public class OrderRequestDTO {
	private String order_date;
	private String order_code;
	private List<SopDTO> sopList;
	
	@Override
	public String toString() {
		return "orderRequestDTO [order_date=" + order_date + ", sopList=" + sopList + "]";
	}
}
