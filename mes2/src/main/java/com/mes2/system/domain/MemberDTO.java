package com.mes2.system.domain;

import java.sql.Timestamp;




import lombok.Data;


@Data
public class MemberDTO {
	
	private String user_num;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_department;
	private String user_position;
	private String user_address;
	private String user_jumin;
	private String user_joindate;
	private String user_tel;
	private Timestamp user_insertdate;
	private Timestamp user_updatedate;
	private String user_auth;
	private int count;
	private String user_img;
	private String menu_status;
}
