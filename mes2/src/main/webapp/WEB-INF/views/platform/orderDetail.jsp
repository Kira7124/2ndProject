<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>발주 상세 조회</title>
<sec:csrfMetaTags/>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/orderDetail.css">
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->
</head>
<body>
	<div class="container">
		<section class="section2">
			<hr>
			<h2>상세 조회</h2>
				<span class="list-btn2">
					<c:if test="${soiDTO.sales_status eq 'requested'}">
						<button type="button" class="btn btn-secondary" id="addBtn" onclick="modifyOrder('${soiDTO.order_code}', '${soiDTO.order_date }', '${soiDTO.sales_status }');">발주 수정</button>
						<button type="button" class="btn btn-secondary" id="addBtn" onclick="deleteOrder('${soiDTO.order_code}', '${soiDTO.sales_status }');">발주 취소</button>
					</c:if>
					<c:if test="${soiDTO.sales_status eq 'deliver'}">
						<button type="button" class="btn btn-secondary" id="addBtn" onclick="completeOrder('${soiDTO.order_code}');">수령 완료</button>
					</c:if>
					<c:if test="${soiDTO.sales_status eq 'complete'}">
						<button type="button" class="btn btn-secondary" id="addBtn" onclick="getSignature('${soiDTO.order_code}');">서명 조회</button>
					</c:if>
				</span> <br>
				납품 요청일: <input type="date" id="dtIp" name="order_date" value="${order_date }" readonly/><br>
				<br>
				<div class="list">
					<div class="list-box">
						<table class="table table-hover" >
							<thead>
								<tr class="table-success">
									<th scope="col">품목코드</th>
									<th scope="col">품목명</th>
									<th scope="col">단가</th>
									<th scope="col">수량</th>
									<th scope="col">합계</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="sopList" items="${soiDTO.sopList }">
									<tr>
										<td>${sopList.product_code }</td>
										<td>${sopList.mdpDTO.name }</td>
										<td><fmt:formatNumber value="${sopList.mdpDTO.price }"/> 원</td>
										<td>${sopList.sales_quantity }EA</td>										
										<td><fmt:formatNumber value="${sopList.mdpDTO.price * sopList.sales_quantity }"/>원</td>										
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
		</section>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/platform/orderDetail.js"></script>
</body>
</html>