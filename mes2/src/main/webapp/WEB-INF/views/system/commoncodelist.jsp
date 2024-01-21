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
<title>공통코드목록</title>

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
    margin-right: 1230px;
}

 .page-item {
       margin-right: 5px; /* 원하는 간격 크기로 조정 */
 }
 
 


</style>




<script>
        function openCodeUpdateModal(userId) {
            console.log('모달을 열 때 사용할 userId:', userId);

            var userNumInput = document.getElementById('commoncode_index_input');
            if (userNumInput) {
                userNumInput.value = userId;
            }

        }
</script>



</head>
	<body>
	 	<%@ include file="sidehead.jsp" %>	
	 	
	 	
	 		<!-- 등록하기 버튼(상단의) -->
	 	<h2 style ="margin-left: 125px;">공통코드리스트</h2>
	 		<div class="list">
			  <div class="d-flex justify-content-end">
			  	<div class="searchInputdiv">
			 	<form action="/system/searchcodelist" method="GET" style="display: flex;" class="d-flex">
			 	 	<select name="searchOption">
				        <option value="option1">코드그룹</option>
				        <option value="option2">상세코드</option>
   					 </select>
				    <input type="text" class="form-control searchInput" name="searchWord">
				    <button class="btn btn-outline-success" type="submit">검색</button>
				</form>
			 	</div>
			   <a href="/system/commoncodejoin" data-bs-toggle="modal" data-bs-target="#CodeJoinForm" href="javascript:void(0);">
			 	<button type="button" class="btn btn-success first" style="margin-right: 20px;">코드등록</button>
			   </a>	
			 </div>
	 	
	 	
	 	
	 	
	 	<div class="son_list-box">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
									<th scope="col">코드인덱스</th>
									<th scope="col">코드그룹</th> <!-- 체크박스 -->
									<th scope="col">코드그룹명</th>
									<th scope="col">세부코드</th>
									<th scope="col">세부코드명</th>
									<th scope="col">수정</th>
									<th scope="col">삭제</th>
								</tr>
						</thead>	
				<c:forEach var="commoncode" items="${commoncodelist}">
                    <tbody>
                        <td>${commoncode.code_index}</td>   
		                <td>${commoncode.code_group}</td>
						<td>${commoncode.code_group_name}</td>
						<td>${commoncode.code_code}</td>
						<td>${commoncode.code_name}</td>
	 					<td>
							<a href="/system/commoncodeupdate?code_index=${commoncode.code_index}" data-bs-toggle="modal" data-bs-target="#CodeUpdateForm" href="javascript:void(0);" onclick="openCodeUpdateModal('${commoncode.code_index}')">
								<button class="btn btn-success" type="button"><i class="bi bi-pencil-square"></i></button>							
							</a>
						</td>
						
						<td>
							<a href="/system/commoncodedelete?code_index=${commoncode.code_index}" data-bs-toggle="modal" data-bs-target="#CodeDeleteForm" href="javascript:void(0);">
							<button class="btn btn-danger" type="button"><i class="bi bi-trash"></i></button>
							</a>					
						</td>
						
	 	            </tbody>
				</c:forEach>		
			</table>
		</div>
		
		<nav aria-label="Page navigation example" style="display: flex; justify-content: center;">
		  <ul class="pagination">
		   <c:if test="${pageVO.prev }">
		    <li class="page-item">
		      <a class="page-link" href="/system/commoncodelist?page=${pageVO.startPage - 1 }" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		   </c:if>
		   
		<c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
		    <c:set var="isActive" value="${pageVO.cri.page == i}" />
		    <li class="page-item ${isActive ? 'active' : ''}">
		        <a class="page-link" href="/system/commoncodelist?page=${i}" style="${isActive ? 'background-color: #95c4a2; color: #ffffff; border-color: #81b189;' : 'background-color: #ffffff; color: #000000; border-color: #dddddd;'}">
		            ${i}
		        </a>
		    </li>
		</c:forEach>
		 
		  
		    
		  <c:if test="${pageVO.next }">  
		    <li class="page-item">
		      <a class="page-link" href="/system/commoncodelist?page=${pageVO.endPage + 1 }" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		   </c:if>
		    
		  </ul>
		</nav>	
		
		
