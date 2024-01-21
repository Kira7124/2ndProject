package com.mes2.metadata.controller;


import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.PageVO;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.md_businessDTO;
import com.mes2.metadata.service.Business_Service;

//http://localhost:8088/business/firstpage
@Controller
@RequestMapping(value = "/business/*")
public class Business_Controller {

	private static final Logger logger = LoggerFactory.getLogger(Product_Controller.class);
	
	@Inject
	private Business_Service bService;
	
	// 거래처관리 페이지, 모든 거래처정보리스트 호출
		@RequestMapping(value="/firstpage", method= {RequestMethod.POST, RequestMethod.GET})
		public String businessdataGET(Model model, Criteria cri, alllistDTO aDTO) throws Exception{
			
			aDTO.setCri(cri);
			
			//페이징 pageVO 작업
			PageVO pageVO = new PageVO();
			pageVO.setCri(cri);
			pageVO.setTotalCount(bService.gettotalcount(aDTO));
			
			//리스트 가져오기
			List<md_businessDTO> businessList = bService.getlist(aDTO);
			
			//모델로 보내기
			model.addAttribute("aDTO", aDTO);
			model.addAttribute("businessList", businessList);
			model.addAttribute("pageVO", pageVO);
			
			
			return "/meta_data/businessinfo";
				
		}
		
		
		// 품목 추가 하는 곳
		@RequestMapping(value="/insertbusiness", method=RequestMethod.POST)
		public String productinsertPOST(md_businessDTO dto, Model model) throws Exception{
			
			logger.debug("여기까지옴" + dto);
			bService.businessinsert(dto);

			return "redirect:/business/firstpage";
			
			
		}
		
		// 품목 수정
		@RequestMapping(value="/updatebusiness", method=RequestMethod.POST)
		public String productupdatePOST(md_businessDTO dto, Model model) throws Exception{
			
			logger.debug("여기까지옴2" + dto);
			bService.businessupdate(dto);
				
			return "redirect:/business/firstpage";			
		}
			
			
		// 품목 삭제
		@RequestMapping(value="/deletebusiness", method=RequestMethod.POST)
		public String productdeletePOST(md_businessDTO dto) throws Exception{
			
			bService.businessdelete(dto);		
			
			return "redirect:/business/firstpage";			
		}
		
		
				

	
}
