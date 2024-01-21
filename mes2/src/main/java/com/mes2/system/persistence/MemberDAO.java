package com.mes2.system.persistence;

import java.util.List;

import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface MemberDAO {
	
	

	
	//로그인처리
	public MemberDTO loginMember(MemberDTO dto);
	
	
	//사원정보조회
	public MemberDTO getMember(String user_id);
	
	
	
	//사원정보수정
	public void updateMember(MemberDTO dto);
	
	
	
	//사원정보삭제
	public int deleteMember(MemberDTO dto);
	
		
	
	//사원등록하기
	public void insertMember(MemberDTO dto);
	
	
	//사원리스트출력하기
	public List<MemberDTO> getMemberList(MemberDTO dto);
	
	
	//아이디중복 확인
	public boolean checkID(String user_id);
	
	
	//파일업로드
	public void fileUpload(MemberDTO dto);
	
	
	//메뉴리스트출력
	public List<MemberDTO> getMenuList(MemberDTO dto);
	
	
	//메뉴상태 업데이트
	public List<MemberDTO> updateMenu(MemberDTO dto);
	
	
	//검색사원명단 출력
	public List<MemberDTO> searchMemberlist(String searchOption, String searchWord);
	
 	
	//페이징처리 1 -> 페이징처리 수 계산
	public List<MemberDTO> getMemberListPage(int page) throws Exception;
	
	
	//페이징처리 2 -> cri 파라미터 받아서 계산
	public List<MemberDTO> getMemberListPage(Criteria cri) throws Exception;
	
	
	//페이징처리 3 -> 총 사원수 계산
	public int getMemberCount() throws Exception;
	
	
	
	//합산사원명수출력
	public int totalMember();
	
	
	//합산제품갯수출력
	public Integer totalPd();
	
	
	//합산입고수출력
	public Integer totalIn();
	
	
	//합산출고수출력
	public Integer totalOut();
	
	
	//합산 양품갯수출력
	public Integer totalOk();
	
	
	//합산 불량품갯수출력
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
