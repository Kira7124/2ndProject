<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>사원목록</title>



<style text="text/css">

 

 .list {	
  	margin-left: 100px;
  	margin-top: 10px;
    box-sizing: border-box;
   	width: 93%;
        }

 .table th, .table td {
    text-align: center;
    vertical-align: middle; 
    border : none;
        }


.table tr td {
    border-bottom: 1px solid #dee2e6;  /* td 아랫쪽에만 border 추가 */
}

.profile-img{
 	width: 100px; /* 원하는 크기로 조절합니다. */
    height: 100px; 
}


.btn.btn-success.first{
	margin-bottom: 10px;
}


.searchInput{
	flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
    margin-right: 5px; /* 검색어 입력란 오른쪽과의 간격 설정 */
}


.searchInputdiv {
    display: flex;
    justify-content: flex-start;
    align-items: center; 
    margin-right: 1250px;
}



 .page-item {
       margin-right: 5px; /* 원하는 간격 크기로 조정 */
 }


</style>

<script>
        function openUpdateModal(userId) {
            console.log('모달을 열 때 사용할 userId:', userId);

            var userNumInput = document.getElementById('user_num_input');
            if (userNumInput) {
                userNumInput.value = userId;
            }

        }
</script>



</head>




<body>




<%@ include file="sidehead.jsp" %>	
			<!-- 표 -->

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

			
		   <!-- 등록하기 버튼(상단의) -->
		   <h2 style ="margin-left: 125px;">사원리스트</h2>
			<div class="list">
			  <div class="d-flex justify-content-end">
			   <div class="searchInputdiv">
			 	<form action="/system/searchmemberlist" method="GET" style="display: flex;" class="d-flex">
			 	 	<select name="searchOption">
				        <option value="option1">이름</option>
				        <option value="option2">부서</option>
				        <option value="option3">직급</option>
   					 </select>
				    <input type="text" class="form-control searchInput" name="searchWord">
				    <button class="btn btn-outline-success" type="submit">검색</button>
				</form>
			 	</div>
			    <a href="/system/join" data-bs-toggle="modal" data-bs-target="#joinForm" href="javascript:void(0);">
			 	<button type="button" class="btn btn-success first" style="margin-right: 20px;">사원등록</button>
			   </a>	
			 </div>
			 
			 
			 
			 
				<div class="son_list-box" style="margin-top: 5px;">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
									<th scope="col">사번</th> <!-- 체크박스 -->
									<th scope="col">사진</th>
									<th scope="col">아이디</th>
									<th scope="col">비밀번호</th>
									<th scope="col">이름</th>
									<th scope="col">부서</th>
									<th scope="col">주소</th>
									<th scope="col">직급</th>
									<th scope="col">주민번호</th>
									<th scope="col">입사일</th>
									<th scope="col">전화번호</th>
									<th scope="col">수정</th>
									<th scope="col">삭제</th>
								</tr>
						</thead>	
				<c:forEach var="member" items="${memberlist}">
                    <tbody>
	                    <td>${member.user_num}</td>
						<td><img src="${pageContext.request.contextPath}/resources/img/members/${member.user_img}" onerror="this.onerror=null; this.src='/resources/img/members/default.jpg';" class="profile-img" width="20" height="20"></td>
						<td>${member.user_id}</td>
						<td>${member.user_pw}</td>
						<td>${member.user_name}</td>
						<td>${member.user_department}</td>
						<td>${member.user_address}</td>
						<td>${member.user_position}</td>
						<td>${member.user_jumin}</td>
						<td>${member.user_joindate}</td>
						<td>${member.user_tel}</td>
						 
					<td>
						<a href="/system/adminupdate?user_id=${member.user_id}" data-bs-toggle="modal" data-bs-target="#updateForm" href="javascript:void(0);" onclick="openUpdateModal('${member.user_num}')">
							<button class="btn btn-success" type="button" ><i class="bi bi-pencil-square"></i></button>
						</a>
					</td>
						
					<td>	
					    <a href="/system/adminupdate?user_id=${member.user_id}" data-bs-toggle="modal" data-bs-target="#deleteForm" href="javascript:void(0);">
							<button class="btn btn-danger" type="button"><i class="bi bi-trash"></i></button>
						</a>	
					</td>
						
                    </tbody>
				</c:forEach>		
			</table>
		</div>
		
				
<nav aria-label="Page navigation example" style="display: flex; justify-content: center;" >
  <ul class="pagination">
   <c:if test="${pageVO.prev }">
    <li class="page-item">
      <a class="page-link" href="/system/memberlist?page=${pageVO.startPage - 1 }" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
   </c:if>
   
  		<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
		    <c:set var="isActive" value="${pageVO.cri.page == i}" />
		    <li class="page-item ${isActive ? 'active' : ''}">
		        <a class="page-link" href="/system/memberlist?page=${i}" style="${isActive ? 'background-color: #95c4a2; color: #ffffff; border-color: #81b189;' : 'background-color: #ffffff; color: #000000; border-color: #dddddd;'}">
		            ${i}
		        </a>
		    </li>
		</c:forEach>
 
  
    
	  <c:if test="${pageVO.next }">  
	    <li class="page-item">
	      <a class="page-link" href="/system/memberlist?page=${pageVO.endPage + 1 }" aria-label="Next" >
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	   </c:if>
    
  </ul>
