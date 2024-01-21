package com.mes2.production.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.OutDTO;
import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.Criteria;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.PageVO;
import com.mes2.production.exception.ValidationValueErrorException;
import com.mes2.production.service.InstructionsService;
import com.mes2.production.service.ProductService;
import com.mes2.production.service.ProductionLineService;

@Controller
@RequestMapping("/instructions")
public class InstructionsController {

	@Inject
	private InstructionsService instructionsService;
	
	@Inject
	private ProductionLineService productionLineService;
	
	@Inject
	private ProductService productService;
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	@GetMapping("/start")
	public String startGET() {
		
		InstructionsDTO instructsDto = new InstructionsDTO();
		instructsDto.setCode("20231219A0001");
		instructsDto.setLine(1);
		instructsDto.setMdpCode("A1111");
		instructsDto.setType("F");
		instructsDto.setSopCode("S1111");
		instructsDto.setState("S");
		
		try {
			instructionsService.saveInstructions(instructsDto);
		} catch (ValidationValueErrorException e) {
			
			e.printStackTrace();
		}
		
		return "ok";
	}
	
	//http://localhost:8088/instructions/detail/20231219A0001
	@GetMapping("/detail/{code}")
	public String detailGET(@PathVariable("code")String code) {
		
		//
		return null;
	}
	
	//http://localhost:8088/instructions/search
	@GetMapping("/search")
	public String searchGET(Model model,
			@RequestParam(value="searchStartDate", required = false) Date searchStartDate,
			@RequestParam(value="searchEndDate", required = false) Date searchEndDate,
			@ModelAttribute(value = "searchCode") String searchCode,
			@ModelAttribute(value="searchType") String searchType,
			@RequestParam(value="searchState", required = false) String searchState ,
			Criteria cri) {
		
		log.debug("isController : 넘겨받은 startDate : " + searchStartDate);
		log.debug("isController : 넘겨받은 endDate : " + searchEndDate);
		log.debug("isController : 넘겨받은 searchType : " + searchType);
		log.debug("isController : 넘겨받은 code : " + searchCode);
		log.debug("isController : 넘겨받은 searchState : " + searchState);
		
		if(searchStartDate==null) {
		searchStartDate = Date.valueOf(LocalDate.now());
		}
		if(searchEndDate==null) {
		searchEndDate = Date.valueOf(LocalDate.now().plusWeeks(1));
		}
		
		InstructionsSearchParam param = new InstructionsSearchParam();
		
		if(searchState==null ||searchState.equals("")) {
			param.setState(null);
		}else {
			param.setState(searchState);
		}
		
		param.setCode(searchCode);
		param.setSearchType(searchType);
		param.setStartDate(searchStartDate);
		param.setEndDate(searchEndDate);
		
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(instructionsService.getTotalCountWithSearchParam(param));
		model.addAttribute("pageVO", pageVO);
		
		param.setPage(cri.getPage());
		param.setPageSize(cri.getPageSize());
		
		List<InstructionsDTO> instructions =   instructionsService.findBySearchParam(param);

		model.addAttribute("searchStartDate", searchStartDate);
		model.addAttribute("searchEndDate", searchEndDate);
		model.addAttribute("instructions", instructions);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchState", searchState);
		return "/instructions/instructionList";
	}
	
	@GetMapping("/save")
	public String saveGET() {
		
		return "/instructions/save";
	}
	
	@PostMapping("/save")
	public String savePOST(@ModelAttribute("instructionsDTO")InstructionsDTO instructionsDTO) {
		
		log.debug("InstructionsController : " + instructionsDTO.toString());
		
		try {
			instructionsService.saveInstructions(instructionsDTO);
		} catch (ValidationValueErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/instructions/close";
	}
	
	@GetMapping("/close")
	public String closeGET(){
		
		return "/instructions/close";
	}

	
	//생산요청 확인 페이지 -> 날짜 조회 (구버전 ) 현재 미사용
	//http://localhost:8088/instructions/request
//	@GetMapping("/request")
//	public String requestGET(
//					@RequestParam(value ="searchStartDate" , required = false) Date searchStartDate,
//					@RequestParam(value= "searchEndDate" , required = false) Date searchEndDate, 
//					Model model) {
//		if(searchStartDate==null) {
//			searchStartDate = Date.valueOf(LocalDate.now());
//		}
//		if(searchEndDate==null) {
//			searchEndDate = Date.valueOf(LocalDate.now().plusWeeks(1));
//		}
//		
//		InstructionsSearchParam searchParam = new InstructionsSearchParam();
//		searchParam.setStartDate(searchStartDate);
//		searchParam.setEndDate(searchEndDate);
//		searchParam.setState(InstructionsState.REQUESTED);
//		
//		model.addAttribute("instructions" , instructionsService.findByStateAndDate(searchParam));
//		
//		model.addAttribute("searchStartDate", searchStartDate);
//		model.addAttribute("searchEndDate", searchEndDate);
//
//		return "/instructions/request";
//	}
	
	//http://localhost:8088/instructions/request
	@GetMapping("/request")
	public String requestGET(Model model, Criteria cri, @RequestParam(value="searchCode", required = false)String code) {

		String state="requested";

		InstructionsSearchParam param = new InstructionsSearchParam();
		param.setState(state);
		param.setSearchType("sopCode");
		param.setCode(code);
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(instructionsService.getTotalCountWithSearchParam(param));
		model.addAttribute("pageVO", pageVO);
		
		param.setPage(cri.getPage());
		param.setPageSize(cri.getPageSize());
		
		
		//List<InstructionsDTO> instructions = instructionsService.findByState(state);
		List<InstructionsDTO> instructions = instructionsService.findBySearchParam(param);
		for(InstructionsDTO isDTO : instructions) {
			//해당 수주번호를 가진 자재 요청건 모두 꺼내와서 
			//리스트를 돌면서 넣어줘야함
			Map<Integer, String> requestStatusMap = new HashMap();
			int count =1;

			if(!instructionsService.findBySopCodeForOutDTOList(isDTO.getSopCode()).isEmpty()) {
				List<OutDTO> outDTOS = instructionsService.findBySopCodeForOutDTOList(isDTO.getSopCode());
				for(OutDTO outDTO : outDTOS) {
					if(outDTO.getStatus().equals("complete")) {
						requestStatusMap.put(count, "Y");
					}else if(!outDTO.getStatus().equals("complete")) {
						requestStatusMap.put(count, "N");
					}
					count ++;
				}
			
				if(requestStatusMap.isEmpty()) {
					isDTO.setMaterialStatus("N");
				}else if(requestStatusMap.containsValue("Y") &&!requestStatusMap.containsValue("N") ) {
					isDTO.setMaterialStatus("Y");
				}else if(requestStatusMap.containsValue("N") &&!requestStatusMap.containsValue("Y") ) {
					isDTO.setMaterialStatus("R");
				}else if(requestStatusMap.containsValue("N") &&requestStatusMap.containsValue("Y") ) {
					isDTO.setMaterialStatus("R");
				}				
				log.debug("@@@@@@@@@@@@@@@"+isDTO.getMaterialStatus());
			}else if(instructionsService.findBySopCodeForOutDTOList(isDTO.getSopCode()).isEmpty()) {
				isDTO.setMaterialStatus("N");
			}
		}
		
		
		
		model.addAttribute("searchCode", code);
		model.addAttribute("instructions" , instructions);
		
		
		return "/instructions/request";
	}
	
	@GetMapping("/accept/{sopCode}")
	public String acceptGET(@PathVariable("sopCode") String sopCode , Model model) {
		
		InstructionsDTO findIsDTO = instructionsService.findBySopCode(sopCode,"REQUESTED");
		
		model.addAttribute("instructionDTO", findIsDTO);
		
		
		return "/instructions/accept";
	}
	
	
	@PostMapping("/accept")
	public String acceptPost(@RequestParam(value = "sopCode") String sopCode,@RequestParam(value="dueDate")Date dueDate,
			@RequestParam("line") int line ,HttpServletResponse response) {
		
		//수락 누를시 productionLine, instructions 전부 상태 변환 적용
		instructionsService.acceptRequestedInstructions(sopCode, dueDate, line);
		
		
		
		try {
			response.setContentType("text/html; charset=utf-8");
			String msg = "<script>alert('작업요청이 수락되었습니다');</script>";
			msg+="<script>location.href='/instructions/close';</script>";
			PrintWriter writer = response.getWriter();
			
			writer.print(msg);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	@GetMapping("/resultInfo/{isCode}")
	public String resultGet(@PathVariable("isCode")String isCode, Model model) {
		InstructionsDTO findInstruction = instructionsService.findByCode(isCode);
		log.debug("찾아낸 작업지시 내용 "+findInstruction);
		model.addAttribute("instruction" , findInstruction);
		return "/instructions/result";
	}
	
	@PostMapping("/result")
	public String resultPOST(@RequestParam("isCode") String isCode, @RequestParam("quantity") int quantity,
			@RequestParam("fault") int fault, HttpServletResponse response) {
		
		instructionsService.completeInstructions(isCode,quantity, fault);
		
		
		try {
			response.setContentType("text/html; charset=utf-8"); 
			PrintWriter pw = response.getWriter();
			String msg = "<script>alert('입력이 완료되었습니다.');</script>";
			msg += "<script>location.href='/instructions/close';</script>";
			pw.print(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/materials")
	public String materialsPOST() {
		
		
		
		return null;
	}
	
}
