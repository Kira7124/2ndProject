<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>생산라인관리</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/resources/css/platform/orderList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>

<body>
	<%@ include file="../system/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
		<h2>생산라인 관리</h2>
			<form class="search" action="/productionLine/search">
				<select id="boundary" name="searchStatus" class="form-select"  style="width:300px;">
					<option value="">-- 검색선택 --</option>
					<option value="WAITING">작업대기</option>
					<option value="PROGRESSING">작업중</option>
					<option value="COMPLETE">작업완료</option>
				</select>
				
				<input type="text" name="searchCode" value="${searchCode }" placeholder="작업지시코드"  class="form-control "   style="width:300px;"/>
				<div>
					<input id="dtIp" type="date" name="searchStartDate" min="2023-12-01" max="2024-12-31" value="${searchStartDate}"  class="form-control" />
				</div>
				<div>
					<input id="dtIp" type="date" name="searchEndDate" min="2020-01-01" max="2030-12-31" value="${searchEndDate}" width="100px"   class="form-control" />
				</div>


	        	<button type="submit" value="검색" class="btn btn-secondary" id="delete-btn"  style="width:100px;">검색</button>				
			</form>

			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="addbtn" style="visibility:hidden;">추가</button>
					<button type="button" class="btn btn-secondary" id="deletebtn" style="visibility:hidden;">삭제</button>
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
						
							
							<colgroup>
                    			<col style="width: 15%" /> 
                    			<col style="width: 10%" />
                    			<col style="width: 10%" />
                    			<col style="width: 10%" />
                    			<col style="width: 10%" />
                    			<col style="width: 10%" />
                			</colgroup>
							<thead>
								<tr class="table-success">
									<th scope="col">No</th>
									<th scope="col">생산라인</th>
									<th scope="col">작업번호</th>
									<th scope="col">시작날짜</th>
									<th scope="col">종료날짜</th>
									<th scope="col">상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${productionLineList}">
									<tr>
										<td>${item.index}</td>
										<td>${item.line}</td>
										<td>${item.isCode}</td>
										<td>${item.startDate}</td>
										<td>${item.endDate}</td>
										<td>
										<c:if test="${item.status.equals('WAITING')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #416ca4;"></i>생산시작
										</c:if>
										<c:if test="${item.status.equals('COMPLETE')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i>생산완료
										</c:if>
										<c:if test="${item.status.equals('PROGRESSING')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i>생산중
										</c:if>
										
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
						
		</section>

				<!-- 페이징  -->
		  <div class="page-nav">
		  <nav aria-label="Page navigation example">
		    <ul class="pagination">
		    
		    <!-- 이전페이지 -->
		    <c:if test="${pageVO.prev }">
		        <li class="page-item page-action">
		            <a class="page-link" href="/productionLine/search?page=${pageVO.startPage - 1 }&searchStatus=${searchStatus }&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}" aria-label="Previous">
		                <span aria-hidden="true">&laquo;</span>
		            </a>
		        </li>
		    </c:if>
		    
		    
		<!-- 페이지 번호 -->
		
        <c:forEach var="pageNum" begin="${pageVO.startPage}" end="${pageVO.endPage}">
            <c:if test="${pageVO.cri.page != pageNum}">
                <li class="page-item page-action"><a class="page-link" href="/productionLine/search?page=${pageNum}&searchStatus=${searchStatus }&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}">${pageNum}</a></li>
            </c:if>
            <c:if test="${pageVO.cri.page == pageNum}">
                <li class="active page-item page-action"><a class="page-link" href="/productionLine/search?page=${pageNum}&searchStatus=${searchStatus }&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}">${pageNum}</a></li>
            </c:if>
        </c:forEach>

		<!-- 다음페이지 -->
			<c:if test="${pageVO.next }">
		        <li class="page-item">
		            <a class="page-link" href="/productionLine/search?page=${pageVO.endPage + 1 }&searchStatus=${searchStatus }&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}" aria-label="Next">
		                <span aria-hidden="true">&raquo;</span>
		            </a>
		        </li>
		        </c:if>
		    </ul>
		</nav>
		</div>

		<div id="bottomContent"></div>
	</div>
	<script src="/resources/js/platform/orderList.js"></script>
	
	<script>
	
    var searchStatus = '${searchStatus}'; 
    // JavaScript를 통해 동적으로 선택된 값을 설정
    document.getElementById('boundary').value = searchStatus;
	
    function openIsSave(){
        window.open("/instructions/save","save","width=800px, height=640px")
    }
	</script>
	
</body>
</html>