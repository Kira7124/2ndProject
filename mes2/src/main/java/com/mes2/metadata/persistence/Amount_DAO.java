package com.mes2.metadata.persistence;

import java.util.List;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_amountDTO;
import com.mes2.metadata.domain.md_productDTO;

public interface Amount_DAO {
	
public int amountinsert(md_amountDTO dto) throws Exception;
	
	public int amountupdate(md_amountDTO dto) throws Exception;
	
	public int amountdelete(md_amountDTO dto) throws Exception;
	
	public int gettotalcount(alllistDTO dto) throws Exception;
	
	public List<md_amountDTO> getlist(alllistDTO dto) throws Exception;
	
	public List<md_productDTO> selectbox() throws Exception;
	
	public List<md_productDTO> selectbox2() throws Exception;
	
	public String selectbox3(String product_code) throws Exception;
	
	public String selectbox4(String product_code) throws Exception;
}
