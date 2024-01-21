package com.mes2.materials.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.persistence.StockDAO;

@Service
public class StockServiceImple implements StockService {
	
	private static final Logger logger = LoggerFactory.getLogger(StockServiceImple.class);
	
	@Inject
	private StockDAO sdao;
	
	// 재고 목록 조회
	@Override
	public List<StockDTO> getStockList(OutSearchDTO searchDTO) throws Exception {
		logger.debug("S: getStockList() 조회");
		return sdao.getStockList(searchDTO);
	}
	
	// 재고 목록 개수
	@Override
	public int getStockListCount(OutSearchDTO searchDTO) throws Exception {
		logger.debug("S: getStockListCount() 조회");
		return sdao.getStockListCount(searchDTO);
	}
	
	// 재고 상세 조회
	@Override
	public List<StockDTO> getStockDetail(String product_code) throws Exception {
		logger.debug("S: getStockDetail() 호출");
		return sdao.getStockDetail(product_code);
	}

}
