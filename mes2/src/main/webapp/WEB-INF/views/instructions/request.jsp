<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>생산관리</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/resources/css/production/request.css">
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
		<h2>생산요청 관리</h2>
			<form class="search" action="/instructions/request">

				
				<input type="text" name="searchCode" placeholder="요청번호 "   class="form-control aria-label="Recipient's username" aria-describedby="button-addon2" style="width:200px; value="${searchCode}"/>

				<input type="submit" value="검색" class="btn btn-secondary"/>
			</form>

			<!-- 표 -->
			<div class="list">
			
			
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="deletebtn" style="visibility:hidden;">긴급탈출버튼</button>
				</div>
				

				<div class="list-box">
						<table class="table table-hover"  style="text-align: center;">

							<thead>
								<tr class="table-success">
									<th scope="col">요청번호</th>
									<th scope="col">제품번호</th>
									<th scope="col">요청상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${instructions}">
									<tr>
										<td onclick="getMaterials('${item.sopCode}','${item.salesQuantity }')">${item.sopCode}</td>
										<td onclick="getMaterials('${item.sopCode}','${item.salesQuantity }')">${item.mdpCode}</td>
										<td>
											<c:if test="${item.materialStatus.equals('Y') }">
												<input type="hidden" name="sopCode" value="${item.sopCode}">
												<button type="submit" class="btn btn-secondary" id="accept" onclick="window.open('/instructions/accept/${item.sopCode}','result','width=800px, height=640px')">수락</button>
											</c:if>
											<c:if test="${item.materialStatus.equals('N')}">
												<i class="fa-solid fa-circle fa-2xs" style="color: #a44141;"></i>자재필요
											</c:if>
											<c:if test="${item.materialStatus.equals('R')}">
												<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i>자재준비중
											</c:if>
										</td>
					
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
				</div>
			</div>
	
			
					 <!-- 페이징  -->
        <div class="page-nav">
        <nav aria-label="Page navigation example">
          <ul class="pagination">
          
          <!-- 이전페이지 -->
          <c:if test="${pageVO.prev }">
              <li class="page-item page-action">
                  <a class="page-link" href="/instructions/request?page=${pageVO.startPage - 1 }&searchType=${searchType }" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
          </c:if>
          
          
      <!-- 페이지 번호 -->
      
        <c:forEach var="pageNum" begin="${pageVO.startPage}" end="${pageVO.endPage}">
            <c:if test="${pageVO.cri.page != pageNum}">
                <li class="page-item page-action"><a class="page-link" href="/instructions/request?page=${pageNum}&searchType=${searchType}">${pageNum}</a></li>
            </c:if>
            <c:if test="${pageVO.cri.page == pageNum}">
                <li class="active page-item page-action"><a class="page-link" href="/instructions/request?page=${pageNum }&searchType=${searchType}">${pageNum}</a></li>
            </c:if>
        </c:forEach>

      <!-- 다음페이지 -->
      <c:if test="${pageVO.next }">
              <li class="page-item">
                  <a class="page-link" href="/instructions/request?page=${pageVO.endPage + 1 }&searchType=${searchType}" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                  </a>
              </li>
              </c:if>
          </ul>
      </nav>
      </div>
         <!-- 페이징 끝 -->
			
		</section>
	         
		<section class="section2">
			<div id="bottomContent">
		
		
			</div>
		</section>

	</div>
	<script src="/resources/js/instructions/request.js"></script>
	
	<script>
	
    function openInput(){
        window.open("/instructions/save","save","width=800px, height=640px")
    }
    function openAccept(){
        window.open("/instructions/accept","accept","width=800px, height=640px")
    }
    
   
	</script>
	
</body>
</html>