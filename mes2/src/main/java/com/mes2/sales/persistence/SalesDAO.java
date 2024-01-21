package com.mes2.sales.persistence;

import java.util.Date;
import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;


public interface SalesDAO {

	public List<SalesDTO> getSalesList(Criteria cri);
	public List<SalesDTO> getPlanContent(String order_code);
	public void rejectSales(List<String> odList);
	public SalesDTO getStockQuantity(SalesDTO sd);	
	public void registerPlan(PlanRegisterDTO pdto);
	public String checkRegPw(String user_id);
	public List<SalesDTO> getProdctCode(List<String>list);
	public void makeSalesCode(List<SalesDTO> sd);	
	public List<SalesDTO> getAcceptContent(String order_code);
	public void updateAcceptStatus(AcceptSaveDTO ad);
	public void stockReg(SalesDTO sd);
	public void changeProductStatus(SalesDTO sd);
	public void productInst(SalesDTO sd);
	public void updateStockQuan(SalesDTO sd);
	public void insertShippingPlan(SalesDTO sd);
	public Date checkOrdeDate(String order_code);
	public int getPlanWaitCnt();
	public int getPlanNewCnt(Criteria cri); 
	public List<SalesDTO>makeOrderStates(String order_code);
	public AcceptSaveDTO getOrderPlanInfo(String order_code);
	public AcceptSaveDTO getOrderInfo(String order_code);
	public void updateInstruction(String order_code);
	public List<SalesDTO> listCount(Criteria cri);
	public SalesDTO getRegUser(String order_code);
	
}
