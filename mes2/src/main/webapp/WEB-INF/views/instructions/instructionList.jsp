<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>작업지시관리</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/resources/css/production/instructionList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
</head>

<body>
	<%@ include file="../system/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<h2>작업지시 관리</h2>
			<form class="search" action="/instructions/search" onsubmit="return validateSearchForm()">
				
				<select id="state" name="searchState" class="form-select" aria-label="Default select example" style="width:150px;">
					<option value="">-- 검색선택 --</option>
					<option value="WAITING">작업대기</option>
					<option value="PROGRESSING">진행중</option>
					<option value="COMPLETE">완료</option>
				</select>
				<div style="width:200px;">
					<select id="boundary" name="searchType" class="form-select" aria-label="Default select example">
						<option value="">-- 검색선택 --</option>
						<option value="isCode">작업지시코드</option>
						<option value="sopCode">수주번호</option>
						<option value="mdpCode">제품번호</option>
					</select>
				</div>
				
				
				<input type="text" name="searchCode" placeholder="검색어를 입력하세요"  class="form-control aria-label="Recipient's username" aria-describedby="button-addon2" style="width:200px;"/>
				<div>
					<input id="dtIp" type="date" aria-label="First name" class="form-control" name="searchStartDate" min="2023-12-01" max="2024-12-31" value="${searchStartDate}"/>
				</div>
				<div>				
					<input id="dtIp" type="date" aria-label="Last name" class="form-control" name="searchEndDate" min="2020-01-01" max="2030-12-31" width="100px" value="${searchEndDate}"/>
				</div>
				
				<input type="submit" value="검색" class="btn btn-secondary"/>
			</form>

			<!-- 표 -->
			<div class="list">
			
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="deletebtn" style="visibility:hidden;">긴급탈출버튼</button>
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
						
							
							<colgroup>
                    			<col style="width: 10%" /> 
                    			<col style="width: 3%" />
                    			<col style="width: 8%" />
                    			<col style="width: 15%" />
                    			<col style="width: 13%" />
                    			<col style="width: 13%" />
                    			<col style="width: 5%" />
								<col style="width: 5%" />
								<col style="width: 7%" />
                			</colgroup>
							<thead>
								<tr class="table-success">
									<th scope="col">작업지시번호</th>
									<th scope="col">라인</th>
									<th scope="col">제품번호</th>
									<th scope="col">수주번호</th>
									<th scope="col">시작시간</th>
									<th scope="col">종료시간</th>
									<th scope="col">양품</th>
									<th scope="col">불량품</th>
									<th scope="col">생산상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${instructions}">
									<tr>
										<td>${item.code}</td>
										<td>${item.line}</td>
										<td>${item.mdpCode}</td>
										<td>${item.sopCode}</td>
										<td>${item.startTime}</td>
										<td>${item.endTime}</td>
										<td>${item.quantity}</td>
										<td>${item.fault}</td>
										<td>
										<c:if test="${item.state.equals('WAITING')}">
											<button type="button" class="btn btn-secondary" onclick="updateProgressing('${item.code}');" >생산시작</button>
										</c:if>
										<c:if test="${item.state.equals('COMPLETE')}">
											<i class="fa-solid fa-circle fa-2xs" style="color: #416ca4;"></i>생산완료
										</c:if>
										<c:if test="${item.state.equals('PROGRESSING')}">
											<button type="button" class="btn btn-secondary" onclick="window.open('/instructions/resultInfo/${item.code}','result','width=800px, height=640px')">생산중</button>
										</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
     <!-- 페이징  -->
		<div class="page-nav">
		  <nav aria-label="Page navigation example">
		    <ul class="pagination">
		    
		    <!-- 이전페이지 -->
		    <c:if test="${pageVO.prev }">
		        <li class="page-item page-action">
		            <a class="page-link" href="/instructions/search?page=${pageVO.startPage - 1 }&searchType=${searchType }&searchCode=${searchCode}&searchState=${searchState}&searchStartDate=${searchStartDate }&searchEndDate=${searchEndDate}" aria-label="Previous">
		                <span aria-hidden="true">&laquo;</span>
		            </a>
		        </li>
		    </c:if>
		    
		    
		<!-- 페이지 번호 -->
		
        <c:forEach var="pageNum" begin="${pageVO.startPage}" end="${pageVO.endPage}">
            <c:if test="${pageVO.cri.page != pageNum}">
                <li class="page-item page-action"><a class="page-link" href="/instructions/search?page=${pageNum }&searchType=${searchType}&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchState=${searchState}&searchEndDate=${searchEndDate}">${pageNum}</a></li>
            </c:if>
            <c:if test="${pageVO.cri.page == pageNum}">
                <li class="active page-item page-action"><a class="page-link" href="/instructions/search?page=${pageNum }&searchType=${searchType}&searchCode=${searchCode}&searchStartDate=${searchStartDate }&searchState=${searchState}&searchEndDate=${searchEndDate}">${pageNum}</a></li>
            </c:if>
        </c:forEach>

		<!-- 다음페이지 -->
		<c:if test="${pageVO.next }">
		        <li class="page-item">
		            <a class="page-link" href="/instructions/search?page=${pageVO.endPage + 1 }&searchType=${searchType}&searchState=${searchState}&searchCode=${searchCode}&searchStartDate=${searchStartDate}&searchEndDate=${searchEndDate}" aria-label="Next">
		                <span aria-hidden="true">&raquo;</span>
		            </a>
		        </li>
		        </c:if>
		    </ul>
		</nav>
		</div>
		</section>

		<div id="bottomContent"></div>
	</div>
	<script src="/resources/js/platform/orderList.js"></script>
	
	<script type="text/javascript">
	var isCode = '${item.code}'
	
    function openIsSave(){
        window.open("/instructions/save","save","width=800px, height=640px")
    }
/* 	
    function openIsResult(){
        window.open("/instructions/result?isCode"+isCode,"result","width=800px, height=640px")
    }
    */ 

	
	function validateSearchForm() {
        var searchType = document.getElementById("boundary").value;
        
        if (searchType === "") {
            alert("검색 타입을 선택하세요.");
            return false; 
        }
        
        return true; 
    }
    
    var searchType = '${searchType}'; 
    var searchState = '${searchState}'; 
    // JavaScript를 통해 동적으로 선택된 값을 설정
    document.getElementById('boundary').value = searchType;
    document.getElementById('state').value = searchState;
		
	
	</script>
	<script src="/resources/js/instructions/instructionList.js"></script>
		
</body>
</html>