</div>			
	 	
	
	
	
<!-- 코드등록 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="CodeJoinForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">코드등록</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/commoncodejoin">
		    	<div class="modal-body" style="max-width: 1100px;">
		    		<br>
		    		  <div class="card mb-3" style="max-width: 1100px;"> 
		    		  	 <div class="row g-0">
		    		   				<div class="col-md-4">
            						    <img src="${pageContext.request.contextPath}/resources/img/join/joining.jpg" class="img-fluid rounded-start" alt="...">
          							</div>
				    		          <div class="col-md-8">
				    		               <div class="card-body">
				    		     <label for="exampleInputEmail1" class="form-label">코드그룹</label><br>
								      <div class="input-box">
									      <input type="text" placeholder="코드그룹을 입력하세요" name="code_group" id="join-id">
						   			 </div><br>
				    		   	 <label for="exampleInputEmail1" class="form-label">코드그룹명</label><br>
								   <div class="input-box">
						              <input type="text" name="code_group_name" placeholder="코드그룹명을 입력하세요" />
						           </div><br>
							      <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">세부코드</label><br>
								    <input type="text"  placeholder="세부코드를 입력하세요" name="code_code" id="exampleInputPassword1">
								  </div>  
								  <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">세부코드명</label><br>
								    <input type="text"  placeholder="세부코드명을 입력하세요" name="code_name" id="exampleInputPassword1">
								  </div>
								  <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">코드사용여부</label><br>
								    <select name="code_usestatus">
								    	<option value="1">YES</option>
								    	<option value="0">NO</option>
								    </select>
								  </div>  

								   </div>
							   </div>
							</div>
						</div>			 
					</div>	
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-info" id="CodeJoinButton">등록하기</button>
		    		</div>
		    </form>	
		  </div>
		 </div>	
		</div>

<!-- 코드등록 모달 -->	
	
<!-- 코드수정 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="CodeUpdateForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">코드수정</h4>
		   	  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/commoncodeupdate">
		    	<div class="modal-body" style="max-width: 1100px;">
		    		<br>
		    		  <div class="card mb-3" style="max-width: 1100px;"> 
		    		  	 <div class="row g-0">
		    		   				<div class="col-md-4">
            						    <img src="${pageContext.request.contextPath}/resources/img/join/joining.jpg" class="img-fluid rounded-start" alt="...">
          							</div>
				    		          <div class="col-md-8">
				    		               <div class="card-body">
				    		        <div class="mb-3">
									    <label for="exampleInputPassword1" class="form-label">코드인덱스</label><br>
									    <input type="text" placeholder="코드인덱스입력" name="code_index" id="commoncode_index_input" readonly>
									</div><br>
									
			
										<div>
						    				<h3>변경사항</h3>
						    			</div><hr>  
						    			            
				    		      <label for="exampleInputEmail1" class="form-label">코드그룹</label><br>
								      <div class="input-box">
									      <input type="text" placeholder="코드그룹을 입력하세요" name="code_group" id="join-id">
						   			 </div><br>
				    		   	  <label for="exampleInputEmail1" class="form-label">코드그룹명</label><br>
								   <div class="input-box">
						              <input type="text" name="code_group_name" placeholder="코드그룹명을 입력하세요" />
						           </div><br>
							      <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">세부코드</label><br>
								    <input type="text"  placeholder="세부코드를 입력하세요" name="code_code" id="exampleInputPassword1">
								  </div>  
								  <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">세부코드명</label><br>
								    <input type="text"  placeholder="세부코드명을 입력하세요" name="code_name" id="exampleInputPassword1">
								  </div>
								  <div class="mb-3">
								    <label for="exampleInputPassword1" class="form-label">코드사용여부</label><br>
								    <select name="code_usestatus">
								    	<option value="1">YES</option>
								    	<option value="0">NO</option>
								    </select>
 								  </div>  
  
								   </div>
							   </div>
							</div>
						</div>			 
					</div>	
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-warning" id="CodeUpdateButton">수정하기</button>
		    		</div>
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 코드수정 모달 -->		
	
	

