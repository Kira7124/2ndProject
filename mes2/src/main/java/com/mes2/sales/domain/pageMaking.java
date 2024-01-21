package com.mes2.sales.domain;

import lombok.Data;

@Data
public class pageMaking {

	private Criteria cri;
	private int startPage; //시작 페이지
	private int endPage; //끝페이지
	private int totalCount; //총 게시글 수
	private boolean prev; // 이전버튼
	private boolean next; // 다음버튼
	private int pageBlock=3; // 페이지블록 12345
	
	//디비에 저장된 글 수 구하기 
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		makePage();
	}
	
	private void makePage() {
		// 마지막페이지 정하기 
		endPage = (int)(Math.ceil(cri.getPage()/(double)pageBlock)*pageBlock);
		// 마지막페이지 정하기 
		startPage = (endPage-pageBlock)+1;
		if(startPage<=0) startPage =1;
		// 페이지 블록 끝 번호
		int tempEndPage=(int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		if(tempEndPage<endPage) {
			endPage = tempEndPage;
		}
		//이전버튼
		prev=(startPage==1)? false : true;
		//다음버튼
		next = (endPage<tempEndPage)? true:false;
	}
}