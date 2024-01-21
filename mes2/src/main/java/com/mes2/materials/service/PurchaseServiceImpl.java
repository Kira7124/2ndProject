package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;
import com.mes2.materials.persistence.PurchaseDAO;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceImpl.class);

	@Inject
	private PurchaseDAO pdao;

	@Override
	public void purchaseOrder(PurchaseDTO pdto) throws Exception {
		pdao.insertPurchase(pdto);
	}
	
	@Override 
	  public List<PurchaseDTO> PurchaseInfo(String searchType, String search, Criteria cri, SearchDTO sdto) throws Exception {

	 return pdao.listPurchase(searchType, search, cri, sdto); 
	 }
	 

	@Override
	public int updateOrderStatus(String status, int orders_index) throws Exception {
		return pdao.updateOrderStatus(status, orders_index);
	}

	@Override
	public void updateQuantity(String product_code, int quantity, String category, String pd_lot) throws Exception {
		pdao.updateQuantity(product_code, quantity, category, pd_lot);
	}

	 @Override public int totalPurchaseCount(Criteria cri, String searchType, String keyword) throws Exception {
	 return pdao.getPurchaseCount(cri, searchType,keyword); }
	 

	@Override
	public void MaterialReceipt(String product_code, int quantity  , String pd_lot) throws Exception {
		pdao.MaterialReceipt(product_code, quantity , pd_lot);
	}

	
	 @Override public List<PurchaseDTO> searchMaterial(String searchType, String keyword, Criteria cri) throws Exception { 
		 return pdao.searchMaterial(searchType, keyword, cri);
	 }
	 

	@Override
	public productDTO getProductByCategory(String product_code) throws Exception {
		return pdao.getProductByCategory(product_code);
	}

	@Override
	public List<productDTO> selectMaterialCategoryList(String category) throws Exception {
		return pdao.selectMaterialCategoryList(category);
	}

	@Override
	public List<PurchaseDTO> getAllPurchaseData(PurchaseDTO pdto) throws Exception {
		return pdao.getAllPurchaseData(pdto);
	}

	

	

}
