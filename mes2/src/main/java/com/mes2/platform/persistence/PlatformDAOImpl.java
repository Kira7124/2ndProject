package com.mes2.platform.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.etc.Criteria;
import com.mes2.platform.etc.ModifyPwDTO;
import com.mes2.platform.etc.SearchDTO;

@Repository
public class PlatformDAOImpl implements PlatformDAO {

	private static final Logger logger = LoggerFactory.getLogger(PlatformDAOImpl.class);
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.mes2.platform.mapper.PlatformMapper";

	// 로그인
	@Override
	public MdbDTO customerLogin(MdbDTO mdto) throws Exception {
		logger.debug("DAO: customerLogin() 호출");
		return sqlSession.selectOne(NAMESPACE + ".read", mdto);
	}

	// 발주 신청 시 품목 목록 조회
	@Override
	public List<MdpDTO> inqueryProduct(String searchType, String search, Criteria cri) throws Exception {
		logger.debug("DAO: inqueryProduct() 호출");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("search", search);
		searchMap.put("cri", cri);
		
		return sqlSession.selectList(NAMESPACE + ".inqueryProduct", searchMap);
	}
	
	// 발주 신청 시 품목 개수 조회
	@Override
	public int getCountInqueryProduct(String searchType, String search) throws Exception {
		logger.debug("DAO: getCountInqueryProduct() 호출");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType", searchType);
		searchMap.put("search", search);
		
		return sqlSession.selectOne(NAMESPACE + ".getCountInqueryProduct", searchMap);
	}

	// 품목 하나 등록
	@Override
	public MdpDTO registProduct(String product_code) throws Exception {
		logger.debug("DAO: registProduct() 호출");
		return sqlSession.selectOne(NAMESPACE + ".selectProduct", product_code);
	}

	// 발주 데이터 입력
	@Override
	public void insertOrder(SoiDTO soiDTO) throws Exception {
		logger.debug("DAO: insertOrder() 호출");
		logger.debug("@@@ soiDTO: " + soiDTO.toString());
		sqlSession.insert(NAMESPACE + ".insertOrder", soiDTO);
	}
	
	// 발주 품목 데이터 입력
	@Override
	public void insertOrderProduct(List<SopDTO> sopList) throws Exception {
		logger.debug("DAO: insertOrderProduct() 호출");
		logger.debug("@@@ sopList: " + sopList.toString());
		sqlSession.insert(NAMESPACE + ".insertOrderProduct", sopList);
	}
	
	// 금일 마지막 주문번호(주문번호에 사용)
	@Override
	public String countTodayOrder(String checkCode) throws Exception {
		logger.debug("DAO: countTodayOrder() 호출");
		return sqlSession.selectOne(NAMESPACE + ".countTodayOrder", checkCode);
	}

	// 주문 목록 조회
	@Override
	public List<SoiDTO> getOrderList(SearchDTO sDTO) throws Exception {
		logger.debug("DAO: getOrderList() 호출");
		return sqlSession.selectList(NAMESPACE + ".getOrderList", sDTO);
	}
	
	// 주문 개수 조회(페이징)
	@Override
	public int getTotalOrderCount(SearchDTO sDTO) throws Exception {
		logger.debug("DAO: getTotalOrderCount() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getTotalOrderCount", sDTO);
	}

	// 주문 상세 조회
	@Override
	public SoiDTO getOrderDetail(String order_code) throws Exception {
		logger.debug("DAO: getOrderDetail() 호출");
		return sqlSession.selectOne(NAMESPACE + ".getOrderDetail", order_code);
	}
	
	// 발주 공통코드
	@Override
	public String getCommonCode() throws Exception {
		logger.debug("DAO: getCommonCode() 호출");
		return sqlSession.selectOne(NAMESPACE+".getCommonCode");
	}
	
	// 기존 주문 주문번호, 품목코드 조회(주문 수정에 사용)
	@Override
	public List<SopDTO> getOrderProduct(String order_code) throws Exception {
		logger.debug("DAO: getOrderProduct() 호출");
		return sqlSession.selectList(NAMESPACE + ".getOrderProduct", order_code);
	}

	// 주문 수정 시 삭제된 품목 delete
	@Override
	public void deleteOrderProduct(SopDTO sopDTO) throws Exception {
		logger.debug("DAO: deleteOrderProduct() 호출");
		sqlSession.delete(NAMESPACE + ".deleteOrderProduct", sopDTO);
	}
	
	// 주문 수정
	@Override
//	public void modifyOrder(SopDTO sopDTO) throws Exception {
	public void modifyOrder(List<SopDTO> sopList) throws Exception {
		logger.debug("DAO: modifyOrder() 호출");
		logger.debug("@@@@@@@sopList: " + sopList);
		sqlSession.update(NAMESPACE + ".modifyOrder", sopList);
	}
	
	// 주문 수정 일자 업데이트
	@Override
	public void updateOrderDate(String order_code) throws Exception {
		logger.debug("DAO: updateOrderDate() 호출");
		sqlSession.update(NAMESPACE + ".updateOrderDate", order_code);
	}

	// 주문 삭제
	@Override
	public void deleteOrder(String order_code) throws Exception {
		logger.debug("DAO: deleteOrder() 호출");
		sqlSession.delete(NAMESPACE + ".deleteOrder", order_code);
	}

	// 비밀번호 변경
	@Override
	public void modifyPw(ModifyPwDTO mdDTO) throws Exception {
		logger.debug("DAO: modifyPw() 호출");
		sqlSession.update(NAMESPACE + ".modifyPw", mdDTO);
	}
	
	// 수령 완료(출하 테이블 업데이트)
	@Override
	public void completeShipping(SoiDTO sdto) throws Exception {
		logger.debug("DAO: ");
		sqlSession.update(NAMESPACE + ".completeShipping", sdto);
		
	}
	
	// 수령 완료(수주 테이블 업데이트)
	@Override
	public void completeOrder(SoiDTO sdto) throws Exception {
		logger.debug("DAO: ");
		sqlSession.update(NAMESPACE + ".completeOrder", sdto);
	}

}
