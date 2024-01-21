package com.mes2.sales.service;

import java.util.Date;
import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;

import com.mes2.sales.domain.ShippingDTO;

public interface ShippingService {
	
	
	public List<ShippingDTO> shippingList(Criteria cri);
	public List<String> productStatusCnt(String order_code);
	public void updateShipStatus(ShippingDTO sdto);	
	public List<ShippingDTO> planContent(String order_code);
	public ShippingDTO getId(String order_code);
	public ShippingDTO checkUpdatePw(String user_id,String user_pw, String order_code);
	public int getScheduleDate(Date schedule_date);
	public void updateSchedule(Date scheduled_date, String order_code );
	public AcceptSaveDTO getOrderInfo(String order_code);
	public ShippingDTO countShipStatus();
	public String shipRegister(String order_code);
	public List<ShippingDTO> instructionList(Criteria cri);
	public ShippingDTO countShipProgressing();
	public List<ShippingDTO> shipContent(String order_code);
	public void updateShipDate(Date scheduled_date, String order_code );
	public int totalCount(Criteria cri);
	public int shippingTotalCount(Criteria cri);
	public void outComplete(String order_code);
	
}
