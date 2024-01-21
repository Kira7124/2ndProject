package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.OrderRequestDTO;
import com.mes2.platform.etc.PageVO;
import com.mes2.platform.etc.SearchDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@Controller
@RequestMapping(value="/platform/*")
public class PlatformController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
	
	@Inject
	private PlatformService pService;
	
	// 로그인 화면 호출
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("result") String result) throws Exception {
		logger.debug("platform.loginGET() 호출");
	}
	
	// 로그인하고 메인페이지로 이동
//	@PostMapping(value="/login")
//	public String loginPOST(MdbDTO mdbDTO, HttpSession session, RedirectAttributes rttr) throws Exception {
//		logger.debug("platform.loginPOST() 호출");
//		
//		logger.debug("mdbDTO: " + mdbDTO);
//		
//		MdbDTO mdto = pService.customerLogin(mdbDTO);
//		
//		if(mdto != null) {
//			session.setAttribute("company_code", mdbDTO.getCompany_code());
//			session.setAttribute("mdto", mdto);
//			return "redirect:/platform/orderList";
//		}
//
//		rttr.addFlashAttribute("result", "loginFail");
//		
//		return "redirect:/platform/login";
//	}

	// 발주(주문) 목록 페이지
	@GetMapping(value="/orderList")
	public String orderListGET(HttpSession session, Model model, SearchDTO sDTO, Criteria cri) throws Exception {
		logger.debug("orderListGET() 호출!!");
		String company_code = (String) session.getAttribute("company_code");
		sDTO.setCompany_code(company_code);
		sDTO.setCri(cri);
		
		// 페이징 처리
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(pService.getTotalOrderCount(sDTO));
		model.addAttribute("pageVO", pageVO);
		
		// 주문 목록 조회
		List<SoiDTO> soiDTO = pService.getOrderList(sDTO);
		model.addAttribute("searchDTO", sDTO);
		model.addAttribute("soiDTO", soiDTO);
		
		return "/platform/orderList";
	}

	//http://localhost:8080/platform//insertOrder
	// 발주(주문) 추가 페이지
	@GetMapping(value="/insertOrder")
	public void insertOrderGET(Model model) throws Exception {
		logger.debug("insertOrderGET() 호출");
		
		// 현재 날짜
		Calendar minDay = Calendar.getInstance();
		Calendar maxDay = Calendar.getInstance();
		minDay.add(Calendar.DATE, 14);
		maxDay.add(Calendar.DATE, 44);
		Date minDate = new Date(minDay.getTimeInMillis());
		Date maxDate = new Date(maxDay.getTimeInMillis());
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String formatMinDate = sdf.format(minDate);
		String formatMaxDate = sdf.format(maxDate);
		
		model.addAttribute("minDay", formatMinDate);
		model.addAttribute("maxDay", formatMaxDate);
	}
	
	// 발주(주문) 추가 페이지
	@PostMapping(value="/insertOrder")
	public String insertOrderPOST(@RequestBody OrderRequestDTO orDTO, HttpSession session) throws Exception {
		logger.debug("insertOrderPOST() 호출");
		logger.debug("@@@@orDTO" + orDTO.toString());
		pService.insertOrder(orDTO, session);
		return "redirect:/platform/orderList";
	}
	
	// 품목 추가 페이지에서 품목 찾기
	@GetMapping(value="/searchList")
	public void searchListGET(@RequestParam(value = "searchType", required = false) String searchType, @RequestParam(value = "search", required = false) String search, Criteria cri, Model model) throws Exception {
		logger.debug("searchListGET() 호출");
		logger.debug("" + cri.toString());
		
		if(searchType != null) {
			// 페이징 처리
			cri.setPageSize(5);
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(pService.getCountInqueryProduct(searchType, search)); // 품목 개수
			model.addAttribute("pageVO", pageVO);
					
			List<MdpDTO> mdpDTO = pService.inqueryProduct(searchType, search, cri);
			model.addAttribute("mdpDTO", mdpDTO);
			model.addAttribute("searchType", searchType);
			model.addAttribute("search", search);
			model.addAttribute("cri", cri);
		}
		
	}
	
	// 주문 상세 페이지
	@GetMapping(value="/orderDetail")
	public void orderDetailGET(@RequestParam("order_code") String order_code, @RequestParam("order_date") String order_date, Model model) throws Exception {
		logger.debug("orderDetailGET() 호출");
		model.addAttribute("soiDTO", pService.getOrderDetail(order_code));
		model.addAttribute("order_date", order_date);
	}
	
	// 주문 수정 페이지
	@GetMapping(value="/modifyOrder")
	public void orderModifyGET(@RequestParam("order_code") String order_code, @RequestParam("order_date") String order_date, Model model) throws Exception {
		logger.debug("orderModifyGET() 호출");
		SoiDTO soiDTO = pService.getOrderDetail(order_code);
		model.addAttribute("soiDTO", soiDTO);
		model.addAttribute("order_date", order_date);
	}
	
	// 주문 수정 페이지
	@PostMapping(value="/modifyOrder")
	public void orderModifyPOST(@RequestBody List<SopDTO> jsonSopList) throws Exception {
		logger.debug("orderModifyPOST() 호출");
		logger.debug("@@@@sopList" + jsonSopList.toString());
		pService.modifyOrder(jsonSopList);
	}
	
	// 주문 삭제 페이지
	@GetMapping(value="/deleteOrder")
	public String deleteOrderGET(@RequestParam("order_code") String order_code) throws Exception {
		logger.debug("deleteOrderGET() 호출");
		pService.deleteOrder(order_code);
		return "redirect:/platform/orderList";
	}
	
	// 비밀번호 수정
	@ResponseBody
	@PostMapping(value="/modifyPw")
	public void modifyPwPOST(@RequestBody ModifyPwDTO mpDTO, HttpSession session) throws Exception{
		logger.debug("modifyPwPOST() 호출");
		mpDTO.setCompany_code((String)session.getAttribute("company_code"));
		pService.modifyPw(mpDTO);
	}
	
	// 수령 완료 처리(버튼으로)
	@GetMapping(value="/completeOrder")
	public void completeOrderGET(@RequestParam("order_code") String order_code, Model model) throws Exception {
		logger.debug("completeOrderGET()호출");
		model.addAttribute("order_code", order_code);
	}
	
	// 서명 조회
	@GetMapping(value="/signature")
	public void getSignatureGET(@RequestParam("order_code") String order_code, Model model) throws Exception {
		logger.debug("getSignatureGET() 호출");
		model.addAttribute("soiDTO", pService.getOrderDetail(order_code));
	}
	
}
