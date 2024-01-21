package com.mes2.system.persistence;

import java.util.List;

import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface CommonCodeDAO {
	
	
	
	// 공통코드 리스트 출력
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto);
	
	
	//공통코드 조회
	public CommonCodeDTO getCommoncode(int code_index); 
	
	
	//공통코드 수정
	public void updateCommonCode(CommonCodeDTO dto);
	
	
	//공통코드 등록
	public void insertCommonCode(CommonCodeDTO dto);

	//공통코드 삭제
	public int deleteCommonCode(CommonCodeDTO dto);
	
	
	//공통코드 검색
	public List<CommonCodeDTO> searchCommonCodelist(String searchOption, String searchWord);
	
	
	
	//페이징처리 1 -> 페이징처리 수 계산
	public List<CommonCodeDTO> getCommonCodeListPage(int page) throws Exception;
		
		
	//페이징처리 2 -> cri 파라미터 받아서 계산
	public List<CommonCodeDTO> getCommonCodeListPage(Criteria cri) throws Exception;
		
		
	//페이징처리 3 -> 총 코드갯수 계산
	public int getCommonCodeCount() throws Exception;
	
	
	
	
	
}
