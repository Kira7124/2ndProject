package com.mes2.metadata.service;


import java.util.List;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.md_businessDTO;

public interface Business_Service {
	
	//거래처  추가
	public int businessinsert(md_businessDTO dto) throws Exception;
	
	//거래처 수정
	public int businessupdate(md_businessDTO dto) throws Exception;
	
	//거래처 삭제
	public int businessdelete(md_businessDTO dto) throws Exception;
	
	//글 개수(페이징)
	public int gettotalcount(alllistDTO aDTO) throws Exception;
	
	//리스트(페이징)
	public List<md_businessDTO> getlist(alllistDTO aDTO) throws Exception;
	
}
