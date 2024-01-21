package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.service.ProductionLineService;

@RestController
@RequestMapping("/RestProductionLine")
public class ProductionLineRestController {
	
	private final Logger log = LoggerFactory.getLogger(ProductionLineRestController.class);
	
	@Inject
	private ProductionLineService productionLineService;
	
	@PostMapping("/getEnableLine")
	public List<Integer> getEnableLinePOST(@RequestParam("searchDate") Date searchDate){
		
		log.debug("ProductionLineRestController : 입력받은 날짜 : "+searchDate);
		
		List<Integer> lineList = List.of(1, 2, 3, 4, 5, 6, 7);
		
		// 수정 가능한 리스트로 변환
        List<Integer> remainLineList = new ArrayList<>(lineList);
        
		List<Integer> removeList = new ArrayList();
		
		
		if(searchDate==null) {
			searchDate = Date.valueOf(LocalDate.now());
		}
		
		
		List<ProductionLineDTO> findProductionLineList = productionLineService.findByDateForProduce(searchDate);
		for(ProductionLineDTO productionLine : findProductionLineList) {
			removeList.add(productionLine.getLine());
		}
		
		log.debug("@@@@@@@@@@@@@ 검색된 생산 라인 @@@@@@@@@ : "+removeList.toString());
		remainLineList.removeAll(removeList);
		
		log.debug("@@@@@@@@@@@@@ 엄선된 생산 라인 @@@@@@@@@ : "+remainLineList.toString());
		
		return remainLineList;
	}
	
	

}
