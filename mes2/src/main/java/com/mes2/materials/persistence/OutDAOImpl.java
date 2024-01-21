package com.mes2.materials.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;

@Repository
public class OutDAOImpl implements OutDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(OutDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.outMapper";

	// 출고 목록 조회
	@Override
	public List<OutDTO> getOutList(OutSearchDTO osDTO) throws Exception {
		logger.debug("DAO: getOutList() 호출");
		return sqlSession.selectList(NAMESPACE + ".getOutList", osDTO);
	}
	
	// 목록 개수 조회
	@Override
	public int getTotalOutCount(OutSearchDTO osDTO) throws Exception {
		logger.debug("DAO: getTotalOutCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getTotalOutCount", osDTO);
	}
	
	// 출고 상세 조회(출고코드 X)
	@Override
	public OutDTO getOutInfo(String out_index) throws Exception {
		logger.debug("DAO: getOutInfo() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getOutInfo", out_index);
	}
	
	// 출고 품목 재고 조회
	@Override
	public List<StockDTO> getStockList(String product_code) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getStockList", product_code);
	}
	
	// 출고 품목 등록
	@Override
	public void insertOutProduct(String out_code, List<StockDTO> stockList) throws Exception {
		logger.debug("insertOutProduct() 호출");
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("out_code", out_code);
		insertMap.put("stockList", stockList);
		
		sqlSession.insert(NAMESPACE + ".insertOutProduct", insertMap);
	}
	
	// 재고 감소
	@Override
	public void decreaseStock(List<StockDTO> stockList) throws Exception {
		logger.debug("decreaseStock() 호출");
		sqlSession.update(NAMESPACE + ".decreaseStock", stockList);
	}
	
	// 출고 목록 업데이트
	@Override
	public void updateOutInfo(OutDTO outDTO) throws Exception {
		logger.debug("updateOutInfo() 호출");
		sqlSession.update(NAMESPACE + ".updateOutInfo", outDTO);
	}
	
	// 수주 상태 업데이트
	@Override
	public void updateProductStatus(String based_code, String product_code) throws Exception {
		logger.debug("updateProductStatus() 호출");
		Map<String,	Object> updateMap = new HashMap<String, Object>();
		updateMap.put("based_code", based_code);
		updateMap.put("product_code", product_code);
		sqlSession.update(NAMESPACE + ".updateProductStatus", updateMap);
	}
	
	// 출고 상세 조회(출고코드 O)
	@Override
	public List<OpDTO> getOutDetail(String out_code) throws Exception {
		logger.debug("DAO: getOutDetail() 호출");
		return sqlSession.selectList(NAMESPACE + ".getOutDetail", out_code);
	}
	
	// 품목 전체 재고 가져오기
	@Override
	public int getQuantitySum(String product_code) throws Exception {
		logger.debug("DAO: getQuantitySum() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getQuantitySum", product_code);
	}
	
	// 생산 지시 수량 확인
	@Override
	public int getInsSum(String product_code) throws Exception {
		logger.debug("DAO: getInsSum() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getInsSum", product_code);
	}
	
	// 생산 지시 등록
	@Override
	public void insertIns(InsDTO insDTO) throws Exception {
		logger.debug("DAO: insertIns() 호출");
		sqlSession.insert(NAMESPACE + ".insertIns", insDTO);
	}
	
//	// 출고 품목 재고 등록
//	@Override
//	public StockDTO registProduct(int stock_index) throws Exception {
//		logger.debug("DAO: registProduct() 호출");
//		return sqlSession.selectOne(NAMESPACE + ".registProduct", stock_index);
//	}
	
	// 출고 품목 입력 시 Stock 테이블 출고 예정 수량에 입력
//	@Override
//	public void updatePlannedQuantity(List<StockDTO> stockDTO) throws Exception {
//		logger.debug("DAO: updatePlannedQuantity() 호출");
//		sqlSession.update(NAMESPACE +".updatePlannedQuantity", stockDTO);
//	}
}
