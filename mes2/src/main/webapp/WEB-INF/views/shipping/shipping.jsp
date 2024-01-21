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
    <title>출하 목록</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- <script src="https://kit.fontawesome.com/38bf29a217.js" crossorigin="anonymous"></script> -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
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
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel"></h1>
        
      </div>
      <div class="modal-body mo" id="shippng-modal">
       
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="mo-close" >닫기</button>
       
      </div>
    </div>
  </div>
</div>
</div>


		<!-- 페이징 정보 저장 -->
		<form id="pageForm" action="shipping" method="post">
			<input type="hidden" id="page" name="page" value="${pm.cri.page }"/>
		    <input type="hidden" id="prePageNum" name="perPageNum" value="${pm.cri.perPageNum }"/>
		    <input type="hidden" id="type" name="type" value="${pm.cri.type }"/>
		    <input type="hidden" id="search" name="search" value="${pm.cri.search }"/>
		    <input type="hidden" id="userId" name="userId" value="${pm.cri.userId }"/>
		    <input type="hidden" id="fProStatus" name="progressStatus" value="${pm.cri.progressStatus }"/>
		    <input type="hidden" id="fProSta" name="progressSta" value="${pm.cri.progressSta }"/>
		</form>
    
    <!-- 검색창 -->
    <div class="container" >
    <section class="section1">
    <h2>출하목록</h2>
         <form action="shipping" method="post" id="sfrm" class="search" onsubmit="return checkSearchSub()">     	
      	  <input type="hidden"  id="frmId" name="userId" value="${pm.cri.userId }"/>
      	  <input type="hidden" id="progressStatus" name="progressStatus" value="${pm.cri.progressStatus }"/>
      	  <input type="hidden" id="progressSta" name="progressSta" value="${pm.cri.progressSta }"/>
      	 
    <!--검색타입 -->   	  
      	 <select name="type" id="searchType" class="form-select" aria-label="Default select example">
          <option value="">검색선택</option>
          <option value="ship_code" ${pm.cri.type=='ship_code' ? 'selected' : ''}>출하코드</option>
          <option value="ship_date" ${pm.cri.type=='ship_date' ? 'selected' : ''}>출하일자</option>
          <option value="company_name" ${pm.cri.type=='company_name' ? 'selected' : ''}>수주처</option>
          <option value="order_date" ${pm.cri.type=='order_date' ? 'selected' : ''}>납품요청일</option>
          
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
      	 
         <button type='button' class='btn dark-green-btn' data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="return update()">수정</button>   
      	 
      	 
      	 
               <!-- 현황 -->
			<div class="btn-group">
			  <button type="button" class="btn mint-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">현황</button>
			    <ul class="dropdown-menu">
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipping'">출하</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipping?progressStatus=waiting'">대기 ${status.waitingCnt }건</span></li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipping?progressStatus=shipping'">배송중  ${status.instructionCnt }건</li>
			      <li class="dropdown-item stat-item" onclick="location.href='/shipping/shipping?progressStatus=complete'">완료  ${status.completeCnt }건</li>
			      <li class="dropdown-item stat-item"onclick="location.href='/shipping/shipping?user=true'">등록확인</li>
			   </ul>
			</div>        
        </div>

        <div class="list-box">
          <form class="list-form"  >         
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  
                  <th colspan="2" style="text-align: center;">출하코드</th>
                  <th>출하일자</th>
                  <th>수주처</th>
                  <th>납품요청일</th>
                  <th>출하상태</th>               
                </tr>
              </thead>
              <tbody>
              <c:forEach var="data" items="${list }"> 
                <tr>
                  <td><input type="radio" class="ck" value="${data.order_code }" name="order_code"/></td>  
                  <td onclick="goContent('${data.order_code }')"><span class="od-content">${data.ship_code }</span></td>
                  <td class="ship-date"><fmt:formatDate pattern="yyyy-MM-dd" value="${data.ship_date }"/></td>  
                  <td>${data.company_name }</td>       
                  <td><fmt:formatDate pattern="yyyy-MM-dd" value="${data.order_date}"/></td>            
                  <c:if test="${data.progress_status eq 'complete'}">
                  <td class="pf"><div class="green-circle"/></div>  완료</td>
                  </c:if>
                 	 <c:if test="${data.progress_status eq 'waiting'}">
   				 	<td class="pf"><button type="button" class="btn btn-secondary waite-btn btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal" onclick="return complete('${data.order_code}','${data.ship_date}')">대기</button></td>
					</c:if> 
					 <c:if test="${data.progress_status eq 'shipping'}">
   				 	<td class="pf"><div class="yellow-circle"/></div>  배송</td>
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
      
<!--       <script
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

  <script src="${pageContext.request.contextPath}/resources/js/shipping/ship/btn.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/shipping/ship/details.js"></script>
  </body>
</html>