package com.mes2.sales.service;


import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;


import com.mes2.sales.domain.ShippingDTO;
import com.mes2.sales.persistence.ShippingDAO;

@Service
public class ShippingServiceImpl implements ShippingService {

	private static final Logger logger = LoggerFactory.getLogger(ShippingServiceImpl.class);
	
	@Inject
	private ShippingDAO sdao;
	
	@Override
	public List<ShippingDTO> shippingList(Criteria cri) {
		logger.debug(" S : shippingList(ShippingDTO sdt) ");
		
		List<ShippingDTO> list = sdao.getShippingList(cri);
		
		for(ShippingDTO sdto : list) {
			
			ShippingDTO sd = new ShippingDTO();
			String order_code = sdto.getOrder_code();
			sd.setOrder_code(order_code);
			List<String> cntCheck = productStatusCnt(order_code);
			int count =0;
			
			for(String cnt : cntCheck) {
				String product_status = cnt;
				if(product_status.equals("complete")) {
					count ++;
				}
				
			}
			
			if(count == cntCheck.size()) {
				// 전부다 product_status가 complete인 경우
				// 그 order_code의 shipping에서 ship_status를  instruction으로 변경
				sd.setShip_status("instruction");
				// 위에서order_code를 가지고 와서 shipping에 update해주기 
				updateShipStatus(sd);
			}  else if(count < cntCheck.size() && count >0) {
				// 전부 product_status가 complete인 경우는 아니지만 하나라도 완료인 경우
				// 그 order_code의 shipping에서 ship_status를  waiting으로 변경 
				sd.setShip_status("waiting");
				// 위에서order_code를 가지고 와서 shipping에 update해주기 
				updateShipStatus(sd);
			} else if(count == 0) {
				sd.setShip_status("plan");
				updateShipStatus(sd);
			}
			
			
		}
		
		List<ShippingDTO> ShippingPlanlist = sdao.getShippingList(cri);

		return ShippingPlanlist;
	}
	
	
	
	@Override
	public List<String> productStatusCnt(String order_code) {
		logger.debug(" S :  productStatusCnt(ShippingDTO sdto) ");
		
		return sdao.productStatusCnt(order_code);
	}
	
	@Override
	public void updateShipStatus(ShippingDTO sdto) {
		logger.debug(" S :updateShipStatus(ShippingDTO sdto) ");
		sdao.updateShipStatus(sdto);
		
	}
	
	@Override
	public List<ShippingDTO> planContent(String order_code) {
		logger.debug(" S :planContent(String order_code)");
		return sdao.getPlanContent(order_code);
	}
	
	 
	@Override
		public ShippingDTO getId(String order_code) {
			
			return sdao.getId(order_code);
		}
	
	@Override
	public ShippingDTO checkUpdatePw(String user_id, String user_pw, String order_code) {
     String dbPw = sdao.checkUpdatePw(user_id);
		String check = "";
		if(dbPw.equals(user_pw)) {
			check = "true";
		}else {
			check = "false";
		}
		
		ShippingDTO sdt = sdao.checkOrderDate(order_code);
		sdt.setCheck(check);
		
		return sdt;
	
	}
	
	@Override
	public int getScheduleDate(Date schedule_date) {
		
		int count =0;
		List<String>order_code = sdao.getScheduleDate(schedule_date);
		count = order_code.size();
		return count;
	}
	
	@Override
	public void updateSchedule(Date scheduled_date, String order_code) {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setOrder_code(order_code);
		sdto.setScheduled_date(scheduled_date);
		
		sdao.updateSchedule(sdto);
				
	}
	
	@Override
	public AcceptSaveDTO getOrderInfo(String order_code) {
		
		return sdao.getOrderInfo(order_code);
	}
	
	@Override
	public ShippingDTO countShipStatus() {
		
		ShippingDTO sdto = new ShippingDTO();
		
		sdto.setPlanCnt(sdao.countShipStatus("plan"));
		sdto.setWaitingCnt(sdao.countShipStatus("waiting"));
		sdto.setInstructionCnt(sdao.countShipStatus("instruction"));
		
		return sdto;
	}
	

	
	@Override
	public String shipRegister(String order_code) {
		
		ShippingDTO sd = sdao.getShipDate(order_code);
		sd.setOrder_code(order_code);
		
	    // 지시일 업로드
		sdao.updateShipDate(sd);
		// 출하코드 
		sdao.makeShipCode(sd);
		
		return order_code;
		
	}
	

	
	@Override
	public List<ShippingDTO> instructionList(Criteria cri) {
		List<ShippingDTO> list = sdao.instructionList(cri);

		return list;
	}
	
	
	@Override
	public ShippingDTO countShipProgressing() {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setWaitingCnt(sdao.countShipProgressing("waiting"));
		sdto.setInstructionCnt(sdao.countShipProgressing("shipping"));
		sdto.setCompleteCnt(sdao.countShipProgressing("complete"));
		
		return sdto;
	}
	

	
	@Override
	public List<ShippingDTO> shipContent(String order_code) {
		
		return sdao.getshipContent(order_code);
	}
	
	@Override
	public void updateShipDate(Date scheduled_date, String order_code) {
		ShippingDTO sdto = new ShippingDTO();
		sdto.setScheduled_date(scheduled_date);
		sdto.setOrder_code(order_code);
		sdao.updateShipDate(sdto);
		
	}
	

	
	@Override
	public int totalCount(Criteria cri) {
		List<ShippingDTO> list = sdao.totalCount(cri);
		
		return list.size();
	}
	
	@Override
	public int shippingTotalCount(Criteria cri) {
		List<ShippingDTO> list = sdao.shippingTotalCount(cri);
		
		return list.size();
	}
	
	
	
	@Override
	public void outComplete(String order_code) {
		
		ShippingDTO sdt = new ShippingDTO();
		 sdt.setOrder_code(order_code);
		 sdt.setProgress_status("shipping");
         sdt.setSales_status("deliver");
         sdao.updateSaleStatus(sdt);
         sdao.updateShipProgressing(sdt);
       
	}
	

}
