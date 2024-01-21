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
<title>품목 추가</title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/searchList.css">
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
	<div class="container2">
<!-- 		<h3 id="h3">발주 품목 등록</h3> -->
		<section class="section1">
			<form class="search" id="searchForm" onsubmit="searchProduct(); return false;">
				<select name="searchType" id="searchType" class="form-select" aria-label="Default select example">
					<option value="">구분</option>
					<option value="품목코드">품목코드</option>
					<option value="품목명">품목명</option>
				</select>
				<div class="input-group searchSub">
					<input type="text" name="search" id="search" class="form-control" value="${search }"placeholder="검색어를 입력하세요" aria-label="Recipient's username" aria-describedby="button-addon2">
				</div>
				<button class="btn btn-secondary" type="submit" id="button-addon2">검색</button>
			</form>

			<!-- 표 -->
			<div class="list">
				<div class="list-box">
					<form class="list-form">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<c:if test="${!empty mdpDTO }">
							<table class="table table-hover">
								<thead>
									<tr class="table-success">
										<th></th>
										<th scope="col">품목코드</th>
										<th scope="col">형상정보</th>
										<th scope="col">품목명</th>
										<th scope="col">단가</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="mdpDTO" items="${mdpDTO }" varStatus="loop">
											<tr>
												<td scope="row"><input type="radio" name="idx" value="${loop.index}"></td>
												<td><input type="hidden" name="product_code${loop.index}" value="${mdpDTO.product_code}">${mdpDTO.product_code}</td>
												<td><input type="hidden" name="image${loop.index}" ><img alt="형상정보" src="<%=request.getContextPath() %>/resources/img/metadata/${mdpDTO.ofileName }" onerror="this.onerror=null; this.src='/resources/img/metadata/default.png';"></td>
												<td><input type="hidden" name="name${loop.index}" value="${mdpDTO.name}">${mdpDTO.name}</td>
												<td><input type="hidden" name="price${loop.index}" value="${mdpDTO.price}"><fmt:formatNumber value="${mdpDTO.price}" />원</td>
											</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- 페이징 -->
							<div class="page-nav">
								<nav aria-label="Page navigation example">
									<ul class="pagination">
								
									<c:if test="${pageVO.prev }">
										<li class="page-item page-action">
											<a href="/platform/searchList?page=${pageVO.startPage - 1 }" aria-label="Previous">
												<span aria-hidden="true">&laquo;</span>
											</a>
										</li>
									</c:if>
									
									<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
										<c:if test="${cri.page ne i }">
											<li class="page-item page-action"><a class="page-link" href="/platform/searchList?page=${i }&searchType=${searchType }&search=${search }" style="color: black;">${i }</a></li>
										</c:if>
										<c:if test="${cri.page eq i }">
											<li class="active page-item page-action"><a class="page-link" href="/platform/searchList?page=${i }&searchType=${searchType }&search=${search }">${i }</a></li>
										</c:if>
									</c:forEach>
									
									<c:if test="${pageVO.next }">
										<li class="page-item">
											<a href="/platform/searchList?page=${pageVO.endPage + 1 }">
											    <span aria-hidden="true">&raquo;</span>
											</a>
										</li>
									</c:if>
									</ul>
								</nav>
							</div>
							<!-- 페이징 끝 -->
							<button class="btn btn-secondary regist" onclick="registProduct();">등록</button>
							<button class="btn btn-secondary regist" onclick="window.close();">취소</button>
						</c:if>
					</form>
				</div>
			</div>
		</section>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#searchType").val("${searchType}").attr("selected","selected");
		});
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/platform/searchList.js"></script>
</body>
</html>