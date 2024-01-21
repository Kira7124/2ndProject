package com.mes2.production.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.production.domain.ProductDTO;
import com.mes2.production.persistence.OtherDAO;
import com.mes2.production.service.ProductService;


@Controller
@RequestMapping("/validation")
public class ValidationController {

	@Inject
	private ProductService productService;
	
	@Inject
	private OtherDAO otherDAO;
	
	@GetMapping("/search")
	public String searchValidation() {
		
		return "/validation/home";
		
	}
	
	
	//http://localhost:8088/validation/submit
	@GetMapping("/submit")
	public String submitValidation(@RequestParam("lot") String lot , Model model) {
		
		ProductDTO product =  productService.selectByLot(lot);
		
		model.addAttribute(product);
		
		return "/validation/validResult";
	}
	
	public String submitValidationPost(@RequestParam("quantity") int quantity, @RequestParam("lot") String lot) {
		
		ProductDTO product = productService.selectByLot(lot);
		product.setPd_qc_result(quantity);
		//productSe
		return null;
	}
	
	//http://localhost:8088/validation/createLotTest
	@ResponseBody
	@GetMapping("/createLotTest")
	public String createRmLot() {
		
		String productCode = "RS10001";
		
		String result =otherDAO.createRawMaterialsLot(productCode);
		
		return result;
	}
}
