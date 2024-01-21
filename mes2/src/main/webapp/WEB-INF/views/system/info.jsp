<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>사원정보</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />


<style type="text/css">

 .info-profile-img {
    width: 150px; /* 원하는 크기로 조절합니다. */
    height: 190px; /* 가로 크기에 따라 세로 크기를 자동 조절합니다. */
  }


</style>

</head>
<body>


<div class="card mb-3" style="max-width: 540px;">
  <div class="row g-0">
    <div class="col-md-4">
		<img src="${pageContext.request.contextPath}/resources/img/members/${memberDTO.user_img}" class="info-profile-img">    
	</div>
    <div class="col-md-8">
      <div class="card-body">
      	<h5 class="card-title"><i class="fa-solid fa-list-ol"></i> 사번 : ${memberDTO.user_num}</h5>
        <h5 class="card-title"><i class="fa-solid fa-user"></i> 이름 : ${memberDTO.user_name}</h5>
        <h5 class="card-title"><i class="fa-solid fa-building"></i> 부서 : ${memberDTO.user_department}</h5>
        <h5 class="card-title"><i class="fa-solid fa-user-tie"></i> 직급 : ${memberDTO.user_position}</h5>
        <h5 class="card-title"><i class="fa-solid fa-phone"></i> 전화번호 : ${memberDTO.user_tel}</h5>
      </div>
    </div>
  </div>
</div>
		
		






</body>
</html>