<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>거래처 관리</title>

<!--  sweetalert cdn입니다. -->
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>


<!-- jqery cdn입니다-->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<!-- 부트스트랩 css cdn입니다. -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<!-- 추가 css 입니다. -->
<link rel="stylesheet" href="/resources/css/metadata/business.css?after">



<!-- 추가 js 입니다. -->
<script src="/resources/js/metadata/business.js?after"></script>

<!--  주소 cdn -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>


<body>
<%@ include file="../system/sidehead.jsp" %>
	<!-- 내용 칸 -->
	<div class="son_container">
	<h2>거래처 기준 정보</h2>
		<!-- 검색창,추가 버튼,취소 버튼 -->	
		<div class="form-container">
			
			<form action="/business/firstpage" method="POST" class="search">
							
				<input type="text" name="search" placeholder="거래처명을 입력하세요" class="form-control"
				 aria-label="Recipient's username" aria-describedby="button-addon2"/>
				<input type="submit" value="검색" class="btn btn-secondary" id="button-addon2"/>
				<a><i class="fa-solid fa-rotate-right" onclick="redirectToFirstPage()" style="cursor: pointer; font-size: 20px;"></i></a>
			</form>		
		</div>		
			
		<div class="son_list-btn">			
				<button type="button" class="btn btn-secondary" id="addbtn" onclick="replaceButton()">추가</button>							
				<button type="button" class="btn btn-secondary" id="updatebtn" onclick="replaceButton2()">수정</button>						
				<button type="button" class="btn btn-secondary" id="canclebtn" onclick="redirectToFirstPage()" style="display: none;">취소</button>
		</div>
		<br>
					

		<!-- 테이블 -->									
		<table class="table table-hover">
			
			<!--테이블 헤드 -->
			<thead>
				<tr class="table-success" id="table_head">
					<th scope="col"></th>
					<th scope="col">거래처코드</th>
					<th scope="col">유형</th>
					<th scope="col">비밀번호</th>
					<th scope="col">거래처명</th>
					<th scope="col">담당자</th>
					<th scope="col">주소</th>
					<th scope="col">전화번호</th>
					<th scope="col">fax</th>
					<th scope="col">email</th>
					<th scope="col">거래상황</th>									
					<th scope="col"></th>									
				</tr>
			</thead>
			
			<!-- 테이블 바디 -->				
			<tbody>
				<!-- 거래처 추가시 생기는 행 -->				
				<tr id="insert_hang" style="display: none; text-align: center; vertical-align: middle;">										
					<td></td>							
					<td>[코드자동생성]</td>																				
					<td>
					<select id="typeSelect" onchange="handleTypeSelect()">					
					<option>고객사</option>
					<option>유통업체</option>
					</select>
					</td>										    								      												
					<td><input type="text" name="pw" size="5" value="1234" readonly></td>
					<td><input type="text" name="name" size="5"></td>
					<td><input type="text" name="manager" size="5"></td>				
					
					
					<td>				
					<input type="text" id="join_address" name="address" size="30">
					<input type="button" id="join_button"  onclick="sample1_execDaumPostcode()" value="검색">													
					</td>
					
					
																		
					<td><input type="text" name="call" size="5" style="width: 120px; "></td>				
					<td><input type="text" name="fax" size="5" style=" width: 114px; "></td>
					<td><input type="text" name="email" size="5" style=" width: 200px; "></td>
					<td><i class="fa-solid fa-circle fa-2xs" style="color: #4486ff;"></i>계약 중</td>														
					<td><button type="button" class="btn btn-secondary" id="submitbtn" onclick="submitData()" style="display: none;">저장</button></td>														
				</tr>	
													
				<!-- 거래처리스트 행들을 가져옴 -->								
				<c:forEach var="blist" items="${businessList }">
				<tr style="text-align: center; vertical-align: middle; ">
					
					<!-- 거래처 리스트 -->
					<td><input type="checkbox" class="updatecheckbox" style="display: none;" onchange="a(this)"/></td>															
					<td class="a">${blist.company_code }</td>
					<td class="a">${blist.category }</td>
					<td class="a">${blist.pw }</td>
					<td class="a">${blist.name }</td>
					<td class="a">${blist.manager }</td>
					<td class="a">${blist.address }</td>
					<td class="a">${blist.call }</td>
					<td class="a">${blist.fax }</td>
					<td class="a">${blist.email }</td>
					<td class="a">
                    <c:choose>
                       <c:when test="${blist.contract_status eq '1' }">
                          <i class="fa-solid fa-circle fa-2xs" style="color: #4486ff;"></i> 계약 중
                       </c:when>
                       <c:when test="${blist.contract_status eq '0' }">
                          <i class="fa-solid fa-circle fa-2xs" style="color: #6b6b6b;"></i> 계약만료
                       </c:when>
                    </c:choose>                            
					</td>
					<td class="a" style="content: '\00a0'"></td>				
									
					<!-- 거래처 수정 시 나타나는 행 -->									
					<td class="b" style="display: none;">${blist.company_code }</td>
					<td class="b" style="display: none;">${blist.category }</td>
					<td class="b" style="display: none;">
					<c:if test="${blist.category eq '고객사'}">
					<input type="text" name="pw" size="5" value="${blist.pw }">
					</c:if>
					</td>
					<td class="b" style="display: none;"><input type="text" name="name" size="5" value="${blist.name }"></td>
					<td class="b" style="display: none;"><input type="text" name="manager" size="5" value="${blist.manager }"></td>
					
					
					
					<td class="b" style="display: none;">
					<input type="text" name="address" size="30" value="${blist.address }">
					<input type="button" id="join_button"  onclick="sample1_execDaumPostcode()" value="검색">	
					
					</td>
					
					
					
					<td class="b" style="display: none;"><input type="text" name="call" size="5" value="${blist.call }"></td>
					<td class="b" style="display: none;"><input type="text" name="fax" size="5" value="${blist.fax }"></td>
					<td class="b" style="display: none;"><input type="text" name="email" size="5" value="${blist.email }"></td>
					<td class="b" style="display: none;">
					<select id="upd_status" name="upd_status">
							<option>거래상황</option>
							<option value="1">계약 중</option>
							<option value="0">계약 중단</option>
					</select>
					
					</td>
					<td class="b" style="display: none; width: 80px; ">
						<button type="button" style="margin: 10px 0;" class="btn btn-secondary" id="submitbtn2" onclick="submitData2(this)" >수정</button>
						<button type="button" style="margin: 10px 0;" class="btn btn-secondary" id="submitbtn3" onclick="submitData3(this)" >삭제</button>																		 									
					</td>									
				</tr>
				</c:forEach>
			</tbody>
		</table>

			
		<!-- 페이징 -->
		<div class="box-footer clearfix">
			<div style="margin: 0 auto; width: fit-content;" class="pagination-container">
				<ul class="pagination pagination-sm no-margin pull-right">
				
				<c:if test="${pageVO.prev }">
					<li><a href="/business/firstpage?page=${pageVO.startPage - 1 }">«</a></li>
				</c:if>
				
				<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
					<li class=${pageVO.cri.page == i ? "active":"" }>
						<a href="/business/firstpage?page=${i }&search=${aDTO.search }">
							${i }
						</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageVO.next }">
					<li><a href="/business/first?page=${pageVO.endPage + 1 }">»</a></li>
				</c:if>
			
				</ul>
			</div>
		</div>
		<!-- 페이징 끝 -->
	</div>


</body>
</html>