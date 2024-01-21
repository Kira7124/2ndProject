package com.mes2.metadata.persistence;


import java.util.List;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_productDTO;

public interface Product_DAO {
	
	public int productinsert(md_productDTO dto) throws Exception;
	
	public int productupdate(md_productDTO dto) throws Exception;
	
	public int productdelete(md_productDTO dto) throws Exception;
	
	public int gettotalcount(alllistDTO dto) throws Exception;
	
	public List<md_productDTO> getlist(alllistDTO dto) throws Exception;
	
	public String commoncode(String category) throws Exception;
	
	public String number(String commoncode) throws Exception;
	
	public List<common_DTO> selectbox() throws Exception;
	
	public List<common_DTO> selectbox2() throws Exception;
}
