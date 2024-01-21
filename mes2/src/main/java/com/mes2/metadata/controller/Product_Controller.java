package com.mes2.metadata.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.mes2.metadata.domain.Criteria;
import com.mes2.metadata.domain.PageVO;
import com.mes2.metadata.domain.alllistDTO;
import com.mes2.metadata.domain.common_DTO;
import com.mes2.metadata.domain.md_productDTO;
import com.mes2.metadata.service.Product_Service;


//http://localhost:8088/product/firstpage
@Controller
@RequestMapping(value = "/product/*")
public class  Product_Controller{
	
	private static final Logger logger = LoggerFactory.getLogger(Product_Controller.class);
	
	@Inject
	private Product_Service mService;
	
	@Inject
	private ServletContext  servletContext;
	
	
	// 품목관리 페이지, 모든 품목정보리스트 호출
	@RequestMapping(value="/firstpage", method= {RequestMethod.POST, RequestMethod.GET})
	public String productdataGET(Model model, Criteria cri, alllistDTO aDTO) throws Exception{
		
		aDTO.setCri(cri);
		
		//페이징 pageVO 작업
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(mService.gettotalcount(aDTO));
		
		//리스트 가져오기
		List<md_productDTO> productList = mService.getlist(aDTO);
		
		//모델로 보내기
		model.addAttribute("aDTO", aDTO);
		model.addAttribute("productList", productList);
		model.addAttribute("pageVO", pageVO);
		
		
		return "/meta_data/productinfo";
			
	}
	
	
	// 품목 추가 하는 곳
	@RequestMapping(value="/insertproduct", method=RequestMethod.POST)
	public String productinsertPOST(md_productDTO dto, MultipartHttpServletRequest multiRequest,
            Model model) throws Exception{
		
		
		
		// 파일 정보를 저장
		String ofileName = fileProcess(multiRequest);
		dto.setOfileName(ofileName);
		
		mService.productinsert(dto);
		logger.debug("00붙이기ㅣ2222222222222"+ dto.getProduct_code());
		
		return "redirect:/product/firstpage";
		
		
	}
	
	// 품목 수정
	@RequestMapping(value="/updateproduct", method=RequestMethod.POST)
	public String productupdatePOST(md_productDTO dto, MultipartHttpServletRequest multiRequest, Model model) throws Exception{
			
		String ofileName = fileProcess(multiRequest);
		dto.setOfileName(ofileName);
		mService.productupdate(dto);
			
		return "redirect:/product/firstpage";			
	}
		
		
	// 품목 삭제
	@RequestMapping(value="/deleteproduct", method=RequestMethod.POST)
	public String productdeletePOST(md_productDTO dto) throws Exception{
		
		mService.productdelete(dto);		
		
		return "redirect:/product/firstpage";			
	}
	
	//품목 카테고리 가져오기
	@ResponseBody	
	@RequestMapping(value = "/product2/category", method = RequestMethod.GET)
	public ResponseEntity<List<common_DTO>> productcate() throws Exception{
			
		List<common_DTO> abc = mService.selectbox();
								
		return new ResponseEntity<List<common_DTO>>(abc,HttpStatus.OK);		
	}
	
	// 품목 단위 가져오기
	@ResponseBody	
	@RequestMapping(value = "/product2/unit", method = RequestMethod.GET)
	public ResponseEntity<List<common_DTO>> productunit() throws Exception{
				
		List<common_DTO> abc2 = mService.selectbox2();
					
		return new ResponseEntity<List<common_DTO>>(abc2,HttpStatus.OK);		
	}
	
		
		
	//파일정보(이름)을 저장, 파일업로드 처리	
	private String fileProcess(MultipartHttpServletRequest multiRequest) throws Exception{
					
		String ofileName = null;			
		String realPath = servletContext.getRealPath("/resources/img/metadata/");
		
		Iterator<String> fileNames = multiRequest.getFileNames();
			while(fileNames.hasNext()) {
				// 파라메터 이름을 저장
				String fileName =  fileNames.next();
				logger.debug(" fileName : "+fileName);				
				
				// 전달된 파일이름에 해당하는 MultipartFile정보 저장
				MultipartFile mFile = multiRequest.getFile(fileName);
				ofileName = mFile.getOriginalFilename();
				logger.debug(" oFileName : "+ofileName);
				
				
				// 실제 폴더 생성
				File file = new File(realPath + ofileName);
				// 파일업로드
				if(mFile.getSize() != 0) { //첨부파일이 있을때
					if(!file.exists()) { // 파일,디렉터리(폴더)가 존재하는지 체크
						if(file.getParentFile().mkdirs()) {
							file.createNewFile();
						}
					}// exists
					mFile.transferTo(file);
				}// getSize
			}//while
					
		return ofileName;
	}	


}
