<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login/login.css">
<!-- css 불러오기 (경로설정 중요!!) -->
<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->
</head>





<script>
    $(document).ready(function () {
        // URL에서 loginFailed 파라미터를 가져오기
        var urlParams = new URLSearchParams(window.location.search);
        var loginFailed = urlParams.get('loginFailed');

        // 문자열 "true"와 비교
        if (loginFailed === 'true') {
            // SweetAlert로 로그인 실패 메시지 표시
            Swal.fire({
                icon: 'error',
                title: '로그인 실패',
                text: '아이디 또는 비밀번호가 일치하지 않습니다!'
            });
        }


    });
</script>


<body>

    <header class="header">
      <nav class="nav">
        <a href="#" class="logo" style= "text-decoration: none;"><img src="${pageContext.request.contextPath}/resources/img/icons/AWESOMETIC.png" id="login-icon"></a>
        <button class="btn" id="show-form" style="background: #577D71;">Login</button>
      </nav>
    </header>

    <section class="home">
      <div class="form-container">
        <i class="fas fa-xmark close-form"></i>
        <div class="form login-form">
          <form action="" method="post">
            <img src="${pageContext.request.contextPath}/resources/img/icons/AWESOMETIC.png" id="login-icon">
            
           
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

            <button class="btn" id="btn-post" type="submit" style="background: #577D71;">로그인하기</button>
            
          </form>
        </div>
      </div> 
    </section>
	
	


		<!-- 자바스크립트 동작 불러오기 (경로설정 중요!!) -->
	    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>
	
</body>
</html>