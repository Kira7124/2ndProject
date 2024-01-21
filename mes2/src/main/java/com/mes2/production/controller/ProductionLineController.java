package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.Criteria;
import com.mes2.production.etc.PageVO;
import com.mes2.production.etc.ProductionLineSearchParam;
import com.mes2.production.service.ProductionLineService;

@Controller
public class ProductionLineController {
	
	private final Logger log = LoggerFactory.getLogger(ProductionLineController.class);

	@Inject
	private ProductionLineService productionLineService;
	
	@GetMapping("/productionLine/search")
	public String productionLineSearchGET(@RequestParam(value="searchStartDate", required = false) Date searchStartDate,
			@RequestParam(value="searchEndDate", required=false)Date searchEndDate,
			@RequestParam(value="searchCode", required=false) String searchCode,
			@RequestParam(value="searchStatus", required=false) String searchStatus,
			Criteria cri,
			Model model) {
			
		
		if(searchStartDate==null) {
		searchStartDate = Date.valueOf(LocalDate.now());
		}
		if(searchEndDate==null) {
		searchEndDate = Date.valueOf(LocalDate.now().plusWeeks(1));
		}
		
		
		ProductionLineSearchParam param = new ProductionLineSearchParam();
		param.setStartDate(searchStartDate);
		param.setEndDate(searchEndDate);
		param.setIsCode(searchCode);
		param.setStatus(searchStatus);
		
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(productionLineService.findBySearchParamForTotalCount(param));
		log.debug("@@@@@@@@@@@@@ 여기까지는 호출@@@@@@@@@@@@@@@@@@@@@@@");
		model.addAttribute("pageVO", pageVO);
		
		param.setPage(cri.getPage());
		param.setPageSize(cri.getPageSize());
		
		
		
		
		List<ProductionLineDTO> findProductionLineList = productionLineService.findBySearchParam(param);
		model.addAttribute("productionLineList",findProductionLineList);
		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);
		model.addAttribute("searchCode", searchCode);
		model.addAttribute("searchStatus", searchStatus);
		return "/productionLine/productionLineList";
	}
	
	//http://localhost:8088/productionLine/test
	@GetMapping("/productionLine/test")
	public String productionLineTestGET(@RequestParam(value="searchStartDate", required = false) Date searchStartDate, Model model) {
		
		List<Integer> lineList = List.of(1, 2, 3, 4, 5, 6, 7);
		
		// 수정 가능한 리스트로 변환
        List<Integer> mutableLineList = new ArrayList<>(lineList);
        
		List<Integer> removeList = new ArrayList();
		
		
		if(searchStartDate==null) {
			searchStartDate = Date.valueOf(LocalDate.now());
		}
		
		
		List<ProductionLineDTO> findProductionLineList = productionLineService.findByDateForProduce(searchStartDate);
		for(ProductionLineDTO productionLine : findProductionLineList) {
			removeList.add(productionLine.getLine());
		}
		
		log.debug("@@@@@@@@@@@@@ 검색된 생산 라인 @@@@@@@@@ : "+removeList.toString());
		mutableLineList.removeAll(removeList);
		
		log.debug("@@@@@@@@@@@@@ 엄선된 생산 라인 @@@@@@@@@ : "+mutableLineList.toString());
		model.addAttribute("lineList",mutableLineList);
		return "/productionLine/test";
	}
	
}
