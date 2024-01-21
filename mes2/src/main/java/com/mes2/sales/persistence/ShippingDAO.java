package com.mes2.sales.persistence;

import java.util.Date;
import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;

import com.mes2.sales.domain.ShippingDTO;



public interface ShippingDAO {

	
	public List<ShippingDTO> getShippingList(Criteria cri);
	public List<String> productStatusCnt(String order_code);
	public void updateShipStatus(ShippingDTO sdto);
	public List<ShippingDTO> getPlanContent(String order_code);
	public ShippingDTO getId(String order_code);
	public String checkUpdatePw(String user_id);
	public List<String> getScheduleDate(Date schedule_date);
	public void updateSchedule(ShippingDTO sdto);
	public ShippingDTO checkOrderDate(String order_code);
	public AcceptSaveDTO getOrderInfo(String order_code);
	public int countShipStatus(String ship_status);
	public void updateShipDate(ShippingDTO sdto);
	public void makeShipCode(ShippingDTO sdto);
	public ShippingDTO getShipDate(String order_code);
	public List<ShippingDTO> instructionList(Criteria cri);
	public int countShipProgressing(String progress_status);
	public void updateShipProgressing(ShippingDTO sdto);
	public List<ShippingDTO> getshipContent(String order_code);
	public void updateSaleStatus(ShippingDTO sdto);
	public List<ShippingDTO> totalCount(Criteria cri);
	public List<ShippingDTO> shippingTotalCount(Criteria cri);
	
	
}
