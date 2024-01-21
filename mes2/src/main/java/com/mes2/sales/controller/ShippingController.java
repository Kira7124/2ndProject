package com.mes2.sales.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;
import com.mes2.sales.domain.ShippingDTO;
import com.mes2.sales.domain.pageMaking;
import com.mes2.sales.service.ShippingService;

@Controller
@RequestMapping(value = "/shipping/*")
public class ShippingController {

	private static final Logger logger = LoggerFactory.getLogger(ShippingController.class);

	@Inject
	private ShippingService pService;

	// http://localhost:8080/shipping/shipPlan
	// http://localhost:8088/shipping/shipPlan

	@RequestMapping(value = "/shipPlan")
	public String salesPlan(HttpSession session, Model model, Criteria cri, @RequestParam(name = "user", required = false) String user) {	
		
		String user_id = cri.getUserId();
		if (user != null && user.equals("true")) {
	        user_id = (String) session.getAttribute("id");
         }
        cri.setUserId(user_id);
		List<ShippingDTO> list = pService.shippingList(cri);
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		
		pageMaking pm = new pageMaking();
		pm.setCri(cri);
		pm.setTotalCount(pService.totalCount(cri));
		model.addAttribute("pm", pm);		
		return "/shipping/shipPlan";
	}
	

	
	@RequestMapping(value ="/planContent")
	public @ResponseBody List<ShippingDTO> planContent(String order_code){
		List<ShippingDTO> list = pService.planContent(order_code);
		return list;
	}
	
	
	@RequestMapping(value = "updateIdCheck")
	public @ResponseBody ShippingDTO regIdCheck(String order_code) {		
		ShippingDTO sdt = pService.getId(order_code);
		return sdt;
	}
	
	@RequestMapping(value = "updatePwCheck", method = RequestMethod.POST)
	public @ResponseBody ShippingDTO regPwCheck(String user_pw,String user_id,String order_code ){
		
		ShippingDTO sdt = pService.checkUpdatePw(user_id,user_pw,order_code);
		return sdt;
	}
	

	@RequestMapping(value = "checkSchedule", method = RequestMethod.POST)
	public @ResponseBody Integer checkSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate){
           int count = pService.getScheduleDate(scheduledDate);
           return count;
	}
	
	@RequestMapping(value = "updateSchedule", method = RequestMethod.POST)
	public @ResponseBody String updateSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate, String order_code ){
		
		pService.updateSchedule(scheduledDate, order_code);
		return order_code;
		
	}
	
	
	@RequestMapping(value = "getOrderInfo", method = RequestMethod.POST)
	public @ResponseBody AcceptSaveDTO getOrderInfo(String order_code ){
		
		AcceptSaveDTO adt = pService.getOrderInfo(order_code);
		return adt;
	}
	
	
	@RequestMapping(value = "regShipping" , method = RequestMethod.POST)
	public @ResponseBody String regShipping(String order_code){
		
		
		return pService.shipRegister(order_code);
	}
	
	
	// http://localhost:8080/shipping/shipping
	// http://localhost:8088/shipping/shipping
	@RequestMapping(value = "/shipping")
	public String shipping(HttpSession session, Model model, Criteria cri, @RequestParam(name = "user", required = false) String user)  {	
		
		String user_id = cri.getUserId();
		if (user != null && user.equals("true")) {
		user_id = (String) session.getAttribute("id");
		 }
		
		cri.setUserId(user_id);
		List<ShippingDTO> list = pService.instructionList(cri);
		ShippingDTO sdto = pService.countShipProgressing();
		pageMaking pm = new pageMaking();
		pm.setCri(cri);
		pm.setTotalCount(pService.shippingTotalCount(cri));
		model.addAttribute("pm", pm);
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		
		
		
		return "/shipping/shipping";
	}
	

	@RequestMapping(value ="/shipContent")
	public @ResponseBody List<ShippingDTO> shipContent(String order_code){
		List<ShippingDTO> list = pService.shipContent(order_code);
		return list;
	}
	
	
	@RequestMapping(value = "changeShipSchedule", method = RequestMethod.POST)
	public @ResponseBody String changeShipSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate, String order_code ){
		
		pService.updateSchedule(scheduledDate, order_code);
		pService.updateShipDate(scheduledDate, order_code);
		return order_code;
		
	} 

	
	@RequestMapping(value = "/outComplete", method = RequestMethod.POST)
	public @ResponseBody String outComplete(String order_code) {			
		pService.outComplete(order_code);
		return order_code;
	}
	

}
