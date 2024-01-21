package com.mes2.materials.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.OpDTO;
import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.domain.OutSearchDTO;
import com.mes2.materials.domain.PageVO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.service.OutService;
import com.mes2.materials.service.StockService;

@Controller
@RequestMapping(value = "/materials/*")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);

	@Inject
	private StockService sService;

	// http://localhost:8088/materials/stockList

	@GetMapping(value="/stockList")
	public void stockListGET(Model model, OutSearchDTO searchDTO, Criteria cri) throws Exception{
		logger.debug("stockListGET() 호출");
		
		// 페이징
		cri.setPageSize(10);
		searchDTO.setCri(cri);
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(sService.getStockListCount(searchDTO));
		model.addAttribute("pageVO", pageVO);
		
		model.addAttribute("searchDTO", searchDTO);
		model.addAttribute("stockList", sService.getStockList(searchDTO));
	}

	@GetMapping(value="/stockDetail")
	public void stockDetailGET(@RequestParam("product_code") String product_code, Model model) throws Exception {
		logger.debug("stockDetailGET() 호출");
		List<StockDTO> stockList = sService.getStockDetail(product_code);
		model.addAttribute("stockList", stockList);
	}
}
