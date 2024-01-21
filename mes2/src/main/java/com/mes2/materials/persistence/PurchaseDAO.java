package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

public interface PurchaseDAO {

	public void insertPurchase(PurchaseDTO pdto) throws Exception;

	public List<PurchaseDTO> listPurchase(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception;

	public int updateOrderStatus(String status, int orders_index) throws Exception;

	public void updateQuantity(String product_code, int quantity, String category, String pd_lot) throws Exception;

	public void MaterialReceipt(String product_code, int quantity , String pd_lot) throws Exception;

	public productDTO getProductByCategory(String product_code) throws Exception;

	public List<productDTO> selectMaterialCategoryList(String category) throws Exception;

	public int getPurchaseCount(Criteria cri, String searchType, String keyword) throws Exception;
	 
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword, Criteria cri) throws Exception;

	public List<PurchaseDTO> getAllPurchaseData(PurchaseDTO pdto) throws Exception;

}
