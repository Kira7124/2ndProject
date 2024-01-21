package com.mes2.production.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.mes2.materials.domain.OutDTO;
import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.domain.ProductDTO;
import com.mes2.production.domain.ProductionLineDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.RequestMaterialDTO;
import com.mes2.production.etc.RequestMaterialsDTO;
import com.mes2.production.exception.UpdateFailException;
import com.mes2.production.exception.ValidationValueErrorException;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.persistence.ProductDAO;
import com.mes2.production.persistence.ProductionLineDAO;
import com.mes2.production.vo.InstructionsState;

@Service
public class InstructionsServiceImpl implements InstructionsService {

	private Logger log = LoggerFactory.getLogger(InstructionsServiceImpl.class);
	
	@Inject
	private InstructionsDAO instructionsDAO;
	
	@Inject
	private ProductionLineDAO productionLineDAO;
	
	@Inject
	private ProductDAO productDAO;
	
	
	//현재는 사용하지 않는 메서드
	//@Transactional(rollbackFor = ValidationValueErrorException.class) // 트랜잭션 사용
	@Override
	public void saveInstructions(InstructionsDTO instructionsDTO) throws ValidationValueErrorException {
		//작업지시 등록할때 생산라인도 같이 저장되어야함
		// 즉, 같은 수주번호, 같은 날짜, 같은 종료날짜 등등 예약이 들어가야함 ->
		//한가지만 저장되고 한가지가 문제 발생 (트렌잭션 사용)
		
		log.debug("InstructionsServiceImpl : saveInstructions 동작");
		
		//View에서 입력받은 값을 기반으로 instructionsDTO에 저장
		instructionsDTO.setCode(createIsCode(instructionsDTO.getDueDate(), instructionsDTO.getLine()));
		log.debug("InstructionsService : saveInstructions : 새로 생성된 코드" +instructionsDTO.getCode());
		
		
		//위에서 입력받은 InstructionsDTO를 기반으로 ProductionLineDTO 정보도 입력
		ProductionLineDTO productionLine = new ProductionLineDTO();
		productionLine.setIsCode(instructionsDTO.getCode());
		productionLine.setStartDate(instructionsDTO.getDueDate());
		productionLine.setLine(instructionsDTO.getLine());
		productionLine.setStatus("STANDBY");
		
		Date dueEndDate = calEndDate(instructionsDTO.getDueDate(), instructionsDTO.getSalesQuantity());
		productionLine.setEndDate(dueEndDate);
		
		//instructionsDAO를 통해 DB에 저장
		instructionsDAO.insert(instructionsDTO);
		//product
		productionLineDAO.insertProductionLine(productionLine);
		
		//검증 실패시 Exception 발생 -> 롤백
		validationInstructionAndLine(instructionsDTO, productionLine);
		
	}

	@Override
	public InstructionsDTO findByCode(String code) {
		return instructionsDAO.selectByCode(code);
	}
	
	
	
	

	@Override
	public List<InstructionsDTO> findByStateAndDate(InstructionsSearchParam param) {
		return instructionsDAO.selectByStateAndDate(param);
	}

	@Override
	public List<InstructionsDTO> findBySearchParam(InstructionsSearchParam param) {

		return instructionsDAO.selectByParamCode(param);
	}
	
	
	
	
	
	//@Transactional(rollbackFor = UpdateFailException.class)
	@Override
	public void acceptRequestedInstructions(String sopCode, Date dueDate, int line) {
		//그래서 뭐해야함?
		//-> 수락시 isCode를 이용해서 작업지시, 생산라인 정보 모드 호출
		// 그리고 상태를 전부 대기상태로 변경해야함
		// 그럼 거절했을시에는? 삭제를 할것인가 아니면 Refuse로 둘것인가?
		// -> 기록이 남아야하니까 refuse로 두는 것으로 생각중
		
		InstructionsDTO isDTO = instructionsDAO.selectBySopCode(sopCode, "REQUESTED");
		log.debug(isDTO.toString());
		isDTO.setCode(createIsCode(dueDate, line));
		isDTO.setDueDate(dueDate);
		isDTO.setLine(line);
		isDTO.setTargetQuantity(isDTO.getSalesQuantity()+(isDTO.getSalesQuantity()/10));			

		log.debug("InstructionsService : saveInstructions : 새로 생성된 코드" +isDTO.getCode());
		
		
		//위에서 입력받은 InstructionsDTO를 기반으로 ProductionLineDTO 정보도 입력
		ProductionLineDTO productionLine = new ProductionLineDTO();
		productionLine.setIsCode(isDTO.getCode());
		productionLine.setStartDate(isDTO.getDueDate());
		productionLine.setLine(isDTO.getLine());
		productionLine.setStatus("WAITING");
		productionLine.setEndDate(calEndDate(isDTO.getDueDate(), isDTO.getSalesQuantity()));
		
		
	
		try {
			

			int isUpdateResult = instructionsDAO.updateAccept(isDTO);
			productionLineDAO.insertProductionLine(productionLine);
			updateSuccessCheck(isUpdateResult);
		} catch (UpdateFailException e) {
			e.printStackTrace();
			log.debug("acceptRequestedInstructions 실행 중 예외 발생");
		}

	}
	
	
	

