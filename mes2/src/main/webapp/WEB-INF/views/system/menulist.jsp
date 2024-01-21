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
<title>메뉴관리</title>

<style text="text/css">

 .son_list-box {
            margin-top: 20px;
            margin-left: 100px;
            margin-right: 10px;
        }

 .table th, .table td {
          text-align: center;
          vertical-align: middle; 
          border : none;
       }


	.table tr td {
	    border-bottom: 1px solid #dee2e6;  /* td 아랫쪽에만 border 추가 */
	}


.list{
	margin-top : 50px;
	margin-right : 30px;
	margin-left: 20px;
}

</style>













</head>
	<body>
	 	<%@ include file="sidehead.jsp" %>	
	 
	<h2 style ="margin-left: 125px; margin-top: 5px;">메뉴상태리스트</h2>
			<!-- 수정하기 버튼(상단의) -->
	 		<div class="list">
			  <div class="d-flex justify-content-end">
			   <a href="/system/menuupdate" data-bs-toggle="modal" data-bs-target="#MenuUpdateForm" href="javascript:void(0);">
			 	<button type="button" class="btn btn-success first">메뉴상태수정</button>
			   </a>	
			 </div>
	 			
		
				
				
				
	 	<div class="son_list-box">	
						<table class="table table-hover" >
							<thead>
								<tr class="table-success" >
									<th scope="col">메뉴리스트</th>
									<th scope="col">사용상태</th> <!-- 체크박스 -->
								</tr>
						</thead>	
				<c:forEach var="menu" items="${menulist}">
                    <tbody>
                        <td>${menu.user_auth}</td>   
		                <td>${menu.menu_status}</td>
	 	            </tbody>
				</c:forEach>		
			</table>
		</div>
	</div>			
				
<!-- 메뉴상태수정 모달 -->	
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" id="MenuUpdateForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">메뉴상태수정</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
		   	 	닫기
		   	 </button>
		   </div>
		    <form method="post" action="/system/menuupdate">
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
						    <label for="user_auth" class="form-label">메뉴목록</label>
						    <select  name="user_auth">
									<option value="시스템관리">시스템관리</option>
									<option value="기준정보관리">기준정보관리</option>
									<option value="생산관리">생산관리</option>
									<option value="영업관리">영업관리</option>
									<option value="자재관리">자재관리</option>
									<option value="플랫폼관리">플랫폼관리</option>
						    </select>
						 </div>		           
				     	 
							  	  <div class="mb-3">
									 <label for="menu_status" class="form-label">권한메뉴상태</label>
										<select name="menu_status">
											<option value="Y">Y</option>  
									  		<option value="N">N</option> 
									   </select>
								  </div>
							  			</div>
								   </div>
							   </div>
							</div>
						</div>			 
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-warning" id="MenuUpdateButton">수정하기</button>
		    		</div>
				</div>	
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 메뉴상태수정 모달 -->	
	


<!-- 메뉴상태수정 모달 자바스크립트 -->	

<script>
    $(document).ready(function(){
        // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
        $("#MenuUpdateButton").click(function(){
            $("#MenuUpdateForm").modal("show");
        });
        
        $("a[data-target='#MenuUpdateForm']").click(function(){
            $("#MenuUpdateForm").modal("show");
        });
        
        $("#MenuUpdateForm .btn-warning").click(function(){
            $("#MenuUpdateForm form").submit();
        });
        
        // 모달이 닫힐 때 폼의 입력 필드 초기화
        $("#MenuUpdateForm").on("hidden.bs.modal", function () {
            $("#MenuUpdateForm form")[0].reset();
        });
        

        $("#MenuUpdateForm .close").click(function(){
            $("#MenuUpdateForm").modal("hide");
        });
           
    });
</script>


<!-- 메뉴상태수정 모달 자바스크립트 -->	


	
	
	
	
	
	
	
	
	
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
	</body>
</html>