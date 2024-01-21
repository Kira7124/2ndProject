package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.WarehouseDTO;

public interface InService {

	public int updateIncomingRequest(String in_code, String pd_lot) throws Exception;

	public List<InDTO> getIncomingStockInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;

	public void insertStock(int quantity, String product_code, String pd_lot) throws Exception;

	public void updateStockOnIncoming(int quantity, String product_code) throws Exception;

	public List<InDTO> selectStock(String product_code) throws Exception;
		
	public InDTO listIncomingProductCodes(String pd_lot) throws Exception;
	
	public List<InDTO> InDetailCompletedWarehouse(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;
	
	public String selectMaxMaterialsLot(String pd_lot) throws Exception;
	
	public int totalInCount(Criteria cri, String searchType, String keyword) throws Exception;

	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception;

	public int inDetailCount(Criteria cri, String searchType, String keyword) throws Exception;

	public String createRmLOT(String product_code) throws Exception;
	
	public List<InDTO> getAllInData(InDTO idto) throws Exception;
	
	public List<InDTO> getInventoryIndex(String in_index) throws Exception;
	
	public WarehouseDTO warehouseCodeCategory(String category) throws Exception;
}
