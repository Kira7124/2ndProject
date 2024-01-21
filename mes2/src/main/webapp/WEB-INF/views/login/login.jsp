<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>로그인</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/img/login.css">
<!-- css 불러오기 (경로설정 중요!!) -->
</head>

<script>
	$(document).ready(function(){
		$('#btn-post').click(function(event){
			// 버튼 클릭 시 폼의 기본 동작을 막음
			event.preventDefault();

			Swal.fire({
				icon : 'success',
				title : '로그인성공',
				text : '환영합니다'
			}).then((result) => {
				// SweetAlert 다이얼로그가 닫힌 후 실행되는 콜백 함수
				if (result.isConfirmed) {
					// SweetAlert 다이얼로그가 확인되었을 때, 페이지 이동
					// 근데 자꾸 /login/main 으로 설정해놨는데 loginget() 이 호출됨? => 해결!
					$('form').submit();
				}
			});	
		});
	});
</script>



<body>

    <header class="header">
      <nav class="nav">
        <a href="#" class="logo" style= "text-decoration: none;">Awsometic</a>
        <button class="btn" id="show-form">Login</button>
      </nav>
    </header>

    <section class="home">
      <div class="form-container">
        <i class="fas fa-xmark close-form"></i>
        <div class="form login-form">
          <form action="" method="post">
            <img src="/resources/img/awesometic2.jpg" id="login-icon">
            <div class="input-box">
              <input type="text" id="id" name="user_id" placeholder="아이디를 입력하세요" value="${rememberedId != null ? rememberedId : ''}" />
              <i class="fas fa-envelope email"></i>
            </div>

            <div class="input-box">
              <input type="password" name="user_pw" placeholder="패스워드를 입력하세요" />
              <i class="fas fa-lock password"></i>
              <i class="fas fa-eye-slash pass-hide"></i>
            </div>

            <div class="options-field">
              <span class="checkbox">
                <input type="checkbox" id="checkid" name="remember" value="chk" ${rememberedId != null ? 'checked' : ''}/>
                <label for="checkid">아이디기억하기</label>
              </span>
            </div>

            <button class="btn" id="btn-post" type="submit" style="background: #3CB371;">로그인하기</button>
            
          </form>
        </div>
    </section>
	
	


		<!-- 자바스크립트 동작 불러오기 (경로설정 중요!!) -->
	    <script src="${pageContext.request.contextPath}/resources/img/app.js"></script>
	
</body>
</html>