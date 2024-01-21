package com.mes2.materials.domain;

import lombok.Data;

@Data
public class SearchDTO {
	private String category;
	private String name;
	
	private String searchType;
	private String keyword;
	
	private Criteria cri;

	@Override
	public String toString() {
		return "SearchDTO [category=" + category + ", name=" + name + ", searchType=" + searchType + ", keyword="
				+ keyword + ", cri=" + cri + "]";
	}

	

}
