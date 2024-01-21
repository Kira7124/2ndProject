package com.mes2.system.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO {

	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	
	// DB 에 접근할 객체 주입
	@Inject
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.mes2.mapper.MemberMapper";
	
	
	// 로그인처리
	@Override
	public MemberDTO loginMember(MemberDTO dto) {
		
		logger.debug("mapper(DB) 로그인 처리 구문 실행 - 시작");
		MemberDTO resultDTO = sqlSession.selectOne(NAMESPACE+".loginMember", dto);
		
		logger.debug("결과 :" + resultDTO);
		
		
		return resultDTO;
	}
	
	
	
	
	// 사원정보조회
	@Override
	public MemberDTO getMember(String user_id) {
		logger.debug("DAO- 회원정보조회 getMember(String emp_id)");

		return sqlSession.selectOne(NAMESPACE+".getMember",user_id);
	}

	
	
	// 사원정보수정
	@Override
	public void updateMember(MemberDTO dto) {
		logger.debug("DAO- 회원정보수정 updateMember(MemberDTO dto)");
		sqlSession.update(NAMESPACE+".updateMember",dto);
	}

	
	// 사원정보삭제
	@Override
	public int deleteMember(MemberDTO dto) {
		logger.debug("DAO - 회원정보삭제 deleteMember(MemberDTO dto)");
		return sqlSession.delete(NAMESPACE+".deleteMember",dto);
	}

	

	// 사원등록
	
	@Override
	public void insertMember(MemberDTO dto) {
		logger.debug("mapper(DB) 사원등록 처리구문 실행-시작");
		sqlSession.insert(NAMESPACE+".insertMember",dto);
		
		logger.debug("mapper(DB) 사원등록 처리구문 실행-끝");

		
	}


	// 사원목록리스트

	@Override
	public List<MemberDTO> getMemberList(MemberDTO dto) {
		logger.debug("DAO - 사원정보리스트 출력");
		
		List<MemberDTO> resultDTO = sqlSession.selectList(NAMESPACE+".getMemberList",dto);
		
		
		return resultDTO;
	}



	// 아이디중복 확인

	@Override
	public boolean checkID(String user_id) {
		logger.debug("DAO - checkID 호출");

		return sqlSession.selectOne(NAMESPACE+".checkID",user_id);
	}


	// 파일업로드

	@Override
	public void fileUpload(MemberDTO dto) {
		logger.debug("DAO - fileUpload 호출");
		sqlSession.update(NAMESPACE+".fileUpload", dto);
		
	}



	//메뉴리스트출력
	@Override
	public List<MemberDTO> getMenuList(MemberDTO dto) {
		logger.debug("DAO - MENULIST 출력");
		List<MemberDTO> resultDTO = sqlSession.selectList(NAMESPACE+".getMenuList", dto);
		
		return resultDTO;
	}



	//메뉴상태업데이트
	@Override
	public List<MemberDTO> updateMenu(MemberDTO dto) {
		logger.debug("DAO - updateMenu 출력");
		List<MemberDTO> resultDTO = sqlSession.selectList(NAMESPACE+".updateMenu", dto);
		return resultDTO;
	}




	@Override
	public List<MemberDTO> searchMemberlist(String searchOption, String searchWord) {
	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("searchOption", searchOption);
	    parameters.put("searchWord", searchWord);
	    
	    return sqlSession.selectList(NAMESPACE + ".searchMember", parameters);
	}




	
	
	
	@Override
	public List<MemberDTO> getMemberListPage(int page) throws Exception {
		logger.debug("DAO : getMemberListPage(int page) 호출!");
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE+".listMember",page);
	}




	@Override
	public List<MemberDTO> getMemberListPage(Criteria cri) throws Exception {
		logger.debug("DAO - getMemberList(Criteria) 호출!");
		
		return sqlSession.selectList(NAMESPACE+".listMember", cri);
	}




	@Override
	public int getMemberCount() throws Exception {
		logger.debug("DAO - getMemberCount()");
		return sqlSession.selectOne(NAMESPACE+".countMember");
	}




	@Override
	public int totalMember() {
		logger.debug("DAO-totalMember 출력!");
		return sqlSession.selectOne(NAMESPACE+".totalMember");
	}




	@Override
	public Integer totalPd() {
		
		return sqlSession.selectOne(NAMESPACE+".totalPd");	}




	@Override
	public Integer totalIn() {
		
		return sqlSession.selectOne(NAMESPACE+".totalIn");
	}




	@Override
	public Integer totalOut() {

		return sqlSession.selectOne(NAMESPACE+".totalOut");
	}




	@Override
	public Integer totalOk() {
		return sqlSession.selectOne(NAMESPACE+".okProduct");
	}




	@Override
	public Integer totalNo() {
		return sqlSession.selectOne(NAMESPACE+".noProduct");
	}




	@Override
	public Integer firstLine() {
		return sqlSession.selectOne(NAMESPACE+".firstLine");
	}




	@Override
	public Integer secondLine() {
		return sqlSession.selectOne(NAMESPACE+".secondLine");
	}




	@Override
	public Integer thirdLine() {
		return sqlSession.selectOne(NAMESPACE+".thirdLine");
	}




	@Override
	public Integer forthLine() {
		return sqlSession.selectOne(NAMESPACE+".forthLine");
	}




	@Override
	public Integer fifthLine() {
		return sqlSession.selectOne(NAMESPACE+".fifthLine");
	}




	@Override
	public Integer sixthLine() {
		return sqlSession.selectOne(NAMESPACE+".sixthLine");
	}




	@Override
	public Integer sevenLine() {
		return sqlSession.selectOne(NAMESPACE+".sevenLine");
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
