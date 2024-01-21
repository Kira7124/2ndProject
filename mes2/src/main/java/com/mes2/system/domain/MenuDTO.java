package com.mes2.system.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MenuDTO {
	private String menu_name;
	private String menu_status;
	private Timestamp menu_insertdate;
	private Timestamp menu_updatedate;
	
}
