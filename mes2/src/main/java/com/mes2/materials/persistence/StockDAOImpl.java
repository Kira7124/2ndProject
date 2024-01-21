package com.mes2.materials.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;

@Repository
public class StockDAOImpl implements StockDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StockDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.mes2.mapper.stockMapper";

	// 재고 목록 조회
	@Override
	public List<StockDTO> getStockList(OutSearchDTO searchDTO) throws Exception {
		logger.debug("DAO: getStockList() 호출");
		return sqlSession.selectList(NAMESPACE + ".getStockList", searchDTO);
	}
	
	// 재고 목록 개수
	@Override
	public int getStockListCount(OutSearchDTO searchDTO) throws Exception {
		logger.debug("DAO: getStockListCount() 호출");
		if(searchDTO != null && "품목명".equals(searchDTO.getSearchType())) {
			logger.debug("품목명 검색");
			return sqlSession.selectOne(NAMESPACE + ".getStockListCountByName", searchDTO);
		}
		logger.debug("검색어 없음 or 품목코드 검색");
		return sqlSession.selectOne(NAMESPACE + ".getStockListCount", searchDTO);
	}
	
	// 재고 상세 조회
	@Override
	public List<StockDTO> getStockDetail(String product_code) throws Exception {
		logger.debug("DAO: getStockDetail() 호출");
		return sqlSession.selectList(NAMESPACE + ".getStockDetail", product_code);
	}

}
