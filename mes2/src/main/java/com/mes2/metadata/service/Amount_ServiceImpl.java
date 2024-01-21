package com.mes2.metadata.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_amountDTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.persistence.Amount_DAO;

@Service
public class Amount_ServiceImpl implements Amount_Service {
	
private static final Logger logger = LoggerFactory.getLogger(Business_ServiceImpl.class);
	
	@Inject
	private Amount_DAO adao;
	

	@Override
	public int amountinsert(md_amountDTO dto) throws Exception {
		
		return adao.amountinsert(dto);
	}

	@Override
	public int amountupdate(md_amountDTO dto) throws Exception {
		
		return adao.amountupdate(dto);
	}
	
	@Override
	public int amountdelete(md_amountDTO dto) throws Exception {
		
		return adao.amountdelete(dto);
	}

	@Override
	public int gettotalcount(alllistDTO aDTO) throws Exception {
		
		return adao.gettotalcount(aDTO);
	}

	@Override
	public List<md_amountDTO> getlist(alllistDTO aDTO) throws Exception {
		
		return adao.getlist(aDTO);
	}

	@Override
	public List<md_productDTO> selectbox() throws Exception {
		
		return adao.selectbox();
	}
	
	@Override
	public List<md_productDTO> selectbox2() throws Exception {
		
		return adao.selectbox2();
	}
	
	@Override
	public String selectbox3(String product_code) throws Exception {
		
		return adao.selectbox3(product_code);
	}
	
	@Override
	public String selectbox4(String product_code) throws Exception {
		
		return adao.selectbox4(product_code);
	}
}
