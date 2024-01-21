package com.mes2.system.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mes2.system.domain.BoardDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;




@Repository
public class BoardDAOImpl implements BoardDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeDAOImpl.class);
	
	
	@Inject
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "com.mes2.mapper.BoardMapper";

	
	// 글 입력
	@Override
	public void insertBoard(BoardDTO dto) throws Exception {
		logger.debug(" DAO - insertBoard 시작!");
		sqlSession.insert(NAMESPACE+".insertBoard", dto);
		
	}

	
	// 글 목록 출력
	@Override
	public List<BoardDTO> getBoardListAll() throws Exception {
		logger.debug("DAO - getBoardListAll() 시작!");
		return sqlSession.selectList(NAMESPACE+".listAll");
	}

	
	// bno 로 특정글 조회
	@Override
	public BoardDTO getBoard(int bno) throws Exception {
		logger.debug("DAO - getBoard(int bno) 실행!");
		return sqlSession.selectOne(NAMESPACE+".getBoardNum",bno);
	}

	
	// 글 정보수정
	@Override
	public int updateBoard(BoardDTO dto) throws Exception {
		logger.debug("DAO - UpdateBoard(BoardDTO dto) 실행!");
		return sqlSession.update(NAMESPACE+".updateBoard", dto);
	}

	
	// 글 조회수 업데이트
	@Override
	public void updateViewCnt(int bno) throws Exception {
		logger.debug(" DAO : updateViewCnt(int bno) ");
		sqlSession.update(NAMESPACE + ".updateReadCnt", bno);
		
	}

	
	// 글 삭제
	@Override
	public void deleteBoard(int bno) throws Exception {
		logger.debug("DAO - DeleteBoard(int bno) 실행!");
		sqlSession.delete(NAMESPACE+".deleteBoard",bno);
		
	}

	
	

	@Override
	public List<BoardDTO> searchBoardlist(String searchOption, String searchWord) {
	    Map<String, String> parameters = new HashMap<>();
	    parameters.put("searchOption", searchOption);
	    parameters.put("searchWord", searchWord);
	    
	    return sqlSession.selectList(NAMESPACE+".searchBoard", parameters);
	}


	
	
	
	
	@Override
	public List<BoardDTO> getBoardListPage(int page) throws Exception {
		logger.debug("DAO : getBoardListPage(int page) 호출!");

		page = (page - 1) * 10;
		
		
		return sqlSession.selectList(NAMESPACE+".listBoard",page);
	}


	@Override
	public List<BoardDTO> getBoardListPage(Criteria cri) throws Exception {
		logger.debug("DAO - getBoardList(Criteria) 호출!");

		return sqlSession.selectList(NAMESPACE+".listBoard", cri);
	}


	@Override
	public int getBoardCount() throws Exception {
		logger.debug("DAO - getBoardCount()");
		
		return sqlSession.selectOne(NAMESPACE+".countBoard");
	}

	
	
	
	
	
	
	
	
	

	
	
}
