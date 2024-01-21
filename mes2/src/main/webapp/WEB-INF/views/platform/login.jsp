<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>고객사 로그인</title>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/platform/login.css">
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->
</head>

<body>
	<header class="header">
		<nav class="nav">
			<a href="#" class="logo" style="text-decoration: none;"><img src="${pageContext.request.contextPath}/resources/img/icons/AWESOMETIC.png" id="login-icon"></a>
			<button class="btn" id="show-form">Login</button>
		</nav>
	</header>

	<section class="home">
		<div class="form-container">
			<i class="fas fa-xmark close-form"></i>
			<div class="form login-form">
				<form method="post">
					<img src="${pageContext.request.contextPath}/resources/img/icons/AWESOMETIC.png" id="login-icon">
					<div class="input-box">
						<input type="text" id="id" name="company_code" placeholder="아이디를 입력하세요" /> <i class="fas fa-envelope email"></i>
					</div>

					<div class="input-box">
						<input type="password" name="pw" placeholder="패스워드를 입력하세요" />
						<i class="fas fa-lock password"></i>
						<i class="fas fa-eye-slash pass-hide"></i>
					</div>

					<input type="hidden" name="${_csrf.parameterName }"
						value="${_csrf.token }">
					<button class="btn" id="btn-post" type="submit">로그인하기</button>

				</form>
			</div>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/resources/js/platform/login.js"></script>
</body>
</html>