<!-- 코드삭제 모달 -->
	   <div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" id="CodeDeleteForm">
		 <div class="modal-dialog modal-sm">
		  <div class="modal-content">
		   <div class="modal-header">
		   	<h4 class="modal-title">공통코드삭제</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/commoncodedelete">
		    	  <div class="modal-body">
		    		<div align="center">
		    			삭제후 복구가 불가능합니다<br>
		    			정말로 삭제하시겠습니까?<br>
		    		</div>
		    		<br>
		    		    <label for="userPwd" class="mr-sm-2">코드인덱스 :</label>
		    				<input type="text" name="code_index" placeholder="코드인덱스를입력하세요">	
		    		   <label for="userPwd" class="mr-sm-2">코드그룹 : </label>
		    				<input type="text"  name="code_group" placeholder="코드그룹명을입력하세요">
		    		   <label for="userPwd" class="mr-sm-2">세부코드 : </label>
	                        <input type="text" placeholder="세부코드를입력하세요" name="code_code" > <br>
	                </div>
	                <!-- Modal footer -->
	                <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-danger" id="CodeDeleteButton">삭제하기</button>
		    	</div>
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 코드삭제 모달 -->		









	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

<!--공통코드등록 모달자바스크립트 -->
	
<script>
$(document).ready(function(){
    // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
    $("#CodeJoinButton").click(function(){
        $("#CodeJoinForm").modal("show");
    });
    
    $("a[data-target='#CodeJoinForm']").click(function(){
        $("#CodeJoinForm").modal("show");
    });
    
    $("#CodeJoinForm .btn-info").click(function(){
        $("#CodeJoinForm form").submit();
    });
    
    // 모달이 닫힐 때 폼의 입력 필드 초기화
    $("#CodeJoinForm").on("hidden.bs.modal", function () {
        $("#CodeJoinForm form")[0].reset();
    });
    

    $("#CodeJoinForm .btn btn-secondary").click(function(){
        $("#CodeJoinForm").modal("hide");
    });
    
    

    $("#CodeJoinForm .close").click(function(){
        $("#CodeJoinForm").modal("hide");
    });
       
});

</script>	
<!--공통코드등록 모달자바스크립트 -->
	
	
<!--공통코드수정 모달자바스크립트 -->
	
<script>
$(document).ready(function(){
    // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
    $("#CodeUpdateButton").click(function(){
        $("#CodeUpdateForm").modal("show");
    });
    
    $("a[data-target='#CodeUpdateForm']").click(function(){
        $("#CodeUpdateForm").modal("show");
    });
    
    $("#CodeUpdateForm .btn-warning").click(function(){
        $("#CodeUpdateForm form").submit();
    });
    
    // 모달이 닫힐 때 폼의 입력 필드 초기화
    $("#CodeUpdateForm").on("hidden.bs.modal", function () {
        $("#CodeUpdateForm form")[0].reset();
    });
    
    $("#CodeUpdateForm .btn btn-secondary").click(function(){
        $("#CodeUpdateForm").modal("hide");
    });
    
    
    
    $("#CodeUpdateForm .close").click(function(){
        $("#CodeUpdateForm").modal("hide");
    });
       
});

</script>	
<!--공통코드수정 모달자바스크립트 -->


<!--공통코드삭제 모달자바스트립트 -->
	
<script>
$(document).ready(function(){
    // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
    $("#CodeDeleteButton").click(function(){
        $("#CodeDeleteForm").modal("show");
    });
    
    $("a[data-target='#CodeDeleteForm']").click(function(){
        $("#CodeDeleteForm").modal("show");
    });
    
    $("#CodeDeleteForm .btn-danger").click(function(){
        $("#CodeDeleteForm form").submit();
    });
    
    // 모달이 닫힐 때 폼의 입력 필드 초기화
    $("#CodeDeleteForm").on("hidden.bs.modal", function () {
        $("#CodeDeleteForm form")[0].reset();
    });
    
	
    $("#CodeDeleteForm .btn btn-secondary").click(function(){
        $("#CodeDeleteForm").modal("hide");
    });
    
    
    $("#CodeDeleteForm .close").click(function(){
        $("#CodeDeleteForm").modal("hide");
    });
       
});

</script>	
<!--공통코드삭제 모달자바스크립트 -->














	
	
	
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
	</body>
</html>