package com.mes2.platform.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.OrderRequestDTO;
import com.mes2.platform.etc.SearchDTO;
import com.mes2.platform.persistence.PlatformDAO;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private PlatformDAO pdao;
	
	// 로그인
	@Override
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception {
		logger.debug("S: customerLogin() 호출");
		return pdao.customerLogin(mdto);
	}

	// 품목 목록 조회
	@Override
	public List<MdpDTO> inqueryProduct(String searchType, String search, Criteria cri) throws Exception {
		logger.debug("S: inqueryProduct() 호출");
		return pdao.inqueryProduct(searchType, search, cri);
	}
	
	// 품목 개수 조회(페이징)
	@Override
	public int getCountInqueryProduct(String searchType, String search) throws Exception {
		logger.debug("S: getCountInqueryProduct() 호출");
		return pdao.getCountInqueryProduct(searchType, search);
	}

	// 품목 하나 선택
	@Override
	public MdpDTO registProduct(String product_code) throws Exception {
		logger.debug("S: registProduct() 호출");
		return pdao.registProduct(product_code);
	}

	// 발주 신청
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertOrder(OrderRequestDTO orDTO, HttpSession session) throws Exception {
		String order_code = makeOrderCode(session);
		Date order_date = Date.valueOf(orDTO.getOrder_date());
		
		SoiDTO soiDTO = new SoiDTO();
		soiDTO.setOrder_date(order_date);
		soiDTO.setOrder_code(order_code);
		soiDTO.setCompany_code((String)session.getAttribute("company_code"));
		
		List<SopDTO> sopList = orDTO.getSopList();
		
		for(SopDTO sopDTO : sopList) {
			sopDTO.setOrder_code(order_code);
		}
		
		pdao.insertOrder(soiDTO);
		pdao.insertOrderProduct(sopList);
	}
	
	// 주문코드 생성
	private String makeOrderCode(HttpSession session) throws Exception {
		logger.debug("S: makeOrderCode() 호출");
		
		// 발주 공통코드
		String common_code = pdao.getCommonCode();
		
		// 날짜 계산
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dtfToday = today.format(dtf);
		String company_code = (String) session.getAttribute("company_code");
		String checkCode = dtfToday + "-" + company_code;
		
		// 금일 주문번호 max
		String lastOrder_code = pdao.countTodayOrder(checkCode);
		
		// 마지막 주문번호 인덱스 계산
		int index = 0;
		
		if(lastOrder_code == null) {
			index = 1;
		} else {
			index = Integer.parseInt(lastOrder_code.substring(lastOrder_code.lastIndexOf("-")+1)) + 1;
		}
		
		String order_code = common_code + "-" + dtfToday + "-" + session.getAttribute("company_code") + "-" + index;
		
		return order_code;
	}

	// 주문 목록 조회
	@Override
	public List<SoiDTO> getOrderList(SearchDTO sDTO) throws Exception {
		logger.debug("S: getOrderList() 호출");
		return pdao.getOrderList(sDTO);
	}
	
	// 주문 개수 조회(페이징)
	@Override
	public int getTotalOrderCount(SearchDTO sDTO) throws Exception {
		logger.debug("S: getTotalOrderCount() 호출");
		return pdao.getTotalOrderCount(sDTO);
	}

	// 주문 상세 조회
	@Override
	public SoiDTO getOrderDetail(String order_code) throws Exception {
		logger.debug("S: getOrderDetail() 호출");
		return pdao.getOrderDetail(order_code);
	}

	// 주문 수정
	@Override
	@Transactional(rollbackFor = Exception.class)/*롤백 하지 않을 예외 지정(rollbackFor = 예외발생 클래스명 )*/
	public void modifyOrder(List<SopDTO> sopList) throws Exception {
		logger.debug("S: modifyOrder() 호출");
		
		String order_code = sopList.get(0).getOrder_code();
		
		// 기존 주문 주문번호, 품목코드 가져오기
		List<SopDTO> beforeList = pdao.getOrderProduct(order_code);
			
		// 수정 주문, 기존 주문 품목코드 비교해서 삭제된 품목은 delete
		for(SopDTO bDTO : beforeList) {
			boolean found = false; // 기본적으로 삭제
			
			for(SopDTO modifyDTO : sopList) {
				if(bDTO.getProduct_code().equals(modifyDTO.getProduct_code())) {
					found = true; // 같은 품목 있으면 true로 변경
//					pdao.modifyOrder(modifyDTO);
					break;
				}
			}
			
			if(!found) { // 같은 품목이 없으면 삭제
				pdao.deleteOrderProduct(bDTO);
			}
		}
		
		// 나머지 수정된 품목 수량 update
		pdao.modifyOrder(sopList);
		
		// update_date 일자 수정
		pdao.updateOrderDate(order_code);
	}

	// 주문 삭제
	@Override
	public void deleteOrder(String order_code) throws Exception {
		logger.debug("S: deleteOrder() 호출");
		pdao.deleteOrder(order_code);
	}

	// 비밀번호 변경
	@Override
	public void modifyPw(ModifyPwDTO mpDTO) throws Exception {
		logger.debug("S: modifyPw() 호출");
		pdao.modifyPw(mpDTO);
	}
	
	// 수령 완료(서명으로)
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void completeOrder(SoiDTO sdto) throws Exception {
		logger.debug("S: completeOrder() 호출");
		pdao.completeShipping(sdto); // 출하 테이블 업데이트(progress_status는 complete로, confirm_status는 파일명으로)
		pdao.completeOrder(sdto); // 수주 테이블 업데이트(order_status는 complete로, sign_file_name은 파일명으로)
	}
	
//	// 수령 완료(버튼으로)
//	@Override
//	public void receiveDelivery(String order_code) throws Exception {
//		logger.debug("S: receiveDelivery() 호출");
//		pdao.receiveDelivery(order_code);
//		pdao.changeOrderStatus(order_code);
//	}
	
}
