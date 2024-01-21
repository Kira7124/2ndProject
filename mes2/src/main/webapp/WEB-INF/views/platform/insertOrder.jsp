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
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>발주 신청</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/insertOrder.css">
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
			<h2>발주 신청</h2>
			<form action="/platform/insertOrder" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<span class="list-btn2">
					<button type="button" class="btn btn-secondary" id="addBtn" onclick="openProductList()">품목 추가</button>
					<button type="button" class="btn btn-secondary" onclick="insertOrder2();">신청</button>
					<button type="button" class="btn btn-secondary" onclick="cancleOrder();">취소</button>
				</span> <br>
				<span>
				납품 요청일: <input type="date" id="dtIp" name="order_date" min="${minDay }" max="${maxDay }"/><br>
				</span>
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
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody id="insertProductList">
								
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</section>
	</div>

	<script src="${pageContext.request.contextPath}/resources/js/platform/insertOrder.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/insertOrder2.js"></script>
</body>
</html>