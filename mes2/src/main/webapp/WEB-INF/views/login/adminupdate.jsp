<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" integrity="sha512-XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" crossorigin="anonymous" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>AdminUpdate</title>
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
		    <label for="exampleInputEmail1" class="form-label">아이디</label>
		    <input type="text" class="form-control" placeholder="아이디를입력하세요" value ="${memberDTO.user_id}" readonly>
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">비밀번호</label>
		    <input type="password" class="form-control" placeholder="비밀번호를입력하세요" name="user_pw">
		  	<i class="fas fa-eye pass-hide" style="cursor: pointer;"></i>  
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">이름</label>
		    <input type="text" class="form-control" placeholder="이름을입력하세요" name="user_name" value ="${memberDTO.user_name}">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">부서</label>
		    <input type="text" class="form-control" placeholder="부서를입력하세요" name="user_department" value ="${memberDTO.user_department}">
		  </div>	
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">직급</label>
		    <input type="text" class="form-control" placeholder="직급을입력하세요" name="user_position" value ="${memberDTO.user_position}">
		  </div>	
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">전화번호</label>
		    <input type="text" class="form-control" placeholder="전화번호를입력하세요" name="user_tel" value ="${memberDTO.user_tel}">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">직원관리권한</label>
		    <input type="text" class="form-control" placeholder="권한입력" name="user_auth" value ="${memberDTO.user_auth}">
		  </div>			
		  <button type="submit" class="btn btn-primary col-lg-12 col-12 mt-2 mb-3">사원정보수정</button>
		</form>
   </div>
</div>
	



	<!-- 패스워드 눈 모양 (보이기/안보이기 기능!) -->
<script>
  var passHide = document.querySelectorAll(".pass-hide");

  passHide.forEach((icon) => {
    icon.addEventListener("click", () => {
      let passInput = icon.parentElement.querySelector("input");
      if (passInput.type === "password") {
        passInput.type = "text";
        icon.classList.replace("fa-eye-slash", "fa-eye");
      } else {
        passInput.type = "password";
        icon.classList.replace("fa-eye", "fa-eye-slash");
      }
    });
  });
</script>
	 		




	
	

	
	
	
	
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
</body>
</html>