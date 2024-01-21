package com.mes2.materials.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.WarehouseDTO;
import com.mes2.materials.persistence.InDAO;

@Service
public class InServiceImpl implements InService {

	private static final Logger logger = LoggerFactory.getLogger(InServiceImpl.class);

	@Inject
	private InDAO idao;

	@Override
	public int updateIncomingRequest(String in_code, String pd_lot) throws Exception {
		return idao.updateIncomingRequest(in_code, pd_lot);
	}

	@Override
	public List<InDTO> getIncomingStockInfo(String searchType, String keyword, Criteria cri, SearchDTO sdto)
			throws Exception {
		return idao.getAllInboundInfo(searchType, keyword, cri, sdto);
	}


	@Override
	public int totalInCount(Criteria cri, String searchType, String keyword) throws Exception {
		return idao.getInCount(cri, searchType, keyword);
	}

	@Override
	public List<InDTO> searchIn(String searchType, String keyword, Criteria cri) throws Exception {
		return idao.searchIn(searchType, keyword, cri);
	}

	@Override
	public void insertStock(int quantity, String product_code, String pd_lot) throws Exception {
		idao.insertStock(quantity, product_code, pd_lot);

	}

	@Override
	public void updateStockOnIncoming(int quantity, String product_code) throws Exception {
		idao.updateStockOnIncoming(quantity, product_code);

	}

	@Override
	public List<InDTO> selectStock(String product_code) throws Exception {
		return idao.selectStock(product_code);
	}

	@Override
	public InDTO listIncomingProductCodes(String pd_lot) throws Exception {
		return idao.listIncomingProductCodes(pd_lot);
	}

	@Override
	public List<InDTO> InDetailCompletedWarehouse(String searchType, String keyword, Criteria cri, SearchDTO sdto)
			throws Exception {
		return idao.InDetailCompletedWarehouse(searchType, keyword, cri, sdto);
	}

	@Override
	public int inDetailCount(Criteria cri, String searchType, String keyword) throws Exception {

		return idao.inDetailCount(cri, searchType, keyword);
	}

	@Override
	public String selectMaxMaterialsLot(String pd_lot) throws Exception {

		return idao.selectMaxMaterialsLot(pd_lot);
	}

	
	@Override
	public String createRmLOT(String product_code) throws Exception {

		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");

		String inputStrDate = sf.format(Date.valueOf(LocalDate.now()));

		String paramLot = inputStrDate + "-RM-" + product_code + "-";

		System.out.println("instruction : paramCode 값 : " + paramLot);

		String result = selectMaxMaterialsLot(paramLot);

		if (result == null) {
			result = paramLot + "0001";
		} else {
			int tmpCount = Integer.valueOf(result.substring(20));
			   logger.debug("tmpCount 작업 전 값 : "+tmpCount);

			tmpCount += 1;
			logger.debug("tmpCount 작업 전 후 : "+tmpCount);
			result = paramLot + String.format("%04d", tmpCount);
			logger.debug("최종 생성된 LOT : "+result);
		}

		return result;
	}

	@Override
	public List<InDTO> getAllInData(InDTO idto) throws Exception {
		return idao.getAllInData(idto);
	}

	@Override
	public List<InDTO> getInventoryIndex(String in_index) throws Exception {
		return idao.getInventoryIndex(in_index);
	}

	@Override
	public WarehouseDTO warehouseCodeCategory(String category) throws Exception {
		return idao.warehouseCodeCategory(category);
	}
	
	

}
