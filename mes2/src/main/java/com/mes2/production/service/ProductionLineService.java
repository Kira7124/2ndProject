package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.ProductionLineSearchParam;

public interface ProductionLineService {

	
	
	public void save(ProductionLineDTO productionLineDTO);
	
	public List<ProductionLineDTO> findBySearchParam(ProductionLineSearchParam param);
	public Integer findBySearchParamForTotalCount(ProductionLineSearchParam param);
	
	public ProductionLineDTO findByIsCode(String isCode);
	
	public List<ProductionLineDTO> findByDateForProduce(Date startDate);
}
