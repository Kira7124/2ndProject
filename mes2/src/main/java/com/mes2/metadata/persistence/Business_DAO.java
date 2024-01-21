package com.mes2.metadata.persistence;

import java.util.List;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_businessDTO;

public interface Business_DAO {
	
	public int businessinsert(md_businessDTO dto) throws Exception;
	
	public int businessupdate(md_businessDTO dto) throws Exception;
	
	public int businessdelete(md_businessDTO dto) throws Exception;
	
	public int gettotalcount(alllistDTO dto) throws Exception;
	
	public List<md_businessDTO> getlist(alllistDTO dto) throws Exception;
	
	public String commoncode(String category) throws Exception;
	
	public String number(String commoncode) throws Exception;
	
	
}
