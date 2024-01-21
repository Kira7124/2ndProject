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

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {

	private static final Logger logger = LoggerFactory.getLogger(OutController.class);

	@Inject
	private OutService oService;

	// http://localhost:8088/materials/outList

	// 출고 목록 리스트 - GET
	@GetMapping(value = "/outList")
	public void outListGET(Model model, OutDTO odto, OutSearchDTO osDTO, Criteria cri ) throws Exception {
		logger.debug("outListGET() 호출 ");
		logger.debug("@@osDTO: " + osDTO);
		cri.setPageSize(7);
		osDTO.setCri(cri);
		
		// 페이징 처리
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(oService.getTotalOutCount(osDTO));
		model.addAttribute("pageVO", pageVO);

		// 출고 목록 조회
		model.addAttribute("osDTO", osDTO);
		model.addAttribute("oList", oService.getOutList(osDTO));
	}

	// 출고 상세 리스트 - GET
	@GetMapping(value = "/outDetail")
	public String outDetailGET(@RequestParam("out_index") String out_index, @RequestParam("out_code") String out_code, Model model) throws Exception {
		logger.debug("outDetailGET() 호출");

		OutDTO outDTO = null;
		List<OpDTO> opList = null;
		if (out_code == "") {
			outDTO = oService.getOutInfo(out_index);
			model.addAttribute("outDTO", outDTO);
			return "/materials/insertOut";
		} else {
			opList = oService.getOutDetail(out_code);
			model.addAttribute("opList", opList);
			return "/materials/outDetail";
		}
	}
	
	// 출고 품목 재고 조회 - GET
	@GetMapping(value = "/stockInsert")
	public void stockInsertGET(@RequestParam("product_code") String product_code, Model model) throws Exception {
		logger.debug("stockInsertGET() 호출");
		List<StockDTO> stockList = oService.getStockList(product_code);
		model.addAttribute("stockList", stockList);
	}
	
	// 출고 등록 - POST
	@PostMapping(value = "/insertOut")
	public String insertOutPOST(@RequestParam Map<String, String> stockMap, RedirectAttributes rttr) throws Exception {
		logger.debug("insertOutPOST() 호출");
		logger.debug("stockMap: " + stockMap);
		
		String out_index = stockMap.get("out_index");
		String product_code = stockMap.get("StockDTO[0].product_code");
		List<StockDTO> stockList = new ArrayList<StockDTO>();
		
		for(int i=0; i<(stockMap.size() - 1)/4; i++) {
			StockDTO sDTO = new StockDTO();
			sDTO.setStock_index(Integer.parseInt(stockMap.get("StockDTO["+i+"].stock_index")));
			sDTO.setPd_lot(stockMap.get("StockDTO["+i+"].pd_lot"));
			sDTO.setProduct_code(product_code);
			sDTO.setQuantity(Integer.parseInt(stockMap.get("quantity")));
			sDTO.setUseQuantity(Integer.parseInt(stockMap.get("StockDTO["+i+"].useQuantity")));
			stockList.add(sDTO);
		}
		
		int quantitySum = oService.insertOut(out_index, stockList);

		if(product_code.contains("PS")) {
			int insSum = oService.getInsSum(product_code); // 생산 지시 수량 확인하기

			rttr.addFlashAttribute("quantitySum", quantitySum);
			rttr.addFlashAttribute("product_code", product_code);
			rttr.addFlashAttribute("insSum", insSum);
		}
		
		rttr.addFlashAttribute("result", "SUCCESS");
		
		return "redirect:/materials/outList";
	}

}
