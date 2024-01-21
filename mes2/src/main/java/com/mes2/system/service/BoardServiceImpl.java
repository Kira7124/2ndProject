package com.mes2.system.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.system.domain.BoardDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;
import com.mes2.system.persistence.BoardDAO;






@Service
public class BoardServiceImpl implements BoardService {

	
	private static final Logger logger = LoggerFactory.getLogger(CommonCodeServiceImpl.class);

	
	@Inject
	private BoardDAO bdao;


	
	
	@Override
	public void boardWrite(BoardDTO dto) throws Exception {
		logger.debug(" S : boardWrite(BoardDTO dto) ");
		bdao.insertBoard(dto);
		
	}


	@Override
	public List<BoardDTO> boardListAll() throws Exception {
		logger.debug("S : boardListAll() 시작");
		return bdao.getBoardListAll();
	}


	@Override
	public BoardDTO getBoard(int bno) throws Exception {
		logger.debug("S : getBoard(int index) 시작!");
		return bdao.getBoard(bno);
	}


	@Override
	public int boardModify(BoardDTO dto) throws Exception {
		logger.debug("S : boardModify() 시작");
		return bdao.updateBoard(dto);
	}


	@Override
	public void incrementViewCnt(int bno) throws Exception {
		logger.debug(" S : incrementViewCnt(int bno) ");
		bdao.updateViewCnt(bno);
	}


	@Override
	public void boardRemove(int bno) throws Exception {
		logger.debug("S : boardRemove() 시작!");
		bdao.deleteBoard(bno);
	}
	
	
	
	// 글검색결과
	@Override
	public List<BoardDTO> searchBoardList(String searchOption, String searchWord) {
		 logger.debug("S : 검색결과출력");
		 List<BoardDTO> resultDTO = bdao.searchBoardlist(searchOption, searchWord);
		 return resultDTO;
	}


	
	
	@Override
	public List<BoardDTO> BoardListPage(Criteria cri) throws Exception {
		logger.debug(" S : MemberListPage(Criteria cri) ");
		return bdao.getBoardListPage(cri);
	}


	
	@Override
	public int totalBoardCount() throws Exception {
		logger.debug(" S : totalMemberCount()  ");
		return bdao.getBoardCount();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
