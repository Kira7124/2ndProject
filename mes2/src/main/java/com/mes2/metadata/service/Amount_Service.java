package com.mes2.metadata.service;

import java.util.List;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_amountDTO;
import com.mes2.metadata.domain.md_productDTO;

public interface Amount_Service {
	
		//bom  추가
		public int amountinsert(md_amountDTO dto) throws Exception;
		
		//bom 수정
		public int amountupdate(md_amountDTO dto) throws Exception;
		
		//bom 삭제
		public int amountdelete(md_amountDTO dto) throws Exception;
		
		//글 개수(페이징)
		public int gettotalcount(alllistDTO aDTO) throws Exception;
		
		//리스트(페이징)
		public List<md_amountDTO> getlist(alllistDTO aDTO) throws Exception;
		
		//bom  품목코드 가져오기
		public List<md_productDTO> selectbox() throws Exception;
		
		//bom  원재료코드 가져오기
		public List<md_productDTO> selectbox2() throws Exception;
				
		//bom
		public String selectbox3(String product_code) throws Exception;
		
		//bom
		public String selectbox4(String product_code) throws Exception;
}
