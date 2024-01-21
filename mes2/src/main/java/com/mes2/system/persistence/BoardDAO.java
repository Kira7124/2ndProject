package com.mes2.system.persistence;

import java.util.List;

import com.mes2.system.domain.BoardDTO;
import com.mes2.system.domain.Criteria;
import com.mes2.system.domain.MemberDTO;

public interface BoardDAO {
	
	
	
	// 글 작성
	public void insertBoard(BoardDTO dto) throws Exception;
	
	
	//글 목록 조회
	public List<BoardDTO> getBoardListAll() throws Exception;
	
	
	// 특정 글 번호 조회
	public BoardDTO getBoard(int bno) throws Exception;
	
	
	//글 업데이트
	public int updateBoard(BoardDTO dto) throws Exception;
	
	
	// 글 삭제
	public void deleteBoard(int bno) throws Exception;
		
	
	//글 조회수 1증가
	public void updateViewCnt(int bno) throws Exception;
	
	
	// 검색 리스트 출력
	public List<BoardDTO> searchBoardlist(String searchOption, String searchWord);

	
	
	//페이징처리 1 -> 페이징처리 수 계산
    public List<BoardDTO> getBoardListPage(int page) throws Exception;
		
		
	//페이징처리 2 -> cri 파라미터 받아서 계산
	public List<BoardDTO> getBoardListPage(Criteria cri) throws Exception;
		
		
	//페이징처리 3 -> 총 사원수 계산
	public int getBoardCount() throws Exception;
	
	
	
	
	
	
	
}
