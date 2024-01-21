package com.mes2.sales.service;


import java.util.Calendar;
import java.util.Date;

import java.util.List;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;

import com.mes2.sales.persistence.SalesDAO;

@Service
public class SalesServiceImpl implements SalesService {

	private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);
	
	@Inject
	private SalesDAO sdao;
	
	@Override
	public List<SalesDTO> salesList(Criteria cri) {
		logger.debug(" S : salesList() ");
		List<SalesDTO> list = sdao.getSalesList(cri);		
		return sdao.getSalesList(cri);
	}
	
	@Override
	public List<SalesDTO> PlanContent(String order_code) {
		logger.debug(" S : PlanContent(String order_code) ");
		return sdao.getPlanContent(order_code);
	}
	
	@Override
	public void rejectSales(List<String> odList) {
		logger.debug(" S : rejectSales(String order_code) ");
		sdao.rejectSales(odList);
		
	}
	
	@Override
	public SalesDTO stockQuantity(SalesDTO sd) {
		logger.debug(" S : stockQuantity(SalesDTO sd) ");
		return sdao.getStockQuantity(sd);
	}
	

	
	@Override
	public void registerPlan(PlanRegisterDTO pdto) {
		logger.debug(" S : registerPlan(PlanRegisterDTO pdto) ");	
		sdao.registerPlan(pdto);
		
		
		
	}
	
	@Override
	public String checkRegPw(String user_id,String upser_pw) {
		logger.debug(" S : checkRegPw(String user_id) ");
		String dbPw = sdao.checkRegPw(user_id);
		
		if(dbPw.equals(upser_pw)) {
			return "true";
		}else {
			return "false";
		}
		
	}
	
	@Override
	public List<SalesDTO> getProdctCode(List<String> list) {
		logger.debug(" S : getProdctCode(List<String> list) ");
		List<SalesDTO> dto = sdao.getProdctCode(list);
		
		return dto;
	}
	
	@Override
	public void makeSalesCode(List<SalesDTO> sd) {
		logger.debug(" S : makeSalesCode(List<SalesDTO> sd) ");
		
		sdao.makeSalesCode(sd);
	}
	

	
	@Override
	public List<SalesDTO> acceptContent(String order_code) {
		logger.debug(" S : acceptContent(String order_code) ");
		return sdao.getAcceptContent(order_code);
	}
	
	
	@Override
	public void changeProductStatus(SalesDTO sd) {
		logger.debug(" S : changeProductStatus(SalesDTO sd) ");
		sdao.changeProductStatus(sd);
	}
	
	@Override
	public void stockReg(SalesDTO sd) {
		logger.debug(" S :stockReg(SalesDTO sd)");
		// 출고테이블에 등록
		sdao.stockReg(sd);
		
		// 상태값 변경
		sdao.changeProductStatus(sd);
		
		
	}
	
	@Override
	public void productInst(SalesDTO sd) {
		logger.debug(" S : productInst(SalesDTO sd) ");
		sdao.productInst(sd);
		// 값 변경
		sdao.changeProductStatus(sd);
		
		
		
		
		
	}
	
	@Override
	public void updateStockQuan(SalesDTO sd) {
		logger.debug(" S :updateStockQuan(SalesDTO sd) ");
		sdao.updateStockQuan(sd);
		// 창고에서 값 빼기 
		
	}
	
	@Override
	public void insertShippingPlan(PlanRegisterDTO pdto) {
		logger.debug(" insertShippingPlan(PlanRegisterDTO pdto) ");
		List<String> orderList = pdto.getOrder_code();
		
		for(String order_code: orderList) {
			SalesDTO sdt = new SalesDTO();
			Date order_date = sdao.checkOrdeDate(order_code);
			Calendar cal = Calendar.getInstance();
			cal.setTime(order_date);
			cal.add(Calendar.DATE, -4);
			sdt.setScheduled_date(cal.getTime());
			sdt.setOrder_code(order_code);			
			sdao.insertShippingPlan(sdt);
		}
		
	}
	

	
	@Override
	public SalesDTO salesPlanCnt() {
		SalesDTO sdt = new SalesDTO();
		Criteria cri = new Criteria();
		cri.setSales_status("requested");
		cri.setNewOrder("true");
		sdt.setNewCnt(sdao.getPlanNewCnt(cri));
		cri.setNewOrder("false");
		sdt.setWaitingCnt(sdao.getPlanNewCnt(cri));
		return sdt;
	}
	
	

	
	@Override
	public SalesDTO proCnt() {
		SalesDTO dto = new SalesDTO();
		Criteria cri = new Criteria();
		cri.setSales_status("accept");
        cri.setNewOrder("true");
        dto.setNewCnt(sdao.getPlanNewCnt(cri));	
		
		cri.setNewOrder("false");
					
		cri.setInstructions("Y");
		dto.setCompleteCnt(sdao.getPlanNewCnt(cri));	
	
		cri.setInstructions("N");
		dto.setWaitingCnt(sdao.getPlanNewCnt(cri));
	
		
		
		return dto;
	}
	

	
	@Override
	public AcceptSaveDTO orderInfo(String order_code) {
		
		return sdao.getOrderInfo(order_code);
	}
	
	@Override
	public String instructSales(List<SalesDTO> list) {
		
		
		
		SalesDTO sdt = list.get(0);

        String order_code = sdt.getOrder_code();
		sdao.updateInstruction(order_code);
		
		for(SalesDTO dto : list) {
			
			
			if (dto != null) {
		        if ("stock".equals(dto.getProcessing_reg())) {
		            dto.setProduct_status("progressing");
		            stockReg(dto);
		        } else if ("production".equals(dto.getProcessing_reg())) {
		            
		            dto.setProduct_status("progressing");
		            dto.setLack_quantity(dto.getSales_quantity());
		            productInst(dto);
		            stockReg(dto);
		        } else if ("multi".equals(dto.getProcessing_reg())) {
		            dto.setProduct_status("progressing");
		            int stock_quantity = stockQuantity(dto).getStock_quantity();
		            int lack_quantity = (dto.getSales_quantity() - stock_quantity);
		            dto.setLack_quantity(lack_quantity);
		            stockReg(dto);
		            productInst(dto);
		        } else if ("N".equals(dto.getProcessing_reg())) {
		            // 아무 동작이 필요하지 않은 경우
		        }
		    }
			
			
		}
		
		return order_code;
	}
	
@Override
	public int totalCount(Criteria cri) {
	  
	  
	List<SalesDTO> list =sdao.listCount(cri);
	 int totalCount =  list.size();
	 return totalCount;
	}

@Override
public SalesDTO getRegUser(String order_code) {
	
	return sdao.getRegUser(order_code);
}

@Override
public AcceptSaveDTO orderPlanInfo(String order_code) {
	
	return sdao.getOrderPlanInfo(order_code);
}
}
