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
<title>공지사항</title>

<style text="text/css">


  .list {
            margin-top : 20px;
            margin-left: 100px;
            margin-right: 20px;
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
    margin-right: 1300px;
}




 legend {
    font-weight: bold;
  }


</style>

	<script>
	    function updateTitle() {
	         var postTypeSelect = document.getElementById("postType");
	         var titleInput = document.getElementById("join-id");
	
	         var selectedOptionText = "[" + postTypeSelect.options[postTypeSelect.selectedIndex].text + "]";
	
	         // 글제목 입력란에 설정
	         titleInput.value = selectedOptionText;
	     }
	</script>


</head>
	<body>
	 	<%@ include file="sidehead.jsp" %>	
	 	
	 	
	 		<!-- 등록하기 버튼(상단의) -->
<div class="list">
	 		
	 		<c:if test="${!empty id && id.equals('admin')}">
			  <div class="d-flex justify-content-end">
			  	<a href="#" data-bs-toggle="modal" data-bs-target="#updateBoardForm" href="javascript:void(0);">
			 		<button type="button" class="btn btn-success first">수정</button>
			  	</a>&nbsp;
			  		
			   <a href="#" data-bs-toggle="modal" data-bs-target="#deleteBoardForm" href="javascript:void(0);">
			 	<button type="button" class="btn btn-success first">삭제</button>
			   </a>	
			   
			 </div>
	 		</c:if>
	 	
	 	
	 	
		 	<div class="son_list-box">	
					<form role="form" method="post"> 
						<input type="hidden" name="bno" value="${resultDTO.bno}">
					</form>
				
			
		<div class="container">
		   <div class="row justify-content-center">
			   <div class="col-md-8">	
				
				<div class="card">
 					 <div class="card-body">
	        			
					<div class="box-body">
					
					<div class="details">
        					<h3 style="font-weight: bold;">공지사항</h3>
      				</div><br>
					
						<div class="form-group w-25">
							<label for="exampleInputEmail1"><h5>작성자</h5></label>
							 <input type="text" class="form-control" id="exampleInputEmail1" name="writer" value ="${resultDTO.writer}" readonly="readonly">
						</div><br>
						<div class="form-group w-25">
							<label for="exampleInputEmail1"><h5>제 목</h5></label>
							 <input type="text" class="form-control" id="exampleInputEmail1" name="title" value="${resultDTO.title}" readonly >
						</div><br>
						<div class="form-group w-50">
							<label for="exampleInputEmail1"><h5>내 용</h5></label>
							<textarea class="form-control" rows="3" name="content" readonly="readonly">${resultDTO.content}</textarea>
						</div><br>	
					</div>
			  </div>
		  </div>		
		
		
			
		  </div>				
		</div>			
	</div>				
				
				
				
				
				
					
						
			</div>
		
		
		
		
</div>			
	 	
	

	
<!-- 게시글수정 모달 -->
		<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="updateBoardForm">
		 <div class="modal-dialog" style="max-width: 1100px;">
		  <div class="modal-content" style="max-width: 1100px;">
		   <div class="modal-header">
		   	<h4 class="modal-title">공지글수정</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/modify">
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
									    <label for="exampleInputPassword1" class="form-label">글번호</label><br>
									    <input type="text" placeholder="수정할글번호입력" name="bno" id="title_update">
									</div><br>              
				    		      <label for="exampleInputEmail1" class="form-label">제목</label><br>
								      <div class="input-box">
									      <input type="text" placeholder="수정할제목을입력하세요" name="title" id="join-id">
						   			 </div><br>
								   <label for="postType" class="form-label">게시글 종류</label>
									<div class="input-box">
									    <select name="postType" id="postType" onchange="updateTitle()">
									        <option value="normal">일반</option>
									        <option value="notice">공지</option>
									        <option value="alert">알림</option>
									        <!-- 다른 종류의 게시글이 필요하다면 추가 가능 -->
									    </select>
							</div>	 
				    		   	  <label for="exampleInputEmail1" class="form-label">내용</label><br>
								   <div class="input-box">
										<textarea class="form-control" rows="3" name="content" placeholder="수정할내용을입력하세요"></textarea>						           
				           		  </div><br>
								   </div>
							   </div>
							</div>
						</div>			 
					</div>	
	                <!-- Modal footer -->
	                 <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-warning" id="updateBoardButton">수정하기</button>
		    		</div>
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 코드수정 모달 -->		
	
	

<!-- 코드삭제 모달 -->
	   <div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"  id="deleteBoardForm">
		 <div class="modal-dialog modal-sm">
		  <div class="modal-content">
		   <div class="modal-header">
		   	<h4 class="modal-title">공지글삭제</h4>
		   	 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		   </div>
		    <form method="post" action="/system/remove">
		    	  <div class="modal-body">
		    		<div align="center">
		    			삭제후 복구가 불가능합니다<br>
		    			정말로 삭제하시겠습니까?<br>
		    		</div>
		    		<br>
		    		    <label for="userPwd" class="mr-sm-2">글번호 :</label>
		    				<input type="text" name="bno" placeholder="글번호를입력하세요">	
		    		  
	                </div>
	                <!-- Modal footer -->
	                <div class="modal-footer" align="center">
	                    <button type="button" class="btn btn-danger" id="deleteBoardButton">삭제하기</button>
		    	</div>
		    </form>	
		  </div>
		 </div>	
		</div>
<!-- 코드삭제 모달 -->
	
	
<!--글수정 모달자바스크립트 -->
	
<script>
$(document).ready(function(){
    // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
    $("#updateBoardButton").click(function(){
        $("#updateBoardForm").modal("show");
    });
    
    $("a[data-target='#updateBoardForm']").click(function(){
        $("#updateBoardForm").modal("show");
    });
    
    $("#updateBoardForm .btn-warning").click(function(){
        $("#updateBoardForm form").submit();
    });
    
    // 모달이 닫힐 때 폼의 입력 필드 초기화
    $("#updateBoardForm").on("hidden.bs.modal", function () {
        $("#updateBoardForm form")[0].reset();
    });
    
    $("#updateBoardForm .btn btn-secondary").click(function(){
        $("#updateBoardForm").modal("hide");
    });
    

    $("#updateBoardForm .close").click(function(){
        $("#updateBoardForm").modal("hide");
    });
       
});

</script>	
<!--공통코드수정 모달자바스크립트 -->


<!--공통코드삭제 모달자바스트립트 -->
	
<script>
$(document).ready(function(){
    // "등록하기" 버튼에 대한 클릭 이벤트 핸들러
    $("#deleteBoardButton").click(function(){
        $("#deleteBoardForm").modal("show");
    });
    
    $("a[data-target='#deleteBoardForm']").click(function(){
        $("#deleteBoardForm").modal("show");
    });
    
    $("#deleteBoardForm .btn-danger").click(function(){
        $("#deleteBoardForm form").submit();
    });
    
    // 모달이 닫힐 때 폼의 입력 필드 초기화
    $("#deleteBoardForm").on("hidden.bs.modal", function () {
        $("#deleteBoardForm form")[0].reset();
    });
    
    $("#deleteBoardForm .btn btn-secondary").click(function(){
        $("#deleteBoardForm").modal("hide");
    });

    $("#deleteBoardForm .close").click(function(){
        $("#deleteBoardForm").modal("hide");
    });
       
});

</script>	
<!--공통코드삭제 모달자바스크립트 -->

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>	
	</body>
</html>