</nav>	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
</div>


<!-- 사원삭제 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="deleteForm">
		 <div class="modal-dialog modal-sm">
		  <div class="modal-content">
		   <div class="modal-header">
		   	<h4 class="modal-title">사원삭제</h4>
		   	 <button type="button"class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/admindelete">
		    	<div class="modal-body">
		    		<div align="center">
		    			삭제후 복구가 불가능합니다<br>
		    			정말로 삭제하시겠습니까?<br>
		    		</div>
		    		<br>
		    		   <label for="userPwd" class="mr-sm-2">아이디 : </label>
		    				<input type="text" class="form-control mb-2 mr-sm-2"  name="user_id" placeholder="아이디를 입력하세요">
		    		   <label for="userPwd" class="mr-sm-2">Password : </label>
	                        <input type="password" class="form-control mb-2 mr-sm-2" placeholder="비밀번호를 입력하세요" id="userPwd" name="user_pw" > <br>
	                </div>
	                <!-- Modal footer -->
	                <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-danger" id="deleteButton">삭제하기</button>
		    	</div>
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 사원삭제 모달 -->




<!-- 사원등록 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="joinForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">사원등록</h4>
		   	 <button type="button"" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
			<jsp:include page="employeeJoin.jsp" />
		  </div>
		 </div>	
		</div>
<!-- 사원등록 모달 -->



<!-- 사원수정 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="updateForm">
		 <div class="modal-dialog"style="max-width: 1100px;">
		  <div class="modal-content"style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">사원수정</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		   	    <jsp:include page="employeeUpdate.jsp" />
		  </div>
		 </div>	
		</div>



<!-- sidehead.jsp 의 로그아웃/상세정보보기 토글이 안되는거 보완 -->













<script>
     $(document).ready(function(){
         $('.dropdown-toggle').dropdown();
     });
    
    
    
    $(document).ready(function(){
        $('.dropdown-toggle').dropdown();

        // "회원탈퇴" 버튼에 대한 클릭 이벤트 핸들러
        $("#deleteButton").click(function(){
            $("#deleteForm").modal("show");
        });

        // <a> 태그에 대한 클릭 이벤트 핸들러
        $("a[data-target='#deleteForm']").click(function(){
            $("#deleteForm").modal("show");
        });

        // "탈퇴하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#deleteForm .btn-danger").click(function(){
            $("#deleteForm form").submit();
        });

        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#deleteForm").on("hidden.bs.modal", function () {
            $("#deleteForm form")[0].reset();
        });
        
        $("#deleteForm .btn btn-secondary").click(function(){
            $("#deleteForm").modal("hide");
        });
        
        
        
        $("#deleteForm .close").click(function(){
            $("#deleteForm").modal("hide");
        });
           
    });
         
</script>

<script>
    $(document).ready(function(){
        // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#joinButton").click(function(){
            $("#joinForm").modal("show");
        });
        
        $("a[data-target='#joinForm']").click(function(){
            $("#joinForm").modal("show");
        });
        
        $("#joinForm .btn-info").click(function(){
            $("#joinForm form").submit();
        });
        
        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#joinForm").on("hidden.bs.modal", function () {
            $("#joinForm form")[0].reset();
        });
        
        $("#joinForm .btn btn-secondary").click(function(){
            $("#joinForm").modal("hide");
        });
        
        

        $("#joinForm .close").click(function(){
            $("#joinForm").modal("hide");
        });
           
    });
</script>

<script>



    $(document).ready(function(){
        // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#updateButton").click(function(){
            $("#updateForm").modal("show");
        });
        
        $("a[data-target='#updateForm']").click(function(){
            $("#updateForm").modal("show");
        });
        
        $("#updateForm .btn-warning").click(function(){
            $("#updateForm form").submit();
        });
        
        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#updateForm").on("hidden.bs.modal", function () {
            $("#updateForm form")[0].reset();
        });
        
		
        $("#updateForm .btn btn-secondary").click(function(){
            $("#updateForm").modal("hide");
        });
        
        
        $("#updateForm .close").click(function(){
            $("#updateForm").modal("hide");
        });
           
    });
</script>




<script>
  var passHide = document.querySelectorAll(".pass-hide");

  passHide.forEach((icon) => {
    icon.addEventListener("click", () => {
      let passInput = icon.parentElement.querySelector("input");
      passInput.style.border = "none";
      passInput.style.outline = "none";
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














<script src="${pageContext.request.contextPath}/resources/img/address2.js"></script>

<%-- <script src="${pageContext.request.contextPath}/resources/img/address.js"></script> --%>
<%-- <script src="${pageContext.request.contextPath}/resources/img/address2.js"></script> --%>




	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	

</body>
</html>