	@Override
	public InstructionsState transType(String enumType) {
		InstructionsState state= null;
		if(enumType.equals("REQUESTED")) {
			state = InstructionsState.REQUESTED;
		}else if(enumType.equals("WAITING")){
			state = InstructionsState.WAITING;
		}else if(enumType.equals("PROGRESSING")){
			state = InstructionsState.PROGRESSING;
		}else if(enumType.equals("COMPLETE")) {
			state = InstructionsState.COMPLETE;
		}
		
		return state;
	}
	
	
	// 작업지시 번호 만들기
	public String createIsCode(Date inputDate, int line) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		
		log.debug("instruction : sf 년도 값 : " +sf.toString());
		
		String inputStrDate = sf.format(inputDate);
		
		String paramCode = inputStrDate + converterLineIntToChar(line);
		
		log.debug("instruction : paramCode 값 : " +paramCode);
		
		String result = instructionsDAO.searchLastIsCode(paramCode);
		
		if(result==null) {
			result = paramCode+"0001";
		}else {
			int tmpCount =Integer.valueOf(result.substring(9));
			
			log.debug("tmpCount 작업 전 값 : "+tmpCount);
			tmpCount+=1;
			
			log.debug("tmpCount 작업 전 후 : "+tmpCount);
			
			result = paramCode+String.format("%04d",tmpCount);
			
			log.debug("최종 생성된 작업시지코드 : "+result);
		}
		
		log.debug("@@@@@@@@@@@검색한 해당 작업 지시번호 : "+ result);
		
