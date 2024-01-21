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
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.WarehouseDTO;

@Repository
public class InDAOImpl implements InDAO {

	private static final Logger logger = LoggerFactory.getLogger(InDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.MaterialsMapper";


	@Override
	public List<InDTO> getAllInboundInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("keyword", keyword);
		searchMap.put("cri", cri);
		searchMap.put("sdto", sdto);
	    
		return sqlSession.selectList(NAMESPACE + ".getInList", searchMap);
	}
	

	@Override
	public int getInCount(Criteria cri, String searchType, String keyword) throws Exception {
		 Map<String, Object> paramMap = new HashMap<>();
		 paramMap.put("cri", cri); 
		 paramMap.put("searchType", searchType); 
		 paramMap.put("keyword", keyword);
		return sqlSession.selectOne(NAMESPACE + ".InCount", paramMap);
	}
	
	@Override
	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);
		paramMap.put("startPage", cri.getStartPage());
		paramMap.put("pageSize", cri.getPageSize());
		
		return sqlSession.selectList(NAMESPACE + ".getInList", paramMap);
	}
	
	


	@Override
	public int updateIncomingRequest(String in_code, String pd_lot) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("in_code", in_code);
		params.put("pd_lot", pd_lot);
		
		return sqlSession.insert(NAMESPACE +".updateIncomingRequest", params);
		
	}


	@Override
	public void insertStock(int quantity, String product_code, String pd_lot) throws Exception {
		Map<String, Object> paramap = new HashMap<>();
		paramap.put("quantity", quantity);
		paramap.put("product_code", product_code);
		paramap.put("pd_lot", pd_lot);
		sqlSession.insert(NAMESPACE + ".insertStock", paramap);
	}


	@Override
	public void updateStockOnIncoming(int quantity, String product_code) throws Exception {
		Map<String, Object> paramap = new HashMap<>();
		 paramap.put("quantity", quantity);
		 paramap.put("product_code", product_code);
		 sqlSession.update(NAMESPACE + ".updateStockOnIncoming" , paramap);
	}


	@Override
	public List<InDTO> selectStock(String product_code) throws Exception {
		Map<String, Object> paramap = new HashMap<>();
		 paramap.put("product_code", product_code);
		 
		return sqlSession.selectList(NAMESPACE + ".selectStock", paramap);
	}


	@Override
	public InDTO listIncomingProductCodes(String pd_lot) throws Exception {
		Map<String, Object> paramap = new HashMap<>();
		 paramap.put("pd_lot", pd_lot);
		return sqlSession.selectOne(NAMESPACE + ".listIncomingProductCodes", paramap);
	}


	@Override
	public List<InDTO> InDetailCompletedWarehouse(String searchType, String keyword, Criteria cri, SearchDTO sdto)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);
		paramMap.put("startPage", cri.getStartPage());
		paramMap.put("pageSize", cri.getPageSize());
		
		return sqlSession.selectList(NAMESPACE + ".InDetailCompletedWarehouse", paramMap);
	}


	@Override
	public int inDetailCount(Criteria cri, String searchType, String keyword) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		 paramMap.put("cri", cri); 
		 paramMap.put("searchType", searchType); 
		 paramMap.put("keyword", keyword);
		return sqlSession.selectOne(NAMESPACE + ".inDetailCount", paramMap);
	}


	@Override
	public String selectMaxMaterialsLot(String pd_lot) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".selectMaxMaterialsLot", pd_lot);
	}


	@Override
	public List<InDTO> getAllInData(InDTO idto) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getAllInData", idto);
	}


	@Override
	public List<InDTO> getInventoryIndex(String in_index) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getInventoryIndex" , in_index);
	}


	@Override
	public WarehouseDTO warehouseCodeCategory(String category) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".warehouseCodeCategory", category);
	}
	
	
	

}
