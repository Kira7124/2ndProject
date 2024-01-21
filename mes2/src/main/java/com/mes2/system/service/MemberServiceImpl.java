package com.mes2.system.service;

import java.util.List;



import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;
import com.mes2.system.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	
	@Inject
	private MemberDAO mdao;
	
	
	//로그인처리
	@Override
	public MemberDTO memberLogin(MemberDTO dto) {
		logger.debug("Service 로그인처리 ");
		
		
		return mdao.loginMember(dto);
	}

	
	
	
	//사원정보조회
	@Override
	public MemberDTO memberInfo(String user_id) {
		logger.debug("Service 조회처리");

		return mdao.getMember(user_id);
	}



	//사원정보수정
	@Override
	public void memberUpdate(MemberDTO dto) {
		logger.debug("Service 수정처리");
		mdao.updateMember(dto);
		
	}



	//사원정보삭제
	@Override
	public int delete(MemberDTO dto) {
		logger.debug("Service 삭제처리");

		return mdao.deleteMember(dto);
	}



	//사원등록
	@Override
	public void memberJoin(MemberDTO dto) {
		logger.debug("DAO 사원등록 메서드호출 - 시작");
		mdao.insertMember(dto);
		logger.debug("DAO 사원등록 메서드호출 - 끝");

		
	}



	//사원리스트
	
	@Override
	public List<MemberDTO> getMemberList(MemberDTO dto) {
		logger.debug("DAO 사원리스트 메서드호출");
		
		List<MemberDTO> resultDTO =  mdao.getMemberList(dto);
		
		
		return resultDTO;
	}



	//아이디 중복확인

	@Override
	public boolean checkID(String user_id) {
		logger.debug("S : 아이디중복확인 메서드 호출");
		return mdao.checkID(user_id);
	}



	// 파일업로드
	@Override
	public void fileUpload(MemberDTO dto) {
		logger.debug("S : 파일업로드 메서드 호출");
		mdao.fileUpload(dto);
			
	}



	//메뉴리스트출력
	@Override
	public List<MemberDTO> getMenuList(MemberDTO dto) {
		logger.debug("S : 메뉴리스트출력");
		List<MemberDTO> resultDTO = mdao.getMenuList(dto);
		return resultDTO; 
	}



	// 메뉴업데이트
	@Override
	public List<MemberDTO> updateMenu(MemberDTO dto) {
		logger.debug("S : 메뉴업데이트");
		List<MemberDTO> resultDTO  = mdao.updateMenu(dto);
		return resultDTO;
	}



	// 사원검색결과
	@Override
	public List<MemberDTO> searchMemberList(String searchOption, String searchWord) {
		 logger.debug("S : 검색결과출력");
		 List<MemberDTO> resultDTO = mdao.searchMemberlist(searchOption, searchWord);
		 return resultDTO;
	}



	
	@Override
	public List<MemberDTO> MemberListPage(Criteria cri) throws Exception {
		logger.debug(" S : MemberListPage(Criteria cri) ");
		return mdao.getMemberListPage(cri);
	}




	@Override
	public int totalMemberCount() throws Exception {
		logger.debug(" S : totalMemberCount()  ");
		return mdao.getMemberCount();
	}




	@Override
	public int totalMember() {
		logger.debug("S : 총합계산출력");
		return mdao.totalMember();
	}




	@Override
	public Integer totalPd() {
		
		return mdao.totalPd();
	}




	@Override
	public Integer totalIn() {
		
		return mdao.totalIn();
	}




	@Override
	public Integer totalOut() {
		
		return mdao.totalOut();
	}




	@Override
	public Integer totalOk() {
		return mdao.totalOk();
	}




	@Override
	public Integer totalNo() {
		return mdao.totalNo();
	}




	@Override
	public Integer firstLine() {
		return mdao.firstLine();
	}




	@Override
	public Integer secondLine() {
		// TODO Auto-generated method stub
		return mdao.secondLine();
	}




	@Override
	public Integer thirdLine() {
		// TODO Auto-generated method stub
		return mdao.thirdLine();
	}




	@Override
	public Integer forthLine() {
		// TODO Auto-generated method stub
		return mdao.forthLine();
	}




	@Override
	public Integer fifthLine() {
		// TODO Auto-generated method stub
		return mdao.fifthLine();
	}




	@Override
	public Integer sixthLine() {
		// TODO Auto-generated method stub
		return mdao.sixthLine();
	}




	@Override
	public Integer sevenLine() {
		// TODO Auto-generated method stub
		return mdao.sevenLine();
	}


	
	
	
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
