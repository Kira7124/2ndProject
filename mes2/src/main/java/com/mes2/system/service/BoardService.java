package com.mes2.system.service;

import java.util.List;

import com.mes2.system.domain.BoardDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface BoardService {

	
	public void boardWrite(BoardDTO dto) throws Exception;
	
	
	
	public List<BoardDTO> boardListAll() throws Exception;
	
	
	
	public BoardDTO getBoard(int bno) throws Exception;
	
	
	
	public int boardModify(BoardDTO dto) throws Exception;
	
	
	
	public void incrementViewCnt(int bno) throws Exception;
	
	
	
	public void boardRemove(int bno) throws Exception;	
	

	
	public List<BoardDTO> searchBoardList(String searchOption, String searchWord);

	
	
	// 페이징처리 1 
	public List<BoardDTO> BoardListPage(Criteria cri) throws Exception;
		
	
	// 페이징처리 2
	public int totalBoardCount() throws Exception;
	
	
	
	
	
}
