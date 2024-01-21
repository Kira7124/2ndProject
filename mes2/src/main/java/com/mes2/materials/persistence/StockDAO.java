package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;

public interface StockDAO {
	
	// 재고 목록 조회
	public List<StockDTO> getStockList(OutSearchDTO searchDTO) throws Exception;
	
	// 재고 목록 개수
	public int getStockListCount(OutSearchDTO searchDTO) throws Exception;
	
	// 재고 상세 조회
	public List<StockDTO> getStockDetail(String product_code) throws Exception;
}
