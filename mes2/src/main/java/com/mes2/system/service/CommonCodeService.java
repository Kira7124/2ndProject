package com.mes2.system.service;

import java.util.List;

import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface CommonCodeService {
	
	//공통코드리스트 출력
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto);
	
	
	//공통코드수정 실행
	public void commoncodeUpdate(CommonCodeDTO dto);
	
	//공통코드조회 실행
	public CommonCodeDTO CommoncodeInfo(int code_index);
	
	
	//공통코드등록 실행
	public void insertCommonCode(CommonCodeDTO dto);
	
	//공통코드삭제 실행
	public int deleteCommonCode(CommonCodeDTO dto);
	
	//공통코드검색 실행
	public List<CommonCodeDTO> searchCommonCodeList(String searchOption, String searchWord);
	
	
	// 페이징처리 1 
	public List<CommonCodeDTO> CommonCodeListPage(Criteria cri) throws Exception;
		
	// 페이징처리 2
	public int totalCommonCodeCount() throws Exception;
	
	
	

}
