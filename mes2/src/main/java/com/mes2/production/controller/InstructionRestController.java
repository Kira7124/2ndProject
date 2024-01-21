package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mes2.materials.domain.OutDTO;
import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.RequestMaterialDTO;
import com.mes2.production.etc.RequestMaterialInfo;
import com.mes2.production.etc.RequestMaterialsDTO;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.persistence.ProductionLineDAO;
import com.mes2.production.service.InstructionsService;

@RestController
@RequestMapping("/restInstruction")
public class InstructionRestController {
	
	private final Logger log  = LoggerFactory.getLogger(InstructionRestController.class);

	@Inject
	private InstructionsService instructionsService;
	
	@Inject
	private ProductionLineDAO productionLineDAO;
	
	@Inject
	private InstructionsDAO instructionsDAO;

	
	@GetMapping("/getInstructions")
	public List<InstructionsDTO> getInstructions(@ModelAttribute("instructionsSearchParam") InstructionsSearchParam isParam) {
		
		List<InstructionsDTO> instructions = instructionsService.findBySearchParam(isParam);
		return instructions;
	}
	
	@GetMapping("/getProduction")
	public String getProductionGET () {
		
		//Date inputDate,int line , String mdpCode
		
		Date test = Date.valueOf(LocalDate.now());
		int line = 1;
		String mdpCode = "PS10001";
		
		return instructionsService.createLotCode(test, line, mdpCode);
		
	}
	//http://localhost:8088/restInstruction/getMaterials
	@GetMapping(value="/getMaterials" , produces ="application/json; charset=utf-8")
	public RequestMaterialsDTO getMaterials(@RequestParam("sopCode") String sopCode , @RequestParam("salesQuantity") int salesQuantity) {
		//String sopCode="ACP-bsp002-ORD-20231231-bsp002-1-100";
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+sopCode);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+salesQuantity);
		
		
		RequestMaterialsDTO rqml = instructionsDAO.selectBySopCodeForMaterials(sopCode);
		
		int precessStatus = 0;
		
		//전체 비교를 위한 Map 객체 소환
		Map<Integer, String> requestStatusMap = new HashMap();
		int count = 1;
		log.debug("@@@@@@@@@@@@@@@@"+rqml.getMaterialList().toString());
		for(RequestMaterialDTO dto : rqml.getMaterialList()) {
			
			log.debug("@@@@@@@@@@@@@ - DTO의 ProductCode값 "+ dto.getProductCode()+"@@@@@@@@@@@@@@");
			
			dto.setTotalAmount(dto.getAmount()*salesQuantity);
			
			//조회해서 상태코드를 보고 상태값 입력해줘야함
			if(instructionsService.findBySopCodeForOutDTO(sopCode,dto.getMaterialCode())==null) {
				log.debug("(_ _ ) instructionService [getMaterials] : 조회 결과 : NULL");
			}else if(instructionsService.findBySopCodeForOutDTO(sopCode ,dto.getMaterialCode())!=null) {
				OutDTO outDTO = instructionsService.findBySopCodeForOutDTO(sopCode,dto.getMaterialCode());
				if(outDTO.getStatus().equals("waiting")) {
					requestStatusMap.put(count, "waiting");
				}else if(outDTO.getStatus().equals("complete")) {
					requestStatusMap.put(count, "complete");
				}
				count ++;
			}
		}
		
		if(requestStatusMap.isEmpty()) {
			rqml.setStatus("empty");
			log.debug("@@@@@@@@@@@@@@ EMPTY @@@@@@@@@@@@@@@@@@@@@@@@@@");
		}
		else if(!requestStatusMap.containsValue("waiting") && requestStatusMap.containsValue("complete")){
            log.debug(" +complete 상태");
            rqml.setStatus("complete");
            
        }else if(requestStatusMap.containsValue("waiting") && !requestStatusMap.containsValue("complete")){
        	log.debug(" waiting 상태");
        	rqml.setStatus("waiting");
            
        }else if(requestStatusMap.containsValue("waiting") && requestStatusMap.containsValue("complete")){
        	log.debug("일부만 완료된 준비중인 상태");
        	rqml.setStatus("progressing");
        	
        }
		
		
		rqml.setSopCode(sopCode);
		rqml.setSalesQuantity(salesQuantity);
		
		return rqml;
	}
	
	@PostMapping("/materials")
	public String materialsPOST() {
		
		
		return null;
	}
	
	@GetMapping("/updateProgressing")
	public String updateProgressing(@RequestParam("isCode") String isCode, @SessionAttribute("id")String userId) {
		
		log.debug("@@@@@@@@@@@@@@@/updateProgressing : 호출@@@@@@@@@@@@@@@@@@@@@");
		log.debug("전달받은 isCode : "+isCode);
		
		InstructionsDTO findInstruction = instructionsDAO.selectByCode(isCode);
		ProductionLineDTO findProductionLine = productionLineDAO.selectByIsCode(isCode);
		
		findInstruction.setEmpId(userId);
		findInstruction.setState("PROGRESSING");
		findProductionLine.setStatus("PROGRESSING");

		instructionsDAO.updateState(findInstruction);
		productionLineDAO.updateState(findProductionLine);
		//instructionsDAO.updateSopByIsCode(isCode, "PROGRESSING");
		
		return "ok";
	}
	
	@PostMapping("/requestMaterials")
	public String requestMaterials(@RequestBody RequestMaterialInfo info) {
		
		log.debug("@@@@@@@@@@@넘겨받은 정보 : "+ info.getSopCode());
		
		RequestMaterialsDTO rqml = instructionsDAO.selectBySopCodeForMaterials(info.getSopCode());
		//추가 수량
		int addQuantity = info.getSalesQuantity()/10;
		for(RequestMaterialDTO dto : rqml.getMaterialList()) {
			dto.setTotalAmount(dto.getAmount()*(info.getSalesQuantity()+addQuantity));
			
			instructionsDAO.insertOutWarehouseForMaterials(info.getSopCode(),dto.getMaterialCode() , dto.getTotalAmount());
			
		}
		
		
		return "ok";
	}
	
}
