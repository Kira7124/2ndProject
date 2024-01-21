package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.ProductionLineSearchParam;
import com.mes2.production.persistence.ProductionLineDAO;

@Service
public class ProductionLineServiceImpl implements ProductionLineService{

	@Inject
	private ProductionLineDAO productionLineDAO;
	
	@Override
	public void save(ProductionLineDTO productionLineDTO) {
		productionLineDAO.insertProductionLine(productionLineDTO);
		
	}

	@Override
	public List<ProductionLineDTO> findBySearchParam(ProductionLineSearchParam param){
		return productionLineDAO.selectByDate(param);
	}
	
	
	
	@Override
	public Integer findBySearchParamForTotalCount(ProductionLineSearchParam param) {
		return productionLineDAO.selectByDateForTotalCount(param);
	}

	private String createLotCode(Date date, String mdpCode) {
		
		return null;
	}

	@Override
	public ProductionLineDTO findByIsCode(String isCode) {
		return productionLineDAO.selectByIsCode(isCode);
	}

	@Override
	public List<ProductionLineDTO> findByDateForProduce(Date startDate) {
		return productionLineDAO.selectByDateForProduce(startDate);
	}
	
	
	
	
	

}
