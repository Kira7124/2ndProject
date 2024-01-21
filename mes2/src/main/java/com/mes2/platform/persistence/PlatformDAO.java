package com.mes2.platform.persistence;

import java.util.List;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.SearchDTO;

public interface PlatformDAO {
	// 로그인
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception;
	
	// 발주 신청 시 품목 목록 조회
	public List<MdpDTO> inqueryProduct(String searchType, String search, Criteria cri) throws Exception;
	
	// 발주 신청 시 품목 개수 조회(페이징)
	public int getCountInqueryProduct(String searchType, String search) throws Exception;
	
	// 품목 하나 등록
	public MdpDTO registProduct(String product_code) throws Exception;
	
	// 발주 데이터 입력
	public void insertOrder(SoiDTO soiDTO) throws Exception;
	
	// 발주 품목 데이터 입력
	public void insertOrderProduct(List<SopDTO> sopList) throws Exception;
	
	// 발주 공통코드
	public String getCommonCode() throws Exception;
	
	// 금일 마지막 주문번호
	public String countTodayOrder(String checkCode) throws Exception;
	
	// 주문 목록 조회
	public List<SoiDTO> getOrderList(SearchDTO sDTO) throws Exception;
	
	// 주문 개수 조회(페이징)
	public int getTotalOrderCount(SearchDTO sDTO) throws Exception;
	
	// 주문 상세 조회
	public SoiDTO getOrderDetail(String order_code) throws Exception;

	// 기존 주문 주문번호, 품목코드 가져오기
	public List<SopDTO> getOrderProduct(String order_code) throws Exception;
	
	// 주문 수정 시 삭제된 품목 delete
	public void deleteOrderProduct(SopDTO sopDTO) throws Exception;
	
	// 주문 수정 일자 업데이트
	public void updateOrderDate(String order_code) throws Exception;

	// 주문 수정
//	public void modifyOrder(SopDTO sopDTO) throws Exception;
	public void modifyOrder(List<SopDTO> sopList) throws Exception;
	
	// 주문 삭제
	public void deleteOrder(String order_code) throws Exception;
	
	// 비밀번호 변경
	public void modifyPw(ModifyPwDTO mdDTO) throws Exception;
	
	// 수령 완료(출하 테이블 업데이트)
	public void completeShipping(SoiDTO sdto) throws Exception;
	
	// 수령 완료(수주 테이블 업데이트)
	public void completeOrder(SoiDTO sdto) throws Exception;
	
}