		return result;
	}
	
	
	
	// 생산 라인에 따른 알파벳 출력 (작업지시번호 부여용)
	private String converterLineIntToChar(int line) {
		String transDate = null;
		switch(line) {
		case 1:
			transDate ="A";
			break;
		case 2:
			transDate ="B";
			break;
		case 3:
			transDate ="C";
			break;
		case 4:
			transDate ="D";
			break;
		case 5:
			transDate ="E";
			break;
		case 6:
			transDate ="F";
			break;
		case 7:
			transDate ="G";
			break;
		}
		return transDate;
	}
	
	private void validationInstructionAndLine(InstructionsDTO instructions, ProductionLineDTO productionLine) throws ValidationValueErrorException {
		
		if(!instructions.getCode().equals(productionLine.getIsCode())) {
			throw new ValidationValueErrorException("작업지시 코드가 다릅니다.");
		}else if(instructions.getLine()!=productionLine.getLine()) {
			throw new ValidationValueErrorException("생산 라인 번호가 다릅니다");
		}else if(instructions.getDueDate().equals(productionLine.getStartDate())) {
			throw new ValidationValueErrorException("등록한 날짜가 다릅니다");
		}else {
			log.debug("작업지시 & 생산라인 검증 완료 - 이상 없음");
		}
		
	}
	
	
	//LOT넘 
	@Override
	public String createLotCode(Date inputDate,int line , String mdpCode) {
		/*
		 * 
		 * 
		 * 필요한거 
		 * 1. 생산날짜 + 제품번호로 조회  / max count 반환
		 */
		
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		
		log.debug("instruction (product) : sf 년도 값 : " +sf.toString());
		
		String inputStrDate = sf.format(inputDate);
		
		String paramLot = inputStrDate+"-"+converterLineIntToChar(line)+"-"+mdpCode+"-";
		
		log.debug("instruction : paramCode 값 : " +paramLot);
		
		String result = productDAO.searchLastLot(paramLot);
		
		if(result==null) {
			result = paramLot+"0001";
		}else {
			int tmpCount =Integer.valueOf(result.substring(19));
			
			log.debug("tmpCount 작업 전 값 : "+tmpCount);
			tmpCount+=1;
			
			log.debug("tmpCount 작업 전 후 : "+tmpCount);
			
			result = paramLot+String.format("%04d",tmpCount);
			
			log.debug("최종 생성된 LOT : "+result);
		}
		
		log.debug("@@@@@@@@@@@검색한 해당 LotNumber : "+ result);
		
		return result;
	}
	
	//작업 종료 -> 작업지시 상태 업데이트 + 생산품 저장 + 생산라인 업데이트
	//@Transactional
	@Override
	public void completeInstructions(String isCode, int quantity, int fault) {
		//기존 작업지시서 소환
		InstructionsDTO findInstructions = instructionsDAO.selectByCode(isCode);
		
		findInstructions.setState("COMPLETE");
		findInstructions.setQuantity(quantity);
		findInstructions.setFault(fault);
		instructionsDAO.updateComplete(findInstructions);
		
		ProductionLineDTO findProductionLine = productionLineDAO.selectByIsCode(isCode);
		productionLineDAO.updateComplete(findProductionLine);
		
		log.debug("instructionsService : 작업종료 완료 및 상태 변경 완료 ");
		
		
		ProductDTO product = new ProductDTO();
		product.setPd_lot(createLotCode(Date.valueOf(LocalDate.now()), findProductionLine.getLine(), findInstructions.getMdpCode()));
		product.setPd_date(Date.valueOf(LocalDate.now()));
		product.setPd_quantity(quantity);
		product.setPd_mdp_code(findInstructions.getMdpCode());
		product.setPd_soi_id(findInstructions.getSopCode());
		product.setPd_is_code(findInstructions.getCode());
		productDAO.insertProduct(product);
		
		//instructionsDAO.updateSopByIsCode(isCode, "complete");

		log.debug("instructionsService : product 저장 완료");
		
		// 입고 등록하기  
		productDAO.insertInWarehouse(product);
		
	}
	
	
	
	
	@Override
	public List<InstructionsDTO> findByState(String state) {
		return instructionsDAO.selectByState(state);
	}

	private void updateSuccessCheck(int result) throws UpdateFailException {
		if(result==0) {
			throw new UpdateFailException("상태 및 업데이트에 문제가 발생하였습니다.");
		}
	}

	@Override
	public InstructionsDTO findBySopCode(String sopCode, String state) {
		return instructionsDAO.selectBySopCode(sopCode, state);
	}


	//[기존]작업종료날짜 = 시작날짜 +1  -> 수주 수량 1000개당 종료날짜 +1
	private Date calEndDate(Date startDate, int count) {
		
		int num = (count/1000)+1;
		
        // 날짜에 일 더하기
        
        LocalDate futureLocalDate = startDate.toLocalDate().plusDays(num);
        Date endDate = Date.valueOf(futureLocalDate);
		
		return endDate;
	}

	
	//작업진행 업데이트
	public void updateProgressing(String isCode ) {
		
		InstructionsDTO instructions = instructionsDAO.selectByCode(isCode);
		ProductionLineDTO productionLine = productionLineDAO.selectByIsCode(isCode);
		instructions.setState("PROGRESSING");
		productionLine.setStatus("PROGRESSING");
		instructionsDAO.updateState(instructions);
		productionLineDAO.updateState(productionLine);
		
	}
	
	//생산요청 - 자재 요청하기
	@Override
	public void requestMaterials(String sopCode, int quantity) {
		RequestMaterialsDTO requestMaterial = instructionsDAO.selectBySopCodeForMaterials(sopCode);
		
		for(RequestMaterialDTO reqMaterial : requestMaterial.getMaterialList()) {
			instructionsDAO.insertOutWarehouseForMaterials(sopCode, reqMaterial.getProductCode(), reqMaterial.getAmount()*quantity);
		}
		
	}

	//출고요청 상태 조회
	@Override
	public OutDTO findBySopCodeForOutDTO(String sopCode, String productCode) {
		return instructionsDAO.selectByBaseCodeForOutDTO(sopCode, productCode);
	}

	@Override
	public int getTotalCountWithSearchParam(InstructionsSearchParam searchParam) {
		return instructionsDAO.getTotalCountWithSearchParam(searchParam);
	}

	@Override
	public List<OutDTO> findBySopCodeForOutDTOList(String sopCode) {
		return instructionsDAO.selectByBaseCodeForOutDTOList(sopCode);
	}
	
	
	
	
}
