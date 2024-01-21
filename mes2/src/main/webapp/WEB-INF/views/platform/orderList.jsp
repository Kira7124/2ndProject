<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>발주 목록</title>
<sec:csrfMetaTags/>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/orderList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
</head>

<body>
	<%@ include file="sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<h2>발주 목록</h2>
			<form class="search">
					<select name="sales_status" id="sales_status" class="form-select" aria-label="Default select example">
						<option value="">-- 진행상태 --</option>
						<option value="requested">신청중</option>
						<option value="accept">생산중</option>
						<option value="deliver">배송중</option>
						<option value="complete">완료</option>
						<option value="reject">발주 불가</option>
					</select>
					
					<div class="input-group">
						<span class="input-group-text">발주일자</span>
						<input type="date" aria-label="First name" class="form-control" name="startDate" value="${searchDTO.startDate }"/>
						<input type="date" aria-label="Last name" class="form-control" name="endDate" value="${searchDTO.endDate }"/>
					</div>
					<button class="btn btn-secondary" type="submit" id="button-addon2">검색</button>
					<a href="/platform/orderList"><i class="fa-solid fa-rotate-right" id="reset"></i></a>
			</form>

			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
					<button type="button" class="btn btn-secondary" id="addbtn" onclick="insertOrder()">발주 신청</button>
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th scope="col">주문번호</th>
									<th scope="col">발주일자</th>
									<th scope="col">납품요청일</th>
									<th scope="col">물품수령일</th>
									<th scope="col">진행상태</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="soiDTO" items="${soiDTO }">
									<tr onclick="getOrderDetail('${soiDTO.order_code }','${soiDTO.order_date }')" class="selectOrder">
										<td>${soiDTO.order_code }</td>
										<td>${soiDTO.request_date }</td>
										<td>${soiDTO.order_date }</td>
										<td>${soiDTO.complete_date }</td>
										<td>
											<c:choose>
												<c:when test="${soiDTO.sales_status eq 'requested' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i> 신청중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'accept' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #439f1d;"></i> 생산중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'deliver' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #4486ff;"></i> 배송중
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'complete' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i> 완료
												</c:when>
												<c:when test="${soiDTO.sales_status eq 'reject' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #a44141;"></i> 발주 불가
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
			
		</section>
			<!-- 페이징 -->
			<div class="page-nav">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
				
					<c:if test="${pageVO.prev }">
						<li class="page-item page-action">
							<a href="/platform/orderList?page=${pageVO.startPage - 1 }&sales_status=${searchDTO.sales_status }&startDate=${searchDTO.startDate }&endDate=${searchDTO.endDate}" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					
					<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
						<c:if test="${searchDTO.cri.page ne i }">
							<li class="page-item page-action"><a class="page-link" href="/platform/orderList?page=${i }&sales_status=${searchDTO.sales_status }&startDate=${searchDTO.startDate }&endDate=${searchDTO.endDate}"  style="color: black;">${i }</a></li>
						</c:if>
						<c:if test="${searchDTO.cri.page eq i }">
							<li class="active page-item page-action"><a class="page-link" href="/platform/orderList?page=${i }&sales_status=${searchDTO.sales_status }&startDate=${searchDTO.startDate }&endDate=${searchDTO.endDate}">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${pageVO.next }">
						<li class="page-item">
							<a  class="page-link" href="/platform/orderList?page=${pageVO.endPage + 1 }&sales_status=${searchDTO.sales_status }&startDate=${searchDTO.startDate }&endDate=${searchDTO.endDate}" aria-label="Next">
							    <span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</c:if>
					</ul>
				</nav>
			</div>
			<!-- 페이징 끝 -->

		<div id="bottomContent"></div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#sales_status").val("${searchDTO.sales_status}").attr("selected","selected");
		});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/orderList.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/insertOrder.js"></script>
<%-- 	<script src="${pageContext.request.contextPath}/resources/js/platform/sideheadscript.js"></script> --%>
</body>
</html>