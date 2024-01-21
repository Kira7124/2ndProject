package com.mes2.production.service;

import java.sql.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.domain.ProductDTO;
import com.mes2.production.etc.ProductSearchParam;
import com.mes2.production.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Inject
	private ProductDAO productDAO;
	
	@Override
	public Date getTime() {
		return productDAO.getTime();
	}

	@Override
	public List<ProductDTO> selectBySearch(ProductSearchParam productSearchParam) {
		return productDAO.selectBySearch(productSearchParam);
	}

	@Override
	public ProductDTO selectByLot(String Lot) {
		return productDAO.selectByLot(Lot);
	}

	@Override
	public int deleteByLot(List<String> lotList) {
		return productDAO.deleteByLot(lotList);
	}

	//LOT 번호 생성 로직 - Lot 번호 형식 : yyyyMMdd-제품코드-생산라인-그날 생산된 작업
	private String createLotNum(InstructionsDTO isDTO) {
		
		
		
		return null;
	}

	//입고 요청하기
	@Override
	public int saveInWarehouse(ProductDTO productDTO) {
		return productDAO.insertInWarehouse(productDTO);
	}

	//페이징용 총 수량 구하기
	@Override
	public int selectBySearchForTotalCount(ProductSearchParam productSearchParam) {
		return productDAO.selectBySearchForTotalCount(productSearchParam);
	}
	
	

	
}
