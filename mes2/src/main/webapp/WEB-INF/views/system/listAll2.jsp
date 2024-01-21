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
       background-color : #28a745;
 }



</style>





</head>
	<body style ="background-color: #F5FBF0;">
	 	
	<!-- 전체를 감싸는 div -->
	<div class="list">
	 	<div class="son_list-box">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
<!-- 									<th scope="col">번호</th> -->
<!-- 									<th scope="col">작성자</th> 체크박스 -->
									<th scope="col">제목</th>
									<th scope="col">등록일</th>
<!-- 									<th scope="col">조회수</th>									 -->
								</tr>
						</thead>
				<c:forEach var="boardList" items="${boardList}">			
                    <tbody>
<%--                         <td>${boardList.bno}</td>    --%>
<%-- 		                <td>${boardList.writer}</td> --%>
				<c:choose>
				    <c:when test="${fn:contains(boardList.title, '[공지]')}">
				        <td>
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: black;">${boardList.title}</a>
				        </td>
				    </c:when>
				    <c:when test="${fn:contains(boardList.title, '[알림]')}">
				        <td>
				            <a id="boardTitleLink" href="/system/readboard?bno=${boardList.bno}" style="text-decoration-line: none; font-weight: bold; color: #eb3d3d;">${boardList.title}</a>
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
<!-- 						<td> -->
<%-- 							${boardList.read_count} --%>
<!-- 						</td> -->
	 	            </tbody>
	 	         </c:forEach>   
			</table>
		</div>
		
			
	</div>			
	<!-- 전체를 감싸는 div --> 	
	
	








	
	
	
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
	</body>
</html>