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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/datepicker/1.9.0/datepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/datepicker/1.9.0/datepicker.min.js"></script>
<title>JoinForm</title>
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
	<h1>/login/join.jsp</h1>

 
	<div class="JoinForm">
			<h3 class="text-center text-primary mt-2 mb-4">
			<a href="#"></a>사원등록
			</h3>
	<div class = "container">
	   	<form method="post">
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">아이디</label>
		    <input type="text" class="form-control" placeholder="아이디를입력하세요" name="user_id" id="exampleInputEmail1" aria-describedby="emailHelp">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">비밀번호</label>
		    <input type="password" class="form-control" placeholder="비밀번호를입력하세요" name="user_pw" id="exampleInputPassword1">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">이름</label>
		    <input type="text" class="form-control" placeholder="이름을입력하세요" name="user_name" id="exampleInputPassword1">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">부서</label>
		    <input type="text" class="form-control" placeholder="부서를입력하세요" name="user_department" id="exampleInputPassword1">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">직급</label>
		    <input type="text" class="form-control" placeholder="직급을입력하세요" name="user_position" id="exampleInputPassword1">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">주민번호</label>
		    <input type="text" class="form-control" placeholder="주민번호를입력하세요" name="user_jumin" id="exampleInputPassword1">
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">입사일</label>
		    <input type="date" class="form-control" placeholder="입사일을입력하세요" name="user_joindate" id="joinDate">
		  </div>
		   <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">전화번호</label>
		    <input type="tel" class="form-control" placeholder="전화번호를입력하세요" name="user_tel" id="exampleInputPassword1">
		  </div>
		    <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">사원관리권한</label>
		    <input type="text" class="form-control" placeholder="권한을입력하세요 Y/N" name="user_auth" id="exampleInputPassword1">
		  </div>		
		  <button type="submit" class="btn btn-primary col-lg-12 col-12 mt-2 mb-3">사원등록</button>
		</form>
   </div>
</div>
	
	
	
 		

	
	

	
	
	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>