package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PageVO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.service.InService;
import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class InController {
	
	// http://localhost:8080/system/login
	private static final Logger logger = LoggerFactory.getLogger(InController.class);

	@Inject
	private InService iService;
	@Inject
	private PurchaseService pService;
	
	// http://localhost:8080/materials/inlist
	@GetMapping(value = "/in")
	public void insertInGET() throws Exception {
	}

	@RequestMapping(value = "/updateInStatus", method = RequestMethod.POST)
	public String insertInPOST(@RequestParam("in_pd_lot") String pd_lot, Model model) throws Exception {

		InDTO idto = iService.listIncomingProductCodes(pd_lot);
		if (!idto.getStatus().equals("complete")) {

			  
			 iService.insertStock(idto.getQuantity(), idto.getProduct_code(), idto.getPd_lot());
			 
			 idto.setPd_lot(iService.createRmLOT(idto.getProduct_code()));
			 

			String in_code = ("IN-" + pd_lot);
			idto.setIn_code(in_code);
			iService.updateIncomingRequest(in_code, pd_lot);
			model.addAttribute("in_code", in_code);

		}

		idto.setPd_lot(iService.selectMaxMaterialsLot(pd_lot));

		return "redirect:/materials/inDetailList";
	}

	@GetMapping(value = "/inlist")
	public void listAllGET(Model model, SearchDTO sDTO, Criteria cri,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword) throws Exception {

		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(iService.totalInCount(cri, searchType, keyword));
		List<InDTO> inlist = iService.searchIn(searchType, keyword, cri);

		model.addAttribute("pageVO", pageVO);
		model.addAttribute("inlist", inlist);

	}

	// http://localhost:8080/materials/inDetailList
	@GetMapping(value = "/inDetailList")
	public void inDetailListGET(Model model, SearchDTO sdto, Criteria cri,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword) throws Exception {

		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(iService.inDetailCount(cri, searchType, keyword));
		List<InDTO> inDetailList = iService.InDetailCompletedWarehouse(searchType, keyword, cri, sdto);
		
		for(InDTO in : inDetailList) {
			in.setWarehouse_code(iService.warehouseCodeCategory(in.getCategory()).getWarehouse_code());
		}

		model.addAttribute("pageVO", pageVO);

		model.addAttribute("inDetailList", inDetailList);

	}


}
