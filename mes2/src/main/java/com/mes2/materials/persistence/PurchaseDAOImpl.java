package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;

@Repository
public class PurchaseDAOImpl implements PurchaseDAO {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseDAOImpl.class);

	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.mes2.mapper.MaterialsMapper";

	@Override
	public void insertPurchase(PurchaseDTO pdto) throws Exception {
		sqlSession.insert(NAMESPACE + ".insertMaterialOrderWithCode", pdto);
	}

	@Override
	public List<PurchaseDTO> listPurchase(String searchType, String keyword, Criteria cri, SearchDTO sdto)
			throws Exception {

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("keyword", keyword);
		searchMap.put("cri", cri);
		searchMap.put("sdto", sdto);

		return sqlSession.selectList(NAMESPACE + ".combinedMaterialList", searchMap);

	}

	@Override
	public int updateOrderStatus(String status, int orders_index) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("status", status);
		params.put("orders_index", orders_index);

		return sqlSession.update(NAMESPACE + ".updateOrderStatus", params);
	}

	@Override
	public productDTO getProductByCategory(String product_code) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);

		return sqlSession.selectOne(NAMESPACE + ".getProductByCategory", paramMap);

	}

	@Override
	public List<productDTO> selectMaterialCategoryList(String category) throws Exception {
		Map<String, Object> parammap = new HashMap<>();
		parammap.put("category", category);

		return sqlSession.selectList(NAMESPACE + ".selectMaterialCategoryList", parammap);
	}


	@Override
	public void updateQuantity(String product_code, int quantity, String category, String pd_lot) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		paramMap.put("quantity", quantity);
		paramMap.put("category", category);
		paramMap.put("pd_lot", pd_lot);

		sqlSession.insert(NAMESPACE + ".PurchaseupdateQuantity", paramMap);
	}

	@Override public int getPurchaseCount(Criteria cri, String searchType, String keyword) throws Exception {
		 Map<String, Object> paramMap = new HashMap<>();
		 paramMap.put("cri", cri); 
		 paramMap.put("searchType", searchType); 
		 paramMap.put("keyword", keyword);
	 return sqlSession.selectOne(NAMESPACE + ".combinedMaterialListCount" , paramMap); 
	 }


	@Override
	public List<PurchaseDTO> searchMaterial(String searchType, String keyword, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);
		paramMap.put("startPage", cri.getStartPage());
		paramMap.put("pageSize", cri.getPageSize());
		
		return sqlSession.selectList(NAMESPACE + ".combinedMaterialList", paramMap);
	}
	
	@Override
	public void MaterialReceipt(String product_code, int quantity, String pd_lot) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("product_code", product_code);
		paramMap.put("quantity", quantity);
		paramMap.put("pd_lot", pd_lot);
		

		sqlSession.update(NAMESPACE + ".insertMaterialReceipt", paramMap);
	}

	@Override
	public List<PurchaseDTO> getAllPurchaseData(PurchaseDTO pdto) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getAllPurchaseData" , pdto);
	}


	
}
