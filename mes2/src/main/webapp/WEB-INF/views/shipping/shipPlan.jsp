<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <title>출하 계획</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
   <!--  <script src="https://kit.fontawesome.com/38bf29a217.js" crossorigin="anonymous"></script> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sales/salesPlan.css">

<!-- 글씨체 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 글씨체 -->

 
    


  </head>
  
  <body>
   <%@ include file="../system/sidehead.jsp" %>
 
  
  
<!-- Modal -->
<div id="modalcon">
<div class="modal fade" id="shippngPlanModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body mo" id="shippngPlan-modal">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="mo-close" >닫기</button>
       
      </div>
    </div>
  </div>
</div>
</div>


	<!-- 페이징 정보 저장 -->
		<form id="pageForm" action="shipPlan" method="post">
			<input type="hidden" id="page" name="page" value="${pm.cri.page }"/>
		    <input type="hidden" id="prePageNum" name="perPageNum" value="${pm.cri.perPageNum }"/>
		    <input type="hidden" id="type" name="type" value="${pm.cri.type }"/>
		    <input type="hidden" id="search" name="search" value="${pm.cri.search }"/>
		    <input type="hidden" id="userId" name="userId" value="${pm.cri.userId }"/>
		    <input type="hidden" id="shipStatus" name="shipStatus" value="${pm.cri.shipStatus }"/>
		    <input type="hidden" name="shipSta" value="${pm.cri.shipSta }"/>
		</form>
    
    <!-- 검색창 -->
    <div class="container">
    <section class="section1">
    <h2>출하계획</h2>
      <form action="shipPlan" method="post" class="search" id="sfrm" onsubmit="return checkSearchSub()">
      	   <input type="hidden"  id="frmId" name="userId" value="${pm.cri.userId }"/>
      	   <input type="hidden"  name="shipStatus" value="${pm.cri.shipStatus }"/>      	  
      	   <input type="hidden" id="shipSta" name="shipSta" value=""/>
      	 
    <!--검색타입 -->
      	 <select name="type" id="searchType" class="form-select" aria-label="Default select example">
          <option value="">검색선택</option>
          <option value="order_code" ${pm.cri.type=='order_code' ? 'selected' : ''}>주문번호</option>
          <option value="company_name" ${pm.cri.type=='company_name' ? 'selected' : ''}>수주처</option>
          <option value="order_date" ${pm.cri.type=='order_date' ? 'selected' : ''}>납품요청일</option>
          <option value="scheduled_date" ${pm.cri.type=='scheduled_date' ? 'selected' : ''}>출하예정일</option>
        </select>
        
   
		
        <!-- 기간검색 -->
		<div class="input-group">
		   <span class="input-group-text">기간</span>
		   <input type="date" aria-label="First name" class="form-control" value="${pm.cri.startDay }" name="startDay" />
		   <input type="date" aria-label="Last name" class="form-control" name="endDay" value="${pm.cri.endDay }"/>
	    </div>
	    
        <!-- 검색타입 -->
        <div class="input-group searchSub">
	        <input type="text" name="search" id="putSearch" class="form-control" placeholder="검색어를 입력하세요" value="${pm.cri.search }" 
	        aria-label="Recipient's username" aria-describedby="button-addon2">
	        <button class="btn btn-secondary" type="submit" onclick="return checkSearchSub()" id="button-addon2">검색</button>
        </div>
      </form>

      <!-- 표 -->
      <div class="list">
        <div class="list-btn">
         <c:if test="${not empty pm.cri.userId}">
       <button type="button" class="btn fg-btn" onclick="showStatus()">처리</button>     	 
      	 </c:if>
         <button type='button' class='btn dark-green-btn' data-bs-toggle='modal' data-bs-target='#shippngPlanModal'  onclick="return update()">수정</button>  
         <!-- 현황버튼 -->
			<div class="btn-group">
			  <button type="button" class="btn mint-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">현황</button>
			    <ul class="dropdown-menu">
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipPlan'">출하</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipPlan?shipStatus=plan'">계획 ${status.planCnt }건</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipPlan?shipStatus=waiting'">대기 ${status.waitingCnt }건</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipPlan?shipStatus=instruction'">가능  ${status.instructionCnt }건</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipPlan?user=true'">등록확인</li>
			   </ul>
			</div>      
        </div>

		
        <div class="list-box">
          <form class="list-form" id="planListForm"  method="post">         
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  
                  <th colspan="2" style="text-align: center;">주문번호</th>
                  <th>수주처</th>
                  <th>납품요청일</th>
                  <th>출하예정일</th>
                  <th>출하상태</th>  
                  <th>출하지시</th>             
                </tr>
              </thead>
              <tbody>
              <c:forEach var="dto" items="${list }">
                <tr>
                  <td><input type="radio" class="ck" value="${dto.order_code }" name="order_code"/></td>  
                  <td onclick="goContent('${dto.order_code }')"><span class="od-content">${dto.order_code }</span></td>
                  <td>${dto.company_name }</td>         
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.order_date}"/></td>            
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.scheduled_date }"/></td>                                              
                  <c:if test="${dto.ship_status eq 'plan'}">
                  <td><div class="gray-circle"/></div>  계획</td>
                  <td><div class="gray-circle"/></div>  불가능</td>
                  </c:if>
                  <c:if test="${dto.ship_status eq 'waiting'}">
                  <td><div class="yellow-circle"/></div>  대기</td>
                  <td><div class="gray-circle"/></div>  불가능</td>
                  </c:if> 
                  <c:if test="${dto.ship_status eq 'instruction'}">
                  <td><div class="green-circle"/></div>  준비완료</td>
                  <td><button type='button' class='btn mint-btn'  onclick="return reg('${dto.order_code }')">가능</button></td>
                  </c:if>               
                </tr>
             </c:forEach> 
                
              </tbody>
            </table>
          </form>
        </div>
      </div>
      <!-- 페이징  -->
		  <div class="page-nav">
		  <nav aria-label="Page navigation example">
		    <ul class="pagination">
		    
		    <!-- 이전페이지 -->
		    <c:if test="${pm.prev }">
		        <li class="page-item page-action">
		            <a class="page-link" href="${pm.startPage-1 }" aria-label="Previous">
		                <span aria-hidden="true">&laquo;</span>
		            </a>
		        </li>
		    </c:if>
		    
		    
		<!-- 페이지 번호 -->
		
        <c:forEach var="pageNum" begin="${pm.startPage}" end="${pm.endPage}">
            <c:if test="${pm.cri.page != pageNum}">
                <li class="page-item page-action"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
            </c:if>
            <c:if test="${pm.cri.page == pageNum}">
                <li class="active page-item page-action"><a class="page-link" href="${pageNum}">${pageNum}</a></li>
            </c:if>
        </c:forEach>

		<!-- 다음페이지 -->
		<c:if test="${pm.next }">
		        <li class="page-item">
		            <a class="page-link" href="${pm.endPage+1}" aria-label="Next">
		                <span aria-hidden="true">&raquo;</span>
		            </a>
		        </li>
		        </c:if>
		    </ul>
		</nav>
		</div>
    </section>

    <section class="section1" id="view2">
   
      </section>
      </div>
      
   <!--     <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>  -->
    
<script type="text/javascript">
    //페이지번호클릭시이동하기 
    $(document).ready(function() {
        var pageFrm = $("#pageForm");

        $(".page-item a").on("click", function(e) {
            
            e.preventDefault(); //a태그기능막기
            var page = $(this).attr("href"); //페이지번호
            pageFrm.find("#page").val(page);
            pageFrm.submit();
        });
    });
</script>

<script src="${pageContext.request.contextPath}/resources/js/shipping/shipPlan/btn.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/shipping/shipPlan/details.js"></script>
  </body>
</html>