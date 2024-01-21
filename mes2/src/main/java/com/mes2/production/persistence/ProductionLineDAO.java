package com.mes2.production.persistence;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.ProductionLineSearchParam;

public interface ProductionLineDAO {
	
	public int insertProductionLine (ProductionLineDTO lineDTO);
	
	public List<ProductionLineDTO> selectByDate(ProductionLineSearchParam param);

	public Integer selectByDateForTotalCount(ProductionLineSearchParam param);
	
	public ProductionLineDTO selectByIsCode(String isCode);
	
	public int updateState(ProductionLineDTO productionLineDTO);
	
	public int updateComplete(ProductionLineDTO productionLineDTO);
	
	public List<ProductionLineDTO> selectByDateForProduce(Date startDate);

}
