package com.mes2.materials.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.service.InService;
import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class ExcelDownloadController {

	@Inject
	private PurchaseService pService;
	
	
	@Inject
	private InService iService;
	
	@Inject
	private ExcelUtils exService;
	
	
	@GetMapping(value = "/materials")
	public ResponseEntity<byte[]> materials(HttpServletResponse response, PurchaseDTO pdto) throws Exception {
	   
	    List<PurchaseDTO> data = pService.getAllPurchaseData(pdto);
	     Workbook work = exService.addDataToWorkbook(data);

	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    work.write(byteArrayOutputStream);
	    byte[] excelBytes = byteArrayOutputStream.toByteArray();
	    work.close();

	    String filename = URLEncoder.encode("자재_리스트.xlsx", StandardCharsets.UTF_8);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

	    return ResponseEntity.ok().headers(headers).contentLength(excelBytes.length).body(excelBytes);
	}
	
	@GetMapping(value = "/inventory")
	   public ResponseEntity<byte[]> inventory(HttpServletResponse response, InDTO idto) throws Exception {
	  
	       List<InDTO> data2 = iService.getAllInData(idto);
	       Workbook work =  exService.addDataToWorkbook2(data2);

	       ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
	       work.write(byteArrayOutputStream2);
	       byte[] excelBytes2 = byteArrayOutputStream2.toByteArray();
	       work.close();

	       String filename2 = URLEncoder.encode("입고_리스트.xlsx", StandardCharsets.UTF_8);

	       HttpHeaders headers2 = new HttpHeaders();
	       headers2.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	       headers2.setContentDispositionFormData("attachment", filename2);
	       headers2.setCacheControl("must-revalidate, post-check=0, pre-check=0");

	       return ResponseEntity.ok().headers(headers2).contentLength(excelBytes2.length).body(excelBytes2);
	   }

	

}