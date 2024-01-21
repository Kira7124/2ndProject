package com.mes2.materials.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.materials.domain.InDTO;
import com.mes2.materials.domain.PurchaseDTO;

@Service
public class ExcelUtils {

	
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
	
	 public Workbook addDataToWorkbook(List<PurchaseDTO> data) {
		 
		 	Workbook work = new XSSFWorkbook();
	        Sheet sheet = work.createSheet("Purchase Data");

	        Row headerRow = sheet.createRow(0);
	        String[] columns = {"orders_code", "product_code", "name", "cost", "price", "category", "quantity", "regdate", "status"};
	        for (int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	        }

	        int rowNum = 1;
	        for (PurchaseDTO purchase : data) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(purchase.getOrders_code());
	            row.createCell(1).setCellValue(purchase.getProduct_code());
	            row.createCell(2).setCellValue(purchase.getName());
	            row.createCell(3).setCellValue(purchase.getCost());
	            row.createCell(4).setCellValue(purchase.getPrice());
	            row.createCell(5).setCellValue(purchase.getCategory());
	            row.createCell(6).setCellValue(purchase.getQuantity());
	            row.createCell(7).setCellValue(purchase.getRegdate());
	            row.createCell(8).setCellValue(purchase.getStatus());
	        }
	        
	        return work;
	    }

	 public Workbook addDataToWorkbook2(List<InDTO> data2) {
		 Workbook work = new XSSFWorkbook();
		 Sheet sheet2 = work.createSheet("In Data");

         Row headerRow2 = sheet2.createRow(0);
         String[] columns2 = {"product_code", "in_code", "pd_lot", "name", "quantity", "unit", "category", "in_regdate"};
         for (int i = 0; i < columns2.length; i++) {
             Cell cell2 = headerRow2.createCell(i);
             cell2.setCellValue(columns2[i]);
         } 

         int rowNum2 = 1;
         for (InDTO in : data2) {
             Row row2 = sheet2.createRow(rowNum2++);
             row2.createCell(0).setCellValue(in.getProduct_code());
             row2.createCell(1).setCellValue(in.getIn_code());
             row2.createCell(2).setCellValue(in.getPd_lot());
             row2.createCell(3).setCellValue(in.getName());
             row2.createCell(4).setCellValue(in.getQuantity());
             row2.createCell(5).setCellValue(in.getUnit());
             row2.createCell(6).setCellValue(in.getCategory());
             row2.createCell(7).setCellValue(in.getIn_regdate());
         }
         
         return work;
     } 
}
	   
