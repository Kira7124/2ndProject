<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>입고 상세 목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/searchList.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/materials/inDetailList.css">
<script src="https://kit.fontawesome.com/11da345fca.js"
	crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
			<h2>입고 완료목록</h2>
			<form action="/materials/inDetailList" method="get" class="search">
				<select name="searchType" id="searchType" class="form-select"
					aria-label="Default select example">
					<option>검색선택</option>
					<option value="pd_lot">품목코드</option>
				</select>
				<div class="input-group searchSub" style="width: 50%; text-align: center;">
					<input type="text" name="search" id="keyword"
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
				<a href="/materials/inlist">
				<i class="fa-solid fa-reply" style="font-size:25px; margin: 10px; color: #A2C6A8;"></i>
				</a>
			</div>
			
			<div class="col-md-13" style="margin-right: 10px;">
				<a href="/materials/inventory">
					<i class="fa-solid fa-file-arrow-down" style="font-size:25px; margin: 10px; color: #A2C6A8;"></i>
				</a>
			</div>
		</div>
			
		








			<div class="list-box">
				<table class="table table-hover">
					<thead>
						<tr class="table-success" style="font-weight: bold">
							<td>창고코드</td>
							<td>입고코드</td>
							<td>품목명</td>
							<td>수량</td>
							<td>단위</td>
							<td>자재유형</td>
							<td>입고등록일</td>
							<td>진행상황</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="in" items="${inDetailList}">
							<tr>
								<td><c:out value="${in.warehouse_code}" /></td>
								<td><c:out value="${in.in_code}" /></td>
								<td><c:out value="${in.name}" /></td>
								<td><c:out value="${in.quantity}" /></td>
								<td><c:out value="${in.unit}" /></td>
								<td><c:out value="${in.category}" /></td>
								<td><fmt:formatDate value="${in.in_regdate}"
										pattern="yyyy-MM-dd" /></td>
								<td><c:if test="${in.status.equals('complete')}">
										<input type="hidden" class="product_code"
											value="${in.product_code}" />
										<button type="button" class="btn complete statusButton">완료</button>
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
							href="/materials/inDetailList?page=${pageVO.startPage - 1}"
							aria-label="Previous"> 
							<span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<c:forEach var="i" begin="${pageVO.startPage }"
						end="${pageVO.endPage }" step="1">
						<li class="page-item ${pageVO.cri.page == i ? 'active' : ''}">
							<a class="page-link" href="/materials/inDetailList?page=${i}">${i}</a>
						</li>
					</c:forEach>

					<c:if test="${pageVO.next }">
						<li class="page-item"><a class="page-link"
							href="/materials/inDetailList?page=${pageVO.endPage + 1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
			</nav>
		</section>
	</div>



</body>
</html>