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
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;


@Repository
public class SalesDAOImpl implements SalesDAO {

	private static final Logger logger = LoggerFactory.getLogger(SalesDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE ="com.mes2.mapper.SalesMapper";
	
	@Override
	public List<SalesDTO> getSalesList(Criteria cri) {

		return sqlSession.selectList(NAMESPACE+".getSalesList",cri);
	}
	
	@Override
	public List<SalesDTO> getPlanContent(String order_code) {
		logger.debug(" DAO : getPlanContent() ");
		return sqlSession.selectList(NAMESPACE+".getPlanContent",order_code);
	}
	
	@Override
	public void rejectSales(List<String> odList) {
		logger.debug(" DAO : rejectSales(String order_code) ");
		sqlSession.update(NAMESPACE+".rejectSales", odList);
		
	}
	
	
	@Override
	public SalesDTO getStockQuantity(SalesDTO sd) {
		logger.debug(" DAO : getStockQuantity(String product_code) ");
		return sqlSession.selectOne(NAMESPACE+".getStockQuantity", sd);
	}
	

	
	@Override
	public void registerPlan(PlanRegisterDTO pdto) {
		logger.debug(" DAO : registerPlan(List<String> list)");
		sqlSession.update(NAMESPACE+".registerPlan", pdto);
		
	}
	
	@Override
	public String checkRegPw(String user_id) {
		logger.debug(" DAO : checkRegPw(String user_pw)");
		return sqlSession.selectOne(NAMESPACE+".checkRegPw", user_id);
	}
	
	@Override
	public List<SalesDTO> getProdctCode(List<String> list) {
		logger.debug(" DAO : getProdctCode(List<String> list)");
		return sqlSession.selectList(NAMESPACE+".getProdctCode", list);
	}
	
	@Override
	public void makeSalesCode(List<SalesDTO> sd) {
		logger.debug(" DAO : makeSalesCode(List<SalesDTO> sd)");
		sqlSession.selectList(NAMESPACE+".makeSalesCode", sd);
		
	}
	

	
	@Override
	public List<SalesDTO> getAcceptContent(String order_code) {
		logger.debug(" DAO : getAcceptContent(String order_code)");
		return sqlSession.selectList(NAMESPACE+".getAcceptContent", order_code);
	}
	
	@Override
	public void updateAcceptStatus(AcceptSaveDTO ad) {
		logger.debug(" DAO :updateAcceptStatus(AcceptSaveDTO ad)");

		sqlSession.update(NAMESPACE+".updateAcceptStatus",ad);
		
	}
	
	@Override
	public void stockReg(SalesDTO sd) {
		logger.debug(" DAO :stockReg(SalesDTO sd)");
		
		sqlSession.insert(NAMESPACE+".stockReg",sd);
		
		
	}
	
	@Override
	public void changeProductStatus(SalesDTO sd) {
		logger.debug(" DAO :changeProductStatus(SalesDTO sd)");
		
		sqlSession.update(NAMESPACE+".changeProductStatus",sd);
	}
	
	@Override
	public void productInst(SalesDTO sd) {
		logger.debug(" DAO :productInst(SalesDTO sd)");
		
		sqlSession.insert(NAMESPACE+".productInst",sd);
		
	}
	
	@Override
	public void updateStockQuan(SalesDTO sd) {
		logger.debug(" DAO :updateStockQuan(SalesDTO sd");
		
		sqlSession.update(NAMESPACE+".updateStockQuan",sd);
		
	}
	
	@Override
	public void insertShippingPlan(SalesDTO sd) {
		logger.debug(" DAO :insertShippingPlan(PlanRegisterDTO pdto)");
		sqlSession.update(NAMESPACE+".insertShippingPlan",sd);
	}
	
	@Override
	public Date checkOrdeDate(String order_code) {
		logger.debug(" DAO :checkOrdeDate(String order_code)");
		return sqlSession.selectOne(NAMESPACE+".checkOrdeDate",order_code);
	}
	

	
	@Override
	public int getPlanWaitCnt() {
		
		return sqlSession.selectOne(NAMESPACE+".getPlanWaitCnt");
	}
	
	@Override
	public int getPlanNewCnt(Criteria cri) {
		
		return sqlSession.selectOne(NAMESPACE+".getPlanNewCnt",cri);
	}
	
	
	@Override
	public List<SalesDTO> makeOrderStates(String order_code) {
		
		return sqlSession.selectList(NAMESPACE+".makeOrderStates", order_code);
	}
	

	
	@Override
	public AcceptSaveDTO getOrderInfo(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getOrderInfo", order_code);
	}
	
	@Override
	public void updateInstruction(String order_code) {
		sqlSession.update(NAMESPACE+".updateInstruction", order_code);
		
	}
	

	
	@Override
	public List<SalesDTO> listCount(Criteria cri) {
		
		return sqlSession.selectList(NAMESPACE+".listCount", cri);
	}
	
	@Override
	public SalesDTO getRegUser(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getRegUser", order_code);
	}
	
	@Override
	public AcceptSaveDTO getOrderPlanInfo(String order_code) {
		
		return sqlSession.selectOne(NAMESPACE+".getOrderPlanInfo", order_code);
	}
	
}
