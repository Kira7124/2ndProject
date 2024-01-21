package com.mes2.platform.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.OrderRequestDTO;
import com.mes2.platform.etc.SearchDTO;

public interface PlatformService {
	// 로그인
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception;
	
	// 품목 목록 조회
	public List<MdpDTO> inqueryProduct(String searchType, String search, Criteria cri) throws Exception;
	
	// 품목 개수 조회(페이징)
	public int getCountInqueryProduct(String searchType, String search) throws Exception;
	
	// 품목 하나 선택
	public MdpDTO registProduct(String product_code) throws Exception;
	
	// 발주 신청
	public void insertOrder(OrderRequestDTO orDTO, HttpSession session) throws Exception;
	
	// 주문 목록 조회
	public List<SoiDTO> getOrderList(SearchDTO sDTO) throws Exception;
	
	// 주문 개수 조회(페이징)
	public int getTotalOrderCount(SearchDTO sDTO) throws Exception;
	
	// 주문 상세 조회
	public SoiDTO getOrderDetail(String order_code) throws Exception;
	
	// 주문 수정
	public void modifyOrder(List<SopDTO> sopList) throws Exception;
	
	// 주문 삭제
	public void deleteOrder(String order_code) throws Exception;
	
	// 비밀번호 변경
	public void modifyPw(ModifyPwDTO mpDTO) throws Exception;

	// 수령 완료(서명으로)
	public void completeOrder(SoiDTO sdto) throws Exception;
	
//	// 수령 완료(버튼으로)
//	public void receiveDelivery(String order_code) throws Exception;
}
