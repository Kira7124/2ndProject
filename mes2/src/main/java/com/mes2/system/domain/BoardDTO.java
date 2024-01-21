package com.mes2.system.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data	
public class BoardDTO {

	private int bno;
	private String writer;
	private String title;
	private String content;
	private int read_count;
	private Timestamp regdate;
	
	
}
