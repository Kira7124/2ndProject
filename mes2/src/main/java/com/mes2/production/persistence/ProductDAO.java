package com.mes2.production.persistence;

import java.sql.Date;
import java.util.List;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;

public interface ProductDAO {
	public Date getTime();
	
	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam);
	
	public ProductDTO selectByLot(String Lot);
	
	public int deleteByLot(List<String> lotList);
	
	public String searchLastLot(String searchLot);
	
	public int insertProduct(ProductDTO productDTO);
	
	public int updateProduct(ProductDTO productDTO);
	
	public int insertInWarehouse(ProductDTO productDTO);
	
	public int selectBySearchForTotalCount(ProductSearchParam productSearchParam);
}
