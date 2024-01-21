package com.mes2.metadata.service;


import java.util.List;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_productDTO;

public interface Product_Service {
	
	//품목  추가
	public int productinsert(md_productDTO dto) throws Exception;
	
	//품목 수정
	public int productupdate(md_productDTO dto) throws Exception;
	
	//품목 삭제
	public int productdelete(md_productDTO dto) throws Exception;
	
	//글 개수(페이징)
	public int gettotalcount(alllistDTO aDTO) throws Exception;
	
	//리스트(페이징)
	public List<md_productDTO> getlist(alllistDTO aDTO) throws Exception;
	
	//카테고리 공통코드
	public List<common_DTO> selectbox() throws Exception;

	//단위 공통코드
	public List<common_DTO> selectbox2() throws Exception;
}
