<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>재고 현황</title>
<sec:csrfMetaTags/>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materials/stockInsert.css">
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
	<div class="container2">
		<h3 id="h3">${stockList[0].product_code } 재고현황</h3>
		<section class="section1">
			<!-- 표 -->
			<div class="list">
				<div class="list-box">
					<form class="list-form">
						<c:if test="${!empty stockList }">
							<table class="table table-hover">
								<thead>
									<tr class="table-success">
										<th scope="col">로트번호</th>
										<th scope="col">입고일자</th>
										<th scope="col">창고위치</th>
										<th scope="col">현재재고</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="stockDTO" items="${stockList }">
											<tr>
												<td>
													<input type="hidden" name="pd_lot${stockDTO.stock_index}" value="${stockDTO.pd_lot}">
													<c:if test="${empty stockDTO.pd_lot}">
														-
													</c:if>
													<c:if test="${!empty stockDTO.pd_lot}">
														${stockDTO.pd_lot}
													</c:if>
												</td>
												<td>${stockDTO.regdate }</td>
												<td>${stockDTO.warehouse_code }</td>
												<td>${stockDTO.quantity}</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
						<c:if test="${empty stockList }">
							<div>현재 재고가 없습니다.</div>
						</c:if>
						<button class="btn btn-secondary regist" onclick="window.close();">닫기</button>
					</form>
				</div>
			</div>
		</section>
	</div>
	
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/stockInsert.js"></script>
</body>
</html>