<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Gn5384xqQ1b8f1PIt4IxlGZhRa7Cf8d/Jw2g9rMzRc5lFf5L2X3+r5g1eU/3eDpP4fAq2Mv8Gf9lCJhCnRZGAw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>공지사항</title>

<style text="text/css">


  .list {
            margin-left: 100px;
  			margin-top: 10px;
            box-sizing: border-box;
   			width: 93%;
        }

 .table th, .table td {
          text-align: center;
          vertical-align: middle; 
          border : none;
       }


.table tr td {
    border-bottom: 1px solid #dee2e6;  /* td 아랫쪽에만 border 추가 */
}

.profile-img{
 	width: 100px; /* 원하는 크기로 조절합니다. */
    height: 100px; 
}


.btn.btn-success.first{
	margin-bottom: 10px;
}


.searchInput{
	flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
    margin-right: 5px; /* 검색어 입력란 오른쪽과의 간격 설정 */
}


.searchInputdiv {
    display: flex;
    justify-content: flex-start;
    align-items: center; 
 	margin-right: 1300px;
 }

 .page-item {
       margin-right: 5px; /* 원하는 간격 크기로 조정 */
 }





</style>





</head>



	<body>
	
	 	<%@ include file="sidehead.jsp" %>	
	 	
	 	
	 	<!-- 전체를 감싸는 div -->
	 	<h2 style ="margin-left: 100px;">공지사항</h2> 	
	 		<div class="list" style="margin-top: 10px;">
	 		
	 		<!-- 등록하기 버튼(상단의) -->
			  <div class="d-flex justify-content-end">
			  	<div class="searchInputdiv">
<!-- 			 	<form action="/system/searchboardlist" method="GET" style="display: flex;" class="d-flex"> -->
<!-- 			 	 	<select name="searchOption"> -->
<!-- 				        <option value="option1">글쓴이</option> -->
<!-- 				        <option value="option2">글제목</option> -->
<!--    					 </select> -->
<!-- 				    <input type="text" class="form-control searchInput" name="searchWord"> -->
<!-- 				    <button class="btn btn-outline-success" type="submit">검색</button> -->
<!-- 				</form> -->
			 	</div>
			 <c:if test="${!empty id && id.equals('admin')}">	
			   <a href="/system/insertBoard" data-bs-toggle="modal" data-bs-target="#insertBoardForm" href="javascript:void(0);">
			 	<button type="button" class="btn btn-success first" style="margin-right: 10px;">글등록</button>
			   </a>
			 </c:if> 	
			 </div>
	 		<!-- 등록하기 버튼(상단의) -->
	 	
	 	
	 	
	 	<div class="son_list-box">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
									<th scope="col">번호</th>
									<th scope="col">작성자</th> <!-- 체크박스 -->
									<th scope="col">제목</th>
									<th scope="col">등록일</th>
									<th scope="col">조회수</th>									
								</tr>
						</thead>
				<c:forEach var="boardList" items="${boardList}">			
                    <tbody>
                        <td>${boardList.bno}</td>   
		                <td>${boardList.writer}</td>
				<c:choose>
				    <c:when test="${fn:contains(boardList.title, '[공지]')}">
				        <td>
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: blue;">${boardList.title}</a>
				        </td>
				    </c:when>
				    <c:when test="${fn:contains(boardList.title, '[알림]')}">
				        <td>
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: red;">${boardList.title}</a>
				        </td>
				    </c:when>
				    <c:when test="${fn:contains(boardList.title, '[일반]')}">
				        <td>
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: black;">${boardList.title}</a>
				        </td>
				    </c:when>
				    <c:otherwise>
				        <td>
				            <!-- 기본값 설정 -->
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: blue;">${boardList.title}</a>
				        </td>
				    </c:otherwise>
				</c:choose>
						<td>
							 <fmt:formatDate value="${boardList.regdate}" dateStyle="short" pattern="yy-MM-dd"/> 
						</td>
						<td>
							${boardList.read_count}
						</td>
	 	            </tbody>
	 	         </c:forEach>   
			</table>
		</div>
		
		
		<nav aria-label="Page navigation example" style="display: flex; justify-content: center;">
		  <ul class="pagination">
		   <c:if test="${pageVO.prev }">
		    <li class="page-item">
		      <a class="page-link" href="/system/listAll?page=${pageVO.startPage - 1 }" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		   </c:if>
		   
		 <c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
		    <c:set var="isActive" value="${pageVO.cri.page == i}" />
		    <li class="page-item ${isActive ? 'active' : ''}">
		        <a class="page-link" href="/system/listAll?page=${i}" style="${isActive ? 'background-color: #95c4a2; color: #ffffff; border-color: #81b189;' : 'background-color: #ffffff; color: #000000; border-color: #dddddd;'}">
		            ${i}
		        </a>
		    </li>
		</c:forEach>
		 
		  
		    
		  <c:if test="${pageVO.next }">  
		    <li class="page-item">
		      <a class="page-link" href="/system/memberlist?page=${pageVO.endPage + 1 }" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		   </c:if>
		    
		  </ul>
		</nav>	
			
	</div>			
	<!-- 전체를 감싸는 div --> 	
	
	
	
<!-- 글등록 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" id="insertBoardForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">공지사항작성</h4>
		   	    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
			<jsp:include page="insertboard.jsp" />
		  </div>
		 </div>	
		</div>
	
	
<!-- 글등록 모달 -->	
	


<!--글등록 모달자바스크립트 -->
	
<script>






    $(document).ready(function(){
    	
    	
    	
        // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#insertBoardButton").click(function(){
            $("#insertBoardForm").modal("show");
        });
        
        $("a[data-target='#insertBoardForm']").click(function(){
            $("#insertBoardForm").modal("show");
        });
        
        $("#insertBoardForm .btn-info").click(function(){
            $("#insertBoardForm form").submit();
        });
        
        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#insertBoardForm").on("hidden.bs.modal", function () {
            $("#insertBoardForm form")[0].reset();
        });
        

        $("#insertBoardForm .btn btn-secondary").click(function(){
            $("#insertBoardForm").modal("hide");
        });
        
        $("#insertBoardForm .close").click(function () {
            $("#insertBoardForm").modal("hide");
        });
        
           
    });
</script>	
<!--글등록 모달자바스크립트 -->



	
	
	
	</body>
</html>