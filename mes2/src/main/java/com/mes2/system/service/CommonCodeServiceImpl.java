package com.mes2.system.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.persistence.CommonCodeDAO;




@Service
public class CommonCodeServiceImpl implements CommonCodeService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeServiceImpl.class);
	
	
	
	@Inject
	private CommonCodeDAO cdao;


	//공통코드리스트
	@Override
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto) {
		logger.debug("S : 공통코드 리스트 DAO 메서드 호출!");
		
		List<CommonCodeDTO> resultDTO = cdao.getCommoncodeList(dto);
		
		return resultDTO;
	}

	
	

	// 공통코드 조회
	@Override
	public CommonCodeDTO CommoncodeInfo(int code_index) {
		logger.debug("S : 공통코드 조회 DAO 메서드 호출!");

		return cdao.getCommoncode(code_index);
	}










	// 공통코드 수정
	@Override
	public void commoncodeUpdate(CommonCodeDTO dto) {
		logger.debug("S : 공통코드 수정 DAO 메서드 호출!");

		cdao.updateCommonCode(dto);
			
	}



	// 공통코드 등록
	@Override
	public void insertCommonCode(CommonCodeDTO dto) {
		logger.debug("S : 공통코드 등록 DAO 메서드호출!");

		cdao.insertCommonCode(dto);
	}



	// 공통코드 삭제
	@Override
	public int deleteCommonCode(CommonCodeDTO dto) {
		logger.debug("S : 공통코드 삭제 DAO 메서드호출!");

		return cdao.deleteCommonCode(dto);
	}



	// 공통코드 검색
	@Override
	public List<CommonCodeDTO> searchCommonCodeList(String searchOption, String searchWord) {
		logger.debug("S : 검색결과 호출!");
		List<CommonCodeDTO> resultDTO = cdao.searchCommonCodelist(searchOption, searchWord);
		return resultDTO;
	}




	
	@Override
	public List<CommonCodeDTO> CommonCodeListPage(Criteria cri) throws Exception {
		
		return cdao.getCommonCodeListPage(cri);
	}




	@Override
	public int totalCommonCodeCount() throws Exception {
		
		return cdao.getCommonCodeCount();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
