package com.mes2.materials.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.materials.domain.Criteria;
import com.mes2.materials.domain.PageVO;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.domain.SearchDTO;
import com.mes2.materials.domain.productDTO;
import com.mes2.materials.service.InService;
import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class PurchaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Inject
	private PurchaseService pService;

	@Inject
	private InService iService;
	
	// http://localhost:8080/materials/purchaselist
	
	@GetMapping(value = "/purchase")
	public void insertPurchaseGET() throws Exception {
	}

	@ResponseBody
	@GetMapping(value = "/getProductCode")
	public List<String> testGET(@RequestParam("category") String category) throws Exception {
		productDTO product = new productDTO();

		List<String> productlist = new ArrayList<String>();

		List<productDTO> purchaselist = pService.selectMaterialCategoryList(category);

		for (productDTO pdto : purchaselist) {
			productlist.add(pdto.getProduct_code());
		}

		return productlist;
	}

	@GetMapping(value = "/productInfo")
	@ResponseBody
	public productDTO getProductByCategory(@RequestParam("productCode") String product_code) throws Exception {
		productDTO product = new productDTO();

		List<String> productlist = new ArrayList<String>();

		return pService.getProductByCategory(product_code);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String insertPurchase(PurchaseDTO pdto, Model model) throws Exception {

		String orders_code = ("ORD-" + pdto.getProduct_code());

		String pd_lot = iService.createRmLOT(pdto.getProduct_code());

		/*
		 * pService.updateQuantity(pdto.getProduct_code(), pdto.getQuantity(),
		 * pdto.getCategory(), pd_lot);
		 */
		pService.MaterialReceipt(pdto.getProduct_code(), pdto.getQuantity(), pd_lot);
		
		pdto.setOrders_code(orders_code);
		pService.purchaseOrder(pdto);
		model.addAttribute("orders_code", orders_code);



		return "redirect:/materials/close";
	}

	@GetMapping(value = "/purchaselist")
	public void listAllGET(Model model, SearchDTO sDTO, Criteria cri,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "keyword", required = false) String keyword) throws Exception {

		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(pService.totalPurchaseCount(cri, searchType, keyword));
		List<PurchaseDTO> purchaselist = pService.searchMaterial(searchType, keyword, cri);

		model.addAttribute("pageVO", pageVO);

		model.addAttribute("purchaselist", purchaselist);

	}

	@PostMapping(value = "/updateStatus")
	public String updateStatus(@RequestParam(value = "orders_index", required = false) int orders_index) throws Exception {
		int purchaselist = pService.updateOrderStatus("complete", orders_index);
		return "redirect:/materials/purchaselist";

	}

	@GetMapping(value = "/inputOrder")
	public String test() throws Exception {

		return "materials/inputOrder";
	}

	@GetMapping(value = "/close")
	public void closePurchase() throws Exception {

	}
}