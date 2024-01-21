package com.mes2.metadata.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.PageVO;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_amountDTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.service.Amount_Service;



//http://localhost:8088/amount/firstpage
@Controller
@RequestMapping(value = "/amount/*")
public class Amount_Controller {
	
private static final Logger logger = LoggerFactory.getLogger(Amount_Controller.class);
	
	@Inject
	private Amount_Service aService;
	
		// bom관리 페이지, 모든 거래처정보리스트 호출
		@RequestMapping(value="/firstpage", method= {RequestMethod.POST, RequestMethod.GET})
		public String productdataGET(Model model, Criteria cri, alllistDTO aDTO) throws Exception{
			
			aDTO.setCri(cri);
			
			//페이징 pageVO 작업
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(aService.gettotalcount(aDTO));
			
			//리스트 가져오기
			List<md_amountDTO> amountList = aService.getlist(aDTO);
			
			//모델로 보내기
			model.addAttribute("aDTO", aDTO);
			model.addAttribute("amountList", amountList);
			model.addAttribute("pageVO", pageVO);
			
			
			return "/meta_data/amountinfo";
				
		}
		
		
		// bom추가 하는 곳
		@RequestMapping(value="/insertamount", method=RequestMethod.POST)
		public String amountinsertPOST(md_amountDTO dto, Model model) throws Exception{
			
			logger.debug("여기까지옴" + dto);
			aService.amountinsert(dto);

			return "redirect:/amount/firstpage";
			
			
		}
		
		// bom 수정
		@RequestMapping(value="/updateamount", method=RequestMethod.POST)
		public String amountupdatePOST(md_amountDTO dto, Model model) throws Exception{
			
			logger.debug("여기까지옴2" + dto);
			aService.amountupdate(dto);
				
			return "redirect:/amount/firstpage";			
		}
			
			
		// bom 삭제
		@RequestMapping(value="/deleteamount", method=RequestMethod.POST)
		public String amountdeletePOST(md_amountDTO dto) throws Exception{
			
			logger.debug("여기까지옴3" + dto);
			aService.amountdelete(dto);		
			
			return "redirect:/amount/firstpage";			
		}
		

		@ResponseBody	
		@RequestMapping(value = "/amount2/product_code", method = RequestMethod.GET)
		public ResponseEntity<List<md_productDTO>> product_code() throws Exception{
				
			List<md_productDTO> abc = aService.selectbox();
			logger.debug("완제품코드들1" + abc);
			
			return new ResponseEntity<List<md_productDTO>>(abc,HttpStatus.OK);		
		}
		
		@ResponseBody	
		@RequestMapping(value = "/amount2/product_code2", method = RequestMethod.GET)
		public ResponseEntity<List<md_productDTO>> product_code2() throws Exception{
				
			List<md_productDTO> abc2 = aService.selectbox2();
			logger.debug("원재료코드들2" + abc2);
			
			return new ResponseEntity<List<md_productDTO>>(abc2,HttpStatus.OK);		
		}
		
		@ResponseBody	
		@RequestMapping(value = "/amount2/unit", method = RequestMethod.GET)
		public ResponseEntity<String> unit(md_amountDTO dto) throws Exception{
						
			
			String abc3 = aService.selectbox3(dto.getProduct_code());
			logger.debug("단위코드들3" +abc3);
			
			return new ResponseEntity<String>(abc3,HttpStatus.OK);		
		}
		
		@ResponseBody	
		@RequestMapping(value = "/amount2/updunit", method = RequestMethod.GET)
		public ResponseEntity<String> updunit(md_amountDTO dto) throws Exception{
						
			
			String abc4 = aService.selectbox4(dto.getProduct_code());
			logger.debug("단위코드들4" +abc4);
			
			return new ResponseEntity<String>(abc4,HttpStatus.OK);		
		}
}
