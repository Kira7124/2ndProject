<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/members/info.jsp</h1>
	
	<form action="" method="post">
		<table border ="1">
		<tr>
			<th>프사</th>
			<td>프사창</td>
		</tr>
		<tr>
			<th>사원번호</th>
			<td>${memberDTO.user_num}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${memberDTO.user_id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${memberDTO.user_name}</td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td>${memberDTO.user_jumin}</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>${memberDTO.user_department}</td>
		</tr>
		<tr>
			<th>직급</th>
			<td>${memberDTO.user_position}</td>
		</tr>
		<tr>
			<th>입사일</th>
			<td>${memberDTO.user_joindate}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${memberDTO.user_tel}</td>
		</tr>
		
	</table>
</form>	




	<a href="/login/main"> 메인페이지로 </a>

</body>
</html>