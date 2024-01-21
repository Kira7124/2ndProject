<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>Insert title here</title>
</head>
<body>
	
	
  	<h3>메인페이지 출력(/login/main.jsp)</h3>
    	<!--  로그인 여부(세션정보) 에 따라서 페이지 이동 (JSTL) -->
			<c:if test="${empty id}">
				<c:redirect url="/login/login"/>
			</c:if>
	
			<h2>${id } 님 안녕하세요</h2>

			<a href="/login/logout">
				<input type="button" value="로그아웃">
			</a>
	
			<hr>
			
			
			<a href="/login/info">		
				<h3>사원정보조회(info)</h3>
			</a>
			
			<a href="/login/update">		
				<h3>사원정보수정(info)</h3>
			</a>

			
			<c:if test="${!empty id && id.equals('admin')}">
				<!--  관리자(admin) 전용메뉴 -->
				<a href="/login/memberlist">		
					<h3>사원정보목록</h3>
				</a>
				<a href="/login/join">
					<h3>사원등록</h3>
				</a>
				<a href="/login/commoncodelist">
					<h3>공통코드목록</h3>
				</a>
				<a href="/login/commoncodejoin">
					<h3>공통코드등록</h3>
				</a>
			</c:if>	
	
	
	
	



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	

</body>
</html>