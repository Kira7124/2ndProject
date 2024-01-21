package com.mes2.system.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.system.domain.CommonCodeDTO;
import com.mes2.system.domain.Criteria;


@Repository
public class CommonCodeDAOImpl implements CommonCodeDAO {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeDAOImpl.class);
	
	
	//DB 에 접근할 객체주입
	@Inject
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.mes2.mapper.CommoncodeMapper";
	
	
	
	//공통코드리스트 출력
	@Override
	public List<CommonCodeDTO> getCommoncodeList(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드리스트출력!");
		
		List<CommonCodeDTO> resultDTO = sqlSession.selectList(NAMESPACE+".getCommoncodeList",dto);
		
		return resultDTO;
	}

	
	
	
	
	
	//공통코드조회

	@Override
	public CommonCodeDTO getCommoncode(int code_index) {
		logger.debug("DAO - 공통코드 조회 하기!");
	
		return sqlSession.selectOne(NAMESPACE+".getCommoncode",code_index);
	}





	//공통코드 수정
	@Override
	public void updateCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드수정실행!");
		sqlSession.update(NAMESPACE+".updateCommonCode",dto);
		
	}





	// 공통코드 등록
	@Override
	public void insertCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드등록실행!");
		sqlSession.insert(NAMESPACE+".insertCommoncode",dto);
		
	}




	
	// 공통코드 삭제
	@Override
	public int deleteCommonCode(CommonCodeDTO dto) {
		logger.debug("DAO - 공통코드삭제실행!");
		return sqlSession.delete(NAMESPACE+".deleteCommonCode",dto);
	}





	// 공통코드 검색
	@Override
	public List<CommonCodeDTO> searchCommonCodelist(String searchOption, String searchWord) {
		logger.debug("DAO - 공통코드검색실행!");
		 Map<String, String> parameters = new HashMap<>();
		 parameters.put("searchOption", searchOption);
		 parameters.put("searchWord", searchWord);

		return sqlSession.selectList(NAMESPACE + ".searchCode", parameters);
	}





	
	

	@Override
	public List<CommonCodeDTO> getCommonCodeListPage(int page) throws Exception {
		
		page = (page - 1) * 10;
		
		return sqlSession.selectList(NAMESPACE+".listCommonCode",page);
	}






	@Override
	public List<CommonCodeDTO> getCommonCodeListPage(Criteria cri) throws Exception {
		
		return sqlSession.selectList(NAMESPACE+".listCommonCode", cri);
	}






	@Override
	public int getCommonCodeCount() throws Exception {
		
		
		return sqlSession.selectOne(NAMESPACE+".countCommonCode");
	}



	



	
	
	
	
	
	
	
	
	
	
}
