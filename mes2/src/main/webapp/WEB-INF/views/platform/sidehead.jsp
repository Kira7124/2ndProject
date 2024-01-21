<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--  페이지도 인코딩 해줘야함 -->
<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Drop Down Sidebar Menu | CodingLab</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/platform/sideheadstyle.css">
<!-- Boxiocns CDN Link -->
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css'
	rel='stylesheet'>

<!-- 부트스트랩 cdn 추가-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- Font Awesome 라이브러리 추가 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->
</head>
<body>
	<div class="sidebar close">
		<div class="logo-details">
			<i class="fa-solid fa-bars"></i> <span class="logo_name">AWESOMETIC</span>
		</div>
		<ul class="nav-links">
			<li>
				<div class="iocn-link">
					<a href="/platform/orderList"> <i
						class="fa-solid fa-cart-shopping"></i> <span class="link_name">발주관리</span>
					</a>
				</div>
			</li>
			<li>
				<div class="profile-details">
<!-- 					<div class="profile-content"> -->
<!-- 						<img src="" alt="프로필 사진 자리"> -->
<!-- 					</div> -->
					<div class="name-job">
						<div class="profile_name">AWESOMETIC</div>
<!-- 						<div class="job">Web Desginer</div> -->
					</div>
				</div>
			</li>
		</ul>
	</div>
	<section class="home-section">
		<div class="home-content">
			<span class="text"></span>

			<div class="dropdown">
				<button class="btn btn-secondary dropdown-toggle" type="button"
					data-bs-toggle="dropdown" aria-expanded="false">
					<i class="fa-solid fa-user-tie"
						style="color: #e8edf7; font-size: 21px;"></i>
				</button>
				<ul class="dropdown-menu">
					<li><a class="dropdown-item" href="#updateModal" rel="modal:open">정보 수정</a></li>
					<li><a class="dropdown-item" href="/platform/logout">로그아웃</a></li>
				</ul>
			</div>
		</div>
	</section>

	<!-- 정보 수정 모달 -->
<!-- 	<div id="updateModal" class="modal"> -->
<!-- 		<form> -->
<%-- 			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> --%>
<%-- 			<input type="hidden" name="nowPw" id="nowPw" value="${mdto.pw }"> --%>
<!-- 			<div> -->
<!-- 				<div class="list"> -->
<%-- 					<span>회사명: <input type="text" name="name" value="${mdto.name }" readonly></span> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<!-- 					<span>현재 비밀번호: <input type="password" name="pw" id="pw"></span> -->
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<!-- 					<span>수정 비밀번호: <input type="password" name="newPw" id="newPw"></span><br> -->
<!-- 					<span id="newPassword" class="explainNewPw">* 영어 대소문자, 숫자를 포함하여 8~12자리</span> -->
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<!-- 					<span>비밀번호 확인: <input type="password" name="checkPw" id="checkPw"></span><br> -->
<!-- 					<span id="checkPassword" class="explainCheckPw">* 수정 비밀번호 재입력</span> -->
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<span>담당자: <input type="text" name=manager value="${mdto.manager }" readonly></span> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<span>전화번호: <input type="text" name=manager value="${mdto.call }" readonly></span> --%>
<!-- 				</div> -->
<!-- 				<div class="list"> -->
<%-- 					<span>주소: <input type="text" name="address" value="${mdto.address }" readonly></span> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			
<!-- 			<div class="list-btn"> -->
<!-- 				<button type="button" class="btn btn-secondary" onclick="modifyPw();">수정하기</button> -->
<!-- 				<a href="#" rel="modal:close"><button type="button" class="btn btn-secondary" >닫기</button></a> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 	</div> -->

	<div id="updateModal" class="modal">
	    <form>
	        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	        <input type="hidden" name="nowPw" id="nowPw" value="${mdto.pw}">
	        <div class="modal-header">
	            <h5 class="modal-title">회사 정보 수정</h5>
	        </div>
	        <div class="modal-body">
	            <div class="form-group">
	                <label for="companyName">회사명:</label>
	                <input type="text" class="form-control" name="name" value="${mdto.name}" readonly>
	            </div>
	            <div class="form-group">
	                <label for="currentPassword">현재 비밀번호:</label>
	                <input type="password" class="form-control" name="pw" id="pw">
	            </div>
	            <div class="form-group">
	                <label for="newPassword">수정 비밀번호:</label>
	                <input type="password" class="form-control" name="newPw" id="newPw">
	                <span id="newPassword" class="explainNewPw">* 영어 대소문자, 숫자를 포함하여 8~12자리</span>
	            </div>
	            <div class="form-group">
	                <label for="confirmPassword">비밀번호 확인:</label>
	                <input type="password" class="form-control" name="checkPw" id="checkPw">
	                <span id="checkPassword" class="explainCheckPw">* 수정 비밀번호 재입력</span>
	            </div>
	            <div class="form-group">
	                <label for="manager">담당자:</label>
	                <input type="text" class="form-control" name="manager" value="${mdto.manager}" readonly>
	            </div>
	            <div class="form-group">
	                <label for="phoneNumber">전화번호:</label>
	                <input type="text" class="form-control" name="manager" value="${mdto.call}" readonly>
	            </div>
	            <div class="form-group">
	                <label for="address">주소:</label>
	                <input type="text" class="form-control" name="address" value="${mdto.address}" readonly>
	            </div>
	        </div>
	        <div class="modal-footer">
	            <button type="button" class="btn btn-primary" id="modifyBtn" onclick="modifyPw();">수정하기</button>
	            <a href="#" rel="modal:close"><button type="button" class="btn btn-secondary" >닫기</button></a>
	        </div>
	    </form>
	</div>

		
	<!-- 사이드바 script -->
	<script src="${pageContext.request.contextPath}/resources/js/platform/sideheadscript.js"></script>

</body>
</html>
