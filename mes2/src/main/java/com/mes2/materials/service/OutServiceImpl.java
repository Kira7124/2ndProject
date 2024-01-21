package com.mes2.materials.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.persistence.OutDAO;

@Service
public class OutServiceImpl implements OutService {

	private static final Logger logger = LoggerFactory.getLogger(OutServiceImpl.class);
	
	@Inject
	private OutDAO odao;

	// 출고 목록 조회
	@Override
	public List<OutDTO> getOutList(OutSearchDTO osDTO) throws Exception {
		logger.debug("S: getOutList() 호출");
		return odao.getOutList(osDTO);
	}
	
	// 목록 개수 조회
	@Override
	public int getTotalOutCount(OutSearchDTO osDTO) throws Exception {
		logger.debug("S: getTotalOutCount() 호출");
		return odao.getTotalOutCount(osDTO);
	}
	
	// 출고 상세 조회(출고코드 X)
	@Override
	public OutDTO getOutInfo(String out_index) throws Exception {
		logger.debug("S: getOutInfo() 호출");
		return odao.getOutInfo(out_index);
	}
	
	// 출고 품목 재고 조회
	@Override
	public List<StockDTO> getStockList(String product_code) throws Exception {
		return odao.getStockList(product_code);
	}
	
	// 출고 등록
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertOut(String out_index, List<StockDTO> stockList) throws Exception {
		logger.debug("insertOut() 호출");
		
		// 출고 코드 생성
		String out_code = makeOutCode(out_index, stockList);
		logger.debug("out_code" +  out_code);
		
		// 출고 품목 테이블 등록
		odao.insertOutProduct(out_code, stockList);
		
		// 재고 테이블 재고 감소
		odao.decreaseStock(stockList);
		
		// 출고 목록 수정(출고 코드 입력, status complete로 변경)
		OutDTO outDTO = odao.getOutInfo(out_index); // out_index에 해당하는 목록 불러오기
		outDTO.setOut_code(out_code); // 출고코드 세팅
		outDTO.setStatus("complete"); // 상태 완료로 변경
		odao.updateOutInfo(outDTO);
		
		String based_code = outDTO.getBased_code();
		String product_code = outDTO.getProduct_code();
		

		// out_type이 S(출하)인 경우
		// based_code, product_code 가지고 sales_order_product 테이블 product_status 상태 complete로 변경
		if(outDTO.getOut_type().equals("S")) {
			odao.updateProductStatus(based_code, product_code);
		}
		
		// 재고 테이블에서 전체 재고 가져오기(n개 이하, 생산중 상품이면 생산지시)
		return odao.getQuantitySum(product_code);
		
	}
	
	// 출고 코드 생성
	private String makeOutCode(String out_index, List<StockDTO> stockList) throws Exception {
		logger.debug("makeOut_code() 호출");
		
		// 일자
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dtfToday = today.format(dtf);
		
		// 품목 코드
		String product_code = stockList.get(0).getProduct_code();
		
		// 전체 수량
		int quantity = stockList.get(0).getQuantity();
		
        return dtfToday + "-" + product_code + "-" + quantity + "-" + out_index;
	}
	
	// 출고 상세 조회(출고코드 O)
	@Override
	public List<OpDTO> getOutDetail(String out_code) throws Exception {
		logger.debug("S: getOutDetail() 호출");
		return odao.getOutDetail(out_code);
	}
	
	// 생산 지시 수량 확인
	@Override
	public int getInsSum(String product_code) throws Exception {
		logger.debug("S: getInsSum() 호출");
		return odao.getInsSum(product_code);
	}
	
	// 생산 지시 등록
	@Override
	public void insertIns(InsDTO insDTO) throws Exception {
		logger.debug("S: insertIns() 호출");
		String sop_code = makeSopCode(insDTO);
		insDTO.setSop_code(sop_code);
		odao.insertIns(insDTO);
	}
	
	// 생산 요청 코드 생성
	private String makeSopCode(InsDTO insDTO) throws Exception {
		// 일자 + 품목코드 + 요청 수량 + index
		// 일자
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		String dtfToday = today.format(dtf);
		
		// 품목코드
		String mdp_code = insDTO.getMdp_code();
		
		// 요청 수량
		int quantity = insDTO.getSales_quantity();
		
		return dtfToday + "-" + mdp_code + "-" + quantity;
	}
}
