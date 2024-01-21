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
<title>메뉴수정</title>
<style type = "text/css">

html{
	height:100%;
}

body{
	margin:0px;
	padding:0px;
	background-position: center;
	background-repeat:no-repeat;
	background-size:cover;
	height:100%;
	overflow:hidden;
	
}
.JoinForm{
	
	background-color: #ffffff; 	
	width:30%;
	height: auto;
	margin: 7% auto;
	padding: 25px 10px;
	
	
}
.primg{
	 float: left; /* 이미지를 왼쪽으로 띄우기 */
  	 margin-right: 10px; /* 이미지와 오른쪽 내용 간격 조절 (원하는 만큼 조절 가능) */

}


.text-center {
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
}





</style>
</head>

<body>
	<h1>/members/adminupdate.jsp</h1>

	
 <div class="UpdateForm">
	<div class = "container">
	   	<form method="post">
		   <div class="mb-3">
			  <select class="form-control" name="user_auth" id="user_position">
			  	<option value="시스템관리">시스템관리</option>  
			  	<option value="기준정보관리">기준정보관리</option>  
			  	<option value="생산관리">생산관리</option>  
			  	<option value="영업관리">영업관리</option>  
			  	<option value="자재관리">자재관리</option>  
			  	<option value="플랫폼관리">플랫폼관리</option>  
			  </select>
		  </div>
		  
		  <div class="mb-3">
			<select class="form-control" name="menu_status" id="menu_status">
				<option value="Y">Y</option>  
		  		<option value="N">N</option> 
		    </select>
		  </div>
		  
		  <button type="submit" class="btn btn-primary col-lg-12 col-12 mt-2 mb-3">메뉴수정</button>
		</form>
   </div>
</div>
	

 		

	
	

	
	
	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>