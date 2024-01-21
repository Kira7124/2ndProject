package com.mes2.production.service;



import java.sql.Date;
import java.util.List;

import com.mes2.materials.domain.OutDTO;
import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionResultParam;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.exception.ValidationValueErrorException;
import com.mes2.production.vo.InstructionsState;

public interface InstructionsService {

	public void saveInstructions(InstructionsDTO instructionsDTO) throws ValidationValueErrorException;
	
	public InstructionsDTO findByCode(String code);
	
	public InstructionsState transType(String enumType);
	
	public List<InstructionsDTO> findBySearchParam(InstructionsSearchParam param);
	
	public String createIsCode(Date inputDate, int line);
	
	public List<InstructionsDTO> findByStateAndDate(InstructionsSearchParam param);
	
	public void acceptRequestedInstructions(String sopCode , Date dueDate, int line);
	
	public String createLotCode(Date inputDate,int line , String mdpCode);
	
	public void completeInstructions(String isCode , int quantity, int fault);
	
	public List<InstructionsDTO> findByState(String state);
	
	public InstructionsDTO findBySopCode(String sopCode, String state);
	
	//자재 요청하기
	public void requestMaterials(String productCode, int quantity);
	
	//자재 출고 내용 조회
	public OutDTO findBySopCodeForOutDTO(String sopCode, String productCode);
	
	public int getTotalCountWithSearchParam(InstructionsSearchParam searchParam);
	
	//자재 출고 내용 조회
	public List<OutDTO> findBySopCodeForOutDTOList(String sopCode);
	
}
