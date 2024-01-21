package com.mes2.metadata.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class common_DTO {

		private String code_group;
		private String code_group_name;
		private String code_code;
		private String code_name;
		private Timestamp code_insertdate;
		private Timestamp code_updatedate;
		private int code_usestatus;
		private int code_index;

}
