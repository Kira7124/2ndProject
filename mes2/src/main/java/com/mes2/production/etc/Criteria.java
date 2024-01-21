package com.mes2.production.etc;

/**
 *  페이징 처리를 위해서 생성한 객체
 *   => 페이지번호, 페이지사이즈를 저장하는 객체  
 */
public class Criteria {

	private int page;
	private int pageSize; // 한 페이지에 글 몇 개 노출할 건지
	
	public Criteria() {
		this.page = 1;
		this.pageSize = 7;
	}
		
	// 페이지 설정
	public void setPage(int page) {
		if(page <=0) {
			this.page = 1;
			return;
		}
		this.page = page;		
	}
	
	public void setPageSize(int pageSize) {
		if(pageSize <= 0 || pageSize > 100) {
			this.pageSize = 7;
			return;
		}
		
		this.pageSize = pageSize;
	}
	
	public int getPage() {
		return page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	// private int startPage; // 변수선언없이 get메서드만 구현
	// 변수는 없지만, mapper에서 사용 #{startPage} 요소 호출
	public int getStartPage() {
		// 페이지 정보를 쿼리사용되는 값(시작인덱스)으로 변경
		return (this.page - 1) * pageSize;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}
