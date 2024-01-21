package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.materials.domain.InsDTO;
import com.mes2.materials.domain.StockDTO;
import com.mes2.materials.service.OutService;

@RestController
@RequestMapping("/rOut/*")
public class OutRestController {
	private static final Logger logger = LoggerFactory.getLogger(OutRestController.class);
	
	@Inject
	OutService oService;
	
//	@PostMapping("/registProduct")
//	public StockDTO registProductPOST(@RequestBody List<StockDTO> stockDTO) throws Exception {
//		logger.debug("registProductPOST() 호출");
//		logger.debug("stockDTO: " + stockDTO);
//		oService.registProduct(stockDTO);
//		return null;
//	}
	
	// 생산 지시 등록
	@PostMapping(value="/instruction")
	public ResponseEntity<Void> insertInstructions(@RequestBody InsDTO insDTO) throws Exception {
		logger.debug("REST: insertInstructions() 호출");
		logger.debug("insDTO: " + insDTO);
		oService.insertIns(insDTO);
		
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}
