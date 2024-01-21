package com.mes2.sales.persistence;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;

import com.mes2.sales.domain.ShippingDTO;

@Repository
public class ShippingDAOImpl implements ShippingDAO {

	private static final Logger logger = LoggerFactory.getLogger(ShippingDAOImpl.class);

	@Inject
	private SqlSession sqlSession;
	

	private static final String NAMESPACE ="com.mes2.mapper.shippingMapper";
	
	@Override
	public List<ShippingDTO> getShippingList(Criteria cri) {
		
		logger.debug(" DAO : getShippingList(ShippingDTO sdt) ");
		return sqlSession.selectList(NAMESPACE+".getShippingList",cri);
	}
	
	@Override
	public List<String> productStatusCnt(String order_code) {
		logger.debug(" DAO : productStatusCnt(ShippingDTO sdto) ");
		return sqlSession.selectList(NAMESPACE+".productStatusCnt", order_code);
	}
	
	@Override
	public void updateShipStatus(ShippingDTO sdto) {
		logger.debug(" DAO : updateShipStatus(ShippingDTO sdto) ");
		sqlSession.update(NAMESPACE+".updateShipStatus", sdto);
		
	}
	
	@Override
	public List<ShippingDTO> getPlanContent(String order_code) {
		logger.debug(" DAO : getPlanContent(String order_code)");
		return sqlSession.selectList(NAMESPACE+".getPlanContent", order_code);
	}


	
	@Override
	public ShippingDTO getId(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getId", order_code);
	}
	
	@Override
	public String checkUpdatePw(String user_id) {
		return sqlSession.selectOne(NAMESPACE+".checkUpdatePw", user_id);
		
	}
	
	@Override
	public List<String> getScheduleDate(Date schedule_date) {
		
		return sqlSession.selectList(NAMESPACE+".getScheduleDate", schedule_date);
	}
	
	@Override
	public void updateSchedule(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".updateSchedule",sdto );
		
	}
	
	@Override
	public ShippingDTO checkOrderDate(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".checkOrderDate", order_code);
	}
	
	@Override
	public AcceptSaveDTO getOrderInfo(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getOrderInfo", order_code);
	}
	
	@Override
	public int countShipStatus(String ship_status) {
		
		return sqlSession.selectOne(NAMESPACE+".countShipStatus",ship_status );
	}
	

	
	@Override
	public void updateShipDate(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".updateShipDate",sdto );
		
	}
	
	@Override
	public void makeShipCode(ShippingDTO sdto) {
		sqlSession.update(NAMESPACE+".makeShipCode",sdto );
		
	}
	@Override
	public ShippingDTO getShipDate(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getShipDate",order_code);
	}
	

	
	@Override
	public List<ShippingDTO> instructionList(Criteria cri) {
		
		return sqlSession.selectList(NAMESPACE+".instructionList",cri);
	}
	
	@Override
	public int countShipProgressing(String progress_status) {
		
		return sqlSession.selectOne(NAMESPACE+".countShipProgressing", progress_status);
	}
	

	
	@Override
	public void updateShipProgressing(ShippingDTO sdto) {
		
		sqlSession.update(NAMESPACE+".updateShipProgressing", sdto);
		
	}
	
	@Override
	public List<ShippingDTO> getshipContent(String order_code) {
		
		return sqlSession.selectList(NAMESPACE+".getshipContent", order_code);
	}
	

	@Override
	public void updateSaleStatus(ShippingDTO sdto) {
		
		sqlSession.update(NAMESPACE+".udpateSaleStatus", sdto);
	}
	
	@Override
	public List<ShippingDTO> totalCount(Criteria cri) {
		return sqlSession.selectList(NAMESPACE+".totalCount",cri);
	}
	
	@Override
	public List<ShippingDTO> shippingTotalCount(Criteria cri) {
		
		return sqlSession.selectList(NAMESPACE+".shippingTotalCount",cri);
	}
	

	
	
}
