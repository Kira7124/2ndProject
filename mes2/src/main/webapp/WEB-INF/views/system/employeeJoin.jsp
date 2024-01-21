<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>사원등록</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

 <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>

<!-- 부트스트랩 css cdn입니다. -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<!--  부트스트랩 js cdn입니다. -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous">
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>

<script>
    function checkId() {
        const userId = document.getElementById("join-id").value;

        $.ajax({
            url: "/system/checkID",
            type: "GET",
            data: {
                user_id: userId
            },
            success: function (data) {
                // 결과 처리
                if (data === "duplicate") {
                    alert("이미존재하는 ID 입니다!");
                } else if (data === "not-duplicate") {
                    alert("사용가능한 ID 입니다!");
                } else {
                    // 서버에서 다른 형태의 응답이 오는 경우에 대한 처리
                    alert("서버 응답 형식이 올바르지 않습니다.");
                }
            },
            error: function () {
                // 서버 통신 에러 발생 시 처리
                alert("서버 통신 중 에러가 발생했습니다.");
            }
        });
    }
</script>



<body>

<form method="post" action="/system/join">
		    	<div class="modal-body" style="max-width: 1100px;">
		    		<br>
		    		  <div class="card mb-3" style="max-width: 1100px;"> 
		    		  	 <div class="row g-0">
		    		   				<div class="col-md-4">
            						    <img src="${pageContext.request.contextPath}/resources/img/join/joining.jpg" class="img-fluid rounded-start" alt="...">
          							</div>
		    		          <div class="col-md-8">
		    		               <div class="card-body">
		    		     <label for="exampleInputEmail1" class="form-label">아이디</label>
						      <div class="input-box">
							      <input type="text" placeholder="아이디를입력하세요" name="user_id" id="join-id">
							      <input type="button" id="check-id" value="중복확인"  onclick="checkId()">
				   			 </div>
		    		   	 <label for="exampleInputEmail1" class="form-label">비밀번호</label>
						   <div class="input-box">
				              <input type="password" name="user_pw" placeholder="패스워드를 입력하세요" />
				              <i class="fas fa-eye-slash pass-hide"></i>
				           </div>
					      <div class="mb-3">
						    <label for="exampleInputPassword1" class="form-label">이름</label><br>
						    <input type="text"  placeholder="이름을입력하세요" name="user_name" id="exampleInputPassword1">
						  </div>  
						<div class="mb-3">
						    <label for="user_department" class="form-label">부서</label>
						    <select  name="user_department" id="user_department">
									<option value="인사">인사</option>
									<option value="영업">영업</option>
									<option value="자재">자재</option>
									<option value="생산">생산</option>
						    </select>
						 </div>		           
				         <div class="mb-3">
						    <label for="user_position" class="form-label">직급</label>
						    <select name="user_position" id="user_position">
									<option value="사장">사장</option>
									<option value="이사">이사</option>
									<option value="차장">차장</option>
									<option value="부장">부장</option>
									<option value="과장">과장</option>
									<option value="대리">대리</option>
									<option value="주임">주임</option>
									<option value="사원">사원</option>
						    </select>
						  </div>  
							 <div class="mb-3">
							    <label for="exampleInputPassword1" class="form-label">주민번호</label><br>
							    <input type="text" placeholder="주민번호를입력하세요" name="user_jumin" id="juminNum">-
							    <input type="password" placeholder="주민번호를입력하세요" name="user_jumin2" id="juminNum">
							  </div>
							   <div class="mb-3">
							    <label for="exampleInputPassword1" class="form-label">입사일</label>
							    <input type="date" placeholder="입사일을입력하세요" name="user_joindate" id="joinDate">
							  </div>
							   <div class="mb-3">
							    <label for="exampleInputPassword1" class="form-label">전화번호</label><br>
							    <input type="tel"  placeholder="전화번호를입력하세요" name="user_tel1" id="exampleInputPassword1">-
							    <input type="tel"  placeholder="전화번호를입력하세요" name="user_tel2" id="exampleInputPassword1">-
							    <input type="tel"  placeholder="전화번호를입력하세요" name="user_tel3" id="exampleInputPassword1">
							  </div>
							  <div class="mb-3">
							    <label for="exampleInputPassword1" class="form-label">주소</label><br>
							    <input type="text"  id="join_postcode" placeholder="우편번호" name="zip-code">
							    <input type="button" class="btn btn-secondary"  id="join_button"  onclick="sample6_execDaumPostcode()" value="주소 검색"><br>
							    <input type="text"  id="join_address" placeholder="주소" name="address">
							    <input type="text"  id="join_detailAddress" placeholder="상세주소" name="address_detail">
							  </div>
							   <div class="mb-3">
							    <label for="user_auth" class="form-label">사원권한</label>
							    <select  name="user_auth" id="user_auth">
										<option value="시스템관리" ${memberDTO.user_auth eq '시스템관리' ? 'selected' : ''}>시스템관리</option>
										<option value="기준정보관리" ${memberDTO.user_auth eq '기준정보관리' ? 'selected' : ''}>기준정보관리</option>
										<option value="생산관리" ${memberDTO.user_auth eq '생산관리' ? 'selected' : ''}>생산관리</option>
										<option value="영업관리" ${memberDTO.user_auth eq '영업관리' ? 'selected' : ''}>영업관리</option>
										<option value="자재관리" ${memberDTO.user_auth eq '자재관리' ? 'selected' : ''}>자재관리</option>
										<option value="플랫폼관리" ${memberDTO.user_auth eq '플랫폼관리' ? 'selected' : ''}>플랫폼관리</option>
							    </select>
							  </div>
<!-- 							  	  <div class="mb-3"> -->
<!-- 									 <label for="user_auth" class="form-label">권한메뉴상태</label> -->
<!-- 										<select  name="menu_status" id="menu_status"> -->
<!-- 											<option value="Y">Y</option>   -->
<!-- 									  		<option value="N">N</option>  -->
<!-- 									   </select> -->
<!-- 								  </div> -->
								   </div>
							   </div>
							</div>
						</div>			 
					</div>	
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-info" id="joinButton">등록하기</button>
		    		</div>
		    </form>	











<script src="${pageContext.request.contextPath}/resources/js/join/address.js"></script>
</body>
</html>