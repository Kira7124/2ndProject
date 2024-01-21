package com.mes2.materials.service;

import java.util.List;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;

public interface OutService {

	// 출고 목록 조회
	public List<OutDTO> getOutList(OutSearchDTO osDTO) throws Exception;
	
	// 목록 개수 조회
	public int getTotalOutCount(OutSearchDTO osDTO) throws Exception;
	
	// 출고 상세 조회(출고코드 X)
	public OutDTO getOutInfo(String out_index) throws Exception;
 	
	// 출고 품목 재고 조회
	public List<StockDTO> getStockList(String product_code) throws Exception;
	
	// 출고 등록
	public int insertOut(String out_index, List<StockDTO> stockList) throws Exception;
	
	// 출고 상세 조회(출고코드 O)
	public List<OpDTO> getOutDetail(String out_code) throws Exception;
	
	// 생산 지시 수량 확인
	public int getInsSum(String product_code) throws Exception;
	
	// 생산 지시 등록
	public void insertIns(InsDTO insDTO) throws Exception;
}
