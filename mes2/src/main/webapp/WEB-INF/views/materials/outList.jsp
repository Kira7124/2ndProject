<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>출고 목록</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materials/outList.css">
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
	<%@ include file="../system/sidehead.jsp" %>
	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<h2>출고 목록</h2>
			<form class="search">
					<select name="status" id="status" class="form-select" aria-label="Default select example">
						<option value="">-- 진행상태 --</option>
						<option value="waiting">대기</option>
						<option value="complete">완료</option>
					</select>
					
					<div class="input-group">
						<span class="input-group-text">요청일자 </span>
						<input type="date" aria-label="First name" class="form-control" name="startDate" value="${osDTO.startDate }"/>
						<input type="date" aria-label="Last name" class="form-control" name="endDate" value="${osDTO.endDate }"/>
					</div>
					
					<div class="input-group searchSub" id="searchSub">
						<input type="text" name="product_code"  id="product_code" class="form-control" value="${osDTO.product_code }" placeholder="품목코드를 입력하세요" aria-label="Recipient's username" aria-describedby="button-addon2"/>
					</div>
					
					<button class="btn btn-secondary" type="submit" id="button-addon2">검색</button>
					<a href="/materials/outList"><i class="fa-solid fa-rotate-right" id="reset"></i></a>
			</form>
			<!-- 표 -->
			<div class="list">
				<div class="list-btn">
				</div>

				<div class="list-box">
					<form action="test" class="list-form">
						<table class="table table-hover">
							<thead>
								<tr class="table-success">
									<th scope="col">출고코드</th>
									<th scope="col">출고품목</th>
									<th scope="col">출고수량</th>
									<th scope="col">출고유형</th>
									<th scope="col">출고요청일</th>
									<th scope="col">출고등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="oList" items="${oList }">
									<tr onclick="getOutDetail('${oList.out_index }','${oList.out_code }');" class="selectOrder">
										<td>${oList.out_code }</td>
										<td>${oList.product_code }</td>
										<td>${oList.quantity } ${oList.pdto.unit}</td>
										<td>
											<c:choose>
												<c:when test="${oList.status eq 'waiting' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #ff9924;"></i> 대기
												</c:when>
												<c:when test="${oList.status eq 'complete' }">
													<i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i> 완료
												</c:when>
											</c:choose>
										</td>
										<td>${oList.out_request_date }</td>
										<td>${oList.out_regdate }</td>
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
							<a href="/materials/outList?page=${pageVO.startPage - 1 }&status=${osDTO.status }&startDate=${osDTO.startDate }&endDate=${osDTO.endDate}&product_code=${osDTO.product_code}" aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
							</a>
						</li>
					</c:if>
					
					<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
						<c:if test="${osDTO.cri.page ne i }">
							<li class="page-item page-action"><a class="page-link" href="/materials/outList?page=${i }&status=${osDTO.status }&startDate=${osDTO.startDate }&endDate=${osDTO.endDate}&product_code=${osDTO.product_code}" style="color: black;">${i }</a></li>
						</c:if>
						<c:if test="${osDTO.cri.page eq i }">
							<li class="active page-item page-action"><a class="page-link" href="/materials/outList?page=${i }&status=${osDTO.status }&startDate=${osDTO.startDate }&endDate=${osDTO.endDate}&product_code=${osDTO.product_code}">${i }</a></li>
						</c:if>
					</c:forEach>
					
					<c:if test="${pageVO.next }">
						<li class="page-item">
							<a class="page-link" href="/materials/outList?page=${pageVO.endPage + 1 }&status=${osDTO.status }&startDate=${osDTO.startDate }&endDate=${osDTO.endDate}&product_code=${osDTO.product_code}" aria-label="Next">
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
			$("#status").val("${osDTO.status}").attr("selected","selected");
		});
	</script>
	<script type="text/javascript">
		var result = '${result}';
		var quantitySum = '${quantitySum}';
		var product_code = '${product_code}';
		var insSum = '${insSum}';
		console.log(quantitySum, product_code);
		
		if(result == 'SUCCESS') {
			Swal.fire({
				text: "출고 등록이 완료되었습니다.",
				confirmButtonColor: "#577D71",
				icon: "success"
			});
		}
		
		if(quantitySum != "") {
			Swal.fire({
				html: '${product_code} 재고가 ${quantitySum}개, 생산 지시 수량이 ${insSum}개 입니다.<br> 생산 지시 하시겠습니까?',
				icon: "question",
				showCancelButton: true,
				confirmButtonColor: "#577D71", // confirm 버튼 색상
				cancelButtonColor: '#d33', // cancle 버튼 색상
				confirmButtonText: '확인', // confirm 버튼 텍스트 지정
				cancelButtonText: '취소', // cancel 버튼 텍스트 지정
			}).then((result) => {
				if (result.isConfirmed) {
					(async () => {
					    const { value: quantity } = await Swal.fire({
					        title: '생산 지시 수량을 입력하세요.',
					        input: 'number',
					        inputPlaceholder: '1000개 단위로 숫자만 입력하세요'
					    })
					    if (quantity) {
					    	insertInstructions(quantity, product_code);
					    }
					})()
				} else { 
					return false;
				}
			});
		}
		
		// 생산 지시
		function insertInstructions(quantity, product_code) {
			var instructData = {
				"mdp_code" : product_code,
				"sales_quantity" : quantity
			}
			
			$.ajax({
				url : "/rOut/instruction",
				type : "POST",
				data : JSON.stringify(instructData),
				contentType : "application/json",
				success : function(data) {
					Swal.fire({
						text: "생산 지시를 완료하였습니다.",
						confirmButtonColor: "#577D71",
						icon: "success"
					}).then(function(){
						location.reload();
					});
				},
				error : function() {
					Swal.fire({
						text: "생산 지시에 실패하였습니다.",
						confirmButtonColor: "#577D71",
						icon: "error"
					}).then(function(){
						location.reload();
					});
				}
			});
		}
	</script>
	<script src="${pageContext.request.contextPath}/resources/js/materials/out/outList.js"></script>
</body>
</html>