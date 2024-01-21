package com.mes2.system.service;

import java.util.List;

import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface MemberService {
	
	// 로그인처리
	public MemberDTO memberLogin(MemberDTO dto);
	
	//사원정보조회 동작
	public MemberDTO memberInfo(String user_id);
	
	//사원정보수정 동작
	public void memberUpdate(MemberDTO dto);
	
	//사원정보삭제 동작
	public int delete(MemberDTO dto);

	//사원등록 동작
	public void memberJoin(MemberDTO dto);
	
	//사원리스트 출력
	public List<MemberDTO> getMemberList(MemberDTO dto);
	
	//아이디중복 확인
	public boolean checkID(String user_id);
	
	
	//파일업로드
	public void fileUpload(MemberDTO dto);
	
	//메뉴리스트출력
	public List<MemberDTO> getMenuList(MemberDTO dto);
	
	
	//메뉴상태업데이트
	public List<MemberDTO> updateMenu(MemberDTO dto);
	
	
	//사원검색결과 출력
	public List<MemberDTO> searchMemberList(String searchOption, String searchWord);
	
	
	// 페이징처리 1 
	public List<MemberDTO> MemberListPage(Criteria cri) throws Exception;
	
	// 페이징처리 2
	public int totalMemberCount() throws Exception;
	
	

	//전체사원합산출력
	public int totalMember();
	
	
	//제품합산
	public Integer totalPd();
	
	
	
	//총입고
	public Integer totalIn();
	
	
	
	//총출고
	public Integer totalOut();
	
	
	//총양품
	public Integer totalOk();
	
	
	
	//총불량품
	public Integer totalNo();
	
	
	
	
	
	//1번라인 완료율 
	public Integer firstLine();
		
		
	//2번라인 완료율 
	public Integer secondLine();
		
		
	//3번라인 완료율 
	public Integer thirdLine();
			
		
	//4번라인 완료율 
	public Integer forthLine();
			
		
	//5번라인 완료율 
	public Integer fifthLine();
			
		
	//6번라인 완료율 
	public Integer sixthLine();
			
		
	//7번라인 완료율 
	public Integer sevenLine();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
