package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@RestController // RestController =  (@ResponseBody + @Controller)
@RequestMapping(value="/restPlatform/*")
public class PlatformRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformRestController.class);
	
	@Inject
	private PlatformService pService;
	
	@Inject
	private ServletContext servletContext;
	
	// 발주 추가 시 품목 등록
	@PostMapping(value ="/registProduct")
	public MdpDTO registProduct(@RequestParam("product_code") String product_code) throws Exception {
		logger.debug("registProduct() 호출");
		logger.debug("product_code" + product_code);
		MdpDTO mdpDTO = pService.registProduct(product_code);
		return mdpDTO;
	}
	
	// 수령 완료 처리(서명으로)
	@PostMapping(value="/completeOrder")
	public ResponseEntity<String> testCompleteOrderPOST(@RequestParam("order_code") String order_code, @RequestParam("file") MultipartFile file) throws Exception {
		logger.debug("testCompleteOrderPOST()호출");
		
		// 전달된 파일 X
		if(file.isEmpty()) {
			return new ResponseEntity<String>("No File", HttpStatus.BAD_REQUEST);
		}
		
		// 전달된 파일 O
		try {
			// 저장 폴더 생성
			String realPath = servletContext.getRealPath("/resources/img/platform/signature");
			logger.debug("realPath: " + realPath);
			File uploadPath = new File(realPath);
			if (!uploadPath.exists()) { // 파일이 올라갈 폴더가 존재하는지 체크
                uploadPath.mkdirs(); // 없으면 폴더 생성
            }

            // 파일 저장
            String fileName = file.getOriginalFilename();
            File uploadFile = new File(uploadPath + "/" + fileName); // 업로드할 파일
            file.transferTo(uploadFile);
            
            
            // 디비에 파일명 등록
            SoiDTO sdto = new SoiDTO();
            sdto.setOrder_code(order_code);
            sdto.setSign_file_name(fileName);
            
            pService.completeOrder(sdto);
            
            return new ResponseEntity<String>("success", HttpStatus.OK);
            
		} catch (Exception e) {
			return new ResponseEntity<String>("error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	// 발주 건 수령 완료처리(버튼으로만)
//	@PostMapping(value = "/receiveDelivery")
//	public void receiveDelivery(@RequestParam("order_code") String order_code) throws Exception {
//		logger.debug("receiveDelivery() 호출");
//		pService.receiveDelivery(order_code);
//	}
	
}
