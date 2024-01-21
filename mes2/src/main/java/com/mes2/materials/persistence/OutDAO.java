package com.mes2.materials.persistence;

import java.util.List;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;

public interface OutDAO {
	
	// 출고 목록 조회
	public List<OutDTO> getOutList(OutSearchDTO osDTO) throws Exception;
	
	// 목록 개수 조회
	public int getTotalOutCount(OutSearchDTO osDTO) throws Exception;
	
	// 출고 상세 조회(출고코드 X)
	public OutDTO getOutInfo(String out_index) throws Exception;

	// 출고 품목 재고 조회
	public List<StockDTO> getStockList(String product_code) throws Exception;
	
	// 출고 품목 재고 등록
//	public StockDTO registProduct(int stock_index) throws Exception;
	
	// 출고 품목 입력 시 Stock 테이블 출고 예정 수량에 입력
//	public void updatePlannedQuantity(List<StockDTO> stockDTO) throws Exception;
	
	// 출고 품목 등록
	public void insertOutProduct(String out_code, List<StockDTO> stockList) throws Exception;
	
	// 재고 감소
	public void decreaseStock(List<StockDTO> stockList) throws Exception;
	
	// 출고 목록 업데이트
	public void updateOutInfo(OutDTO outDTO) throws Exception;
	
	// 수주 상태 업데이트
	public void updateProductStatus(String based_code, String product_code) throws Exception;
	
	// 출고 상세 조회(출고코드 O)
	public List<OpDTO> getOutDetail(String out_code) throws Exception;
	
	// 품목 재고 가져오기
	public int getQuantitySum(String product_code) throws Exception;
	
	// 생산 지시 수량 확인
	public int getInsSum(String product_code) throws Exception;
	
	// 생산 지시 등록
	public void insertIns(InsDTO insDTO) throws Exception;
}
