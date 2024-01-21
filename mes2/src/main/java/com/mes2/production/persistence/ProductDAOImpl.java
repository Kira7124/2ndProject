package com.mes2.production.persistence;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;

@Repository
public class ProductDAOImpl implements ProductDAO{

	private final String NAMESPACE = "com.mes2.mapper.ProductMapper";
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSession sqlSession;
	//테스트용 
	
	@Override
	public Date getTime() {
		return sqlSession.selectOne(NAMESPACE+".getTime");
	}
	
	@Override
	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam){
		
		
		log.debug("ProductDAO : 입력받은 서치 시작 날짜 : " + productSearchParam.getStartDate());
		log.debug("ProductDAO : 입력받은 서치 마지막 날짜 : " + productSearchParam.getEndDate());
		log.debug("ProductDAO : 입력받은 서치 mdp_code : " + productSearchParam.getName());
		
		return sqlSession.selectList(NAMESPACE+".selectBySearch", productSearchParam);
	}

	@Override
	public ProductDTO selectByLot(String lot) {
		log.debug("ProductDAO : selectByLot 호출");
		return sqlSession.selectOne(NAMESPACE+".selectByLot", lot);
	}

	
	@Override
	public int deleteByLot(List<String> lotList) {
		log.debug("ProductDAO : deleteByLot 호출");
		return sqlSession.delete(NAMESPACE+".deleteByLot", lotList);
	}

	@Override
	public String searchLastLot(String searchLot) {
		
		return sqlSession.selectOne(NAMESPACE+".getLastLot", searchLot);
	}

	@Override
	public int insertProduct(ProductDTO productDTO) {
		return sqlSession.insert(NAMESPACE+".insertProduct", productDTO);
	}

	@Override
	public int updateProduct(ProductDTO productDTO) {
		return sqlSession.update(NAMESPACE+".updateProduct", productDTO);
	}

	@Override
	public int insertInWarehouse(ProductDTO productDTO) {
		return sqlSession.insert(NAMESPACE+".insertInWarehouse",productDTO);
	}

	@Override
	public int selectBySearchForTotalCount(ProductSearchParam productSearchParam) {
		return sqlSession.selectOne(NAMESPACE+".selectBySearchForTotal",productSearchParam);
	}									
	
	
	
	
	
}
