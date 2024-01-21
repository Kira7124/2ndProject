package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;

public interface ProductService {

	public Date getTime();

	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam);
	
	public ProductDTO selectByLot(String Lot);
	
	public int deleteByLot(List<String> lotList);
	
	//inWarehouse 입고 요청
	public int saveInWarehouse(ProductDTO productDTO);
	
	public int selectBySearchForTotalCount(ProductSearchParam productSearchParam);

	
}
