<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="${pageContext.request.contextPath}/resources/img/favicon.ico"
	rel="shortcut icon" type="image/x-icon">
<title>발주목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/searchList.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/purchaselist.css">
<script src="https://kit.fontawesome.com/11da345fca.js"
	crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- 글씨체 -->
</head>
<body>
	<%@ include file="../system/sidehead.jsp"%>

	<div class="container">
		<section class="section1">
			<h2>발주목록</h2>
			<form action="/materials/purchaselist" method="get" class="search">

				<select name="searchType" id="searchType" class="form-select"
					aria-label="Default select example">
					<option>검색선택</option>
					<option value="name">품목명</option>
					<option value="category">자재유형</option>
				</select>

				<div class="input-group searchSub" style="width: 50%;">
					<input type="text" name="keyword" id="keyword"
						class="form-control fm" aria-label="Recipient's username"
						aria-describedby="button-addon2">
					<button class="btn btn-secondary" type="submit" id="button-addon2"
						onclick="return check();">검색</button>
				</div>
			</form>

			<script>
	
		function check() {
			
			var key = $("#keyword").val();
			if(key == null || key == ""){
				Swal.fire({
					  title: "검색어를 입력하세요!",
					  icon: "warning"
					}).then((result) => {
						$("#keyword").focus();
});
				
				return false;
			}
				
		}
	</script>

			<div style="display: flex; justify-content: flex-end;">
				<div class="col-md-13" style="margin-right: 10px;">
					<button type="button" onclick="openInputOrder();"
						style="border: none; background: none;">
						<i class="fa-solid fa-pen"
							style="font-size: 25px; margin: 10px; color: #A2C6A8;"></i>
					</button>
				</div>

				<div class="col-md-13" style="margin-right: 10px;">
					<a href="/materials/materials"> <i
						class="fa-solid fa-file-arrow-down"
						style="font-size: 25px; margin: 10px; color: #A2C6A8;"></i></a>
				</div>
			</div>



			<div class="list-box">
				<a href="/materials/purchase"></a>
				<table class="table table-hover">


					<thead>
						<tr class="table-success" style="font-weight: bold">
							<td>발주코드</td>
							<td>품목명</td>
							<td>자재유형</td>
							<td>원가</td>
							<td>단가</td>
							<td>발주수량</td>
							<td>발주등록일</td>
							<td>진행상황</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pl" items="${purchaselist}">
							<tr>
								<td><c:out value="${pl.orders_code}" /></td>
								<td><c:out value="${pl.name}" /></td>
								<td><c:out value="${pl.category}" /></td>
								<td><c:out value="${pl.cost}" /></td>
								<td><c:out value="${pl.price}" /></td>
								<td><c:out value="${pl.quantity}" /></td>
								<td><fmt:formatDate value="${pl.regdate}"
										pattern="yyyy-MM-dd" /></td>

								<td><c:if test="${pl.status.equals('waiting')}">
										<form action="/materials/updateStatus" method="post">
											<input type="hidden" value="${pl.orders_index}"
												name="orders_index">
											<button type="submit" class="btn statusButton waiting">대기</button>
										</form>
									</c:if> <c:if test="${pl.status.equals('complete')}">
										<button type="button" class="btn statusButton complete">완료</button>
									</c:if></td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<nav aria-label="Page navigation example"
				style="padding: 5px; margin: 0 auto;">
				<ul class="pagination justify-content-center">
					<c:if test="${pageVO.prev }">
						<li class="page-item"><a class="page-link"
							href="/materials/purchaselist?page=${pageVO.startPage - 1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<c:forEach var="i" begin="${pageVO.startPage }"
						end="${pageVO.endPage }" step="1">
						<li class="page-item ${pageVO.cri.page == i ? 'active' : ''}">
							<a class="page-link" href="/materials/purchaselist?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<c:if test="${pageVO.next }">
						<li class="page-item"><a class="page-link"
							href="/materials/purchaselist?page=${pageVO.endPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>

		</section>
	</div>

	<script>
		function openInputOrder() {
			window.open("/materials/inputOrder", "order",
					"width=500,height=720");
		}
	
	
		
	</script>


</body>
</html>