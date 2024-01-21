<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!--  페이지도 인코딩 해줘야함 -->
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="javax.servlet.http.HttpSession" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<!-- Designined by CodingLab | www.youtube.com/codinglabyt -->
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <title>메인페이지</title>
    <link rel="stylesheet" href="../../../resources/css/sidehead/sideheadstyle.css">
    <!-- Boxiocns CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>

    <!-- 부트스트랩 cdn 추가-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    
    <!-- Font Awesome 라이브러리 추가 -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     
     <!-- Sweet Alert 라이브러리 추가 -->
     <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
     
    <!-- Jquery 라이브러리 추가 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<link href="${pageContext.request.contextPath}/resources/fullcalander/main.css" rel='stylesheet' />
    <script src="${pageContext.request.contextPath}/resources/fullcalander/main.js"></script>
     
     <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!-- 글씨체 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700&family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<!-- 글씨체 -->
 <style type="text/css">
 * {
	   font-family: 'Noto Sans KR', sans-serif;
	   font-size: 18px;
	}
 
 @font-face {
    font-family: 'MaplestoryOTFBold';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
 

.text-header{
  font-family: 'MaplestoryOTFBold';
  text-decoration: none;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-size: 30px;
  color : white;
}
.logo_name{
  font-family: 'MaplestoryOTFBold';
  text-decoration: none;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-size: 20px;
  color : white;
}




 
.user-greeting {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-right: 10px;
}

.id-greeting {
     margin: 0 0 0 -10px;
}

.dropdown {
    margin-left: auto; /* Push the dropdown to the right */
}

.user-img{
	width : 45px;
	border-radius: 100%;
	border : 1px solid #eee;
	margin-right : 20px;
}


.sidebar .profile-details {
    position: fixed;
    bottom: 0;
    width: 260px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    background: #6E9888;
    padding: 12px 0;
    transition: all 0.5s ease;
}





 </style>     
         
   </head>
<body>

<script>
	function Deny(){
		$(document).ready(function(){
      		Swal.fire({
      			icon : 'error',
      			confirmButtonColor: "#577D71",   
				title : '권한이 없는 접근입니다!',
				text : '관리자에게문의하세요!',
				footer: '<a href="#">관리자번호 : 051-1234-1234</a>'
      		})
      		
      	});   
	}
	
	
	$(document).ready(function() {
        $('#myModal').on('show.bs.modal', function (event) {
            var modal = $(this);
            var contentHolder = modal.find('.modal-body');

            // AJAX를 사용하여 info.jsp 페이지를 로드
            $.ajax({
                type: 'GET',
                url: '/system/info', // info.jsp의 경로에 맞게 수정
                success: function(data) {
                    contentHolder.html(data);
                },
                error: function() {
                    contentHolder.html('<p>데이터를 불러오는 중에 오류가 발생했습니다.</p>');
                }
            });
        });
    });
		


		
</script>



 <div class="sidebar close">
    <div class="logo-details">
      <i class="fa-solid fa-bars"></i>
     <a href="/system/mainpage" style="text-decoration-line: none;">
      <span class="logo_name">AWESOMETIC</span>
     </a> 
    </div>
    <ul class="nav-links">
    
    <c:choose>
     <c:when test="${auth.equals('시스템관리') && id.equals('admin')}">
       <c:if test="${sessionScope.status eq 'Y'&& id.equals('admin') }">
      <li>
        <div class="iocn-link">
          <a href="#">
			<i class="fa-solid fa-gear"></i>
            <span class="link_name">시스템관리</span>
          </a>
          <i class="fa-solid fa-chevron-down"></i> <!--드롭다운 아이콘 클래스명 지정하는 곳-->
        </div>
       <ul class="sub-menu">
         <c:if test="${!empty id && id.equals('admin') && sessionScope.status eq 'Y'}">
         	<li><a class="link_name" href="#">시스템 관리</a></li>
           <li><a class="dropdown-item" href="/system/memberlist">사원정보목록</a></li>
<!--            <li><a class="dropdown-item" href="/system/join">사원등록</a></li> -->
           <li><a class="dropdown-item" href="/system/commoncodelist">공통코드목록</a></li>
<!--            <li><a class="dropdown-item" href="/system/commoncodejoin">공통코드등록</a></li> -->
           <li><a class="dropdown-item" href="/system/menulist">시스템메뉴관리</a></li>
        </c:if>
       </ul>
      </li>
      </c:if>
     </c:when>
    <c:otherwise>
     <li>
      <div class="iocn-link">
        <a href="#" onclick="Deny()">
			<i class="fa-solid fa-gear"></i>
          <span class="link_name">시스템관리</span>
        </a>
        <i class="fa-solid fa-chevron-down"></i>
      </div>
     </li>
    </c:otherwise>
  </c:choose> 
      
      
   <c:choose>
  	<c:when test="${auth.equals('기준정보관리') || id.equals('admin') }">
    	<c:if test="${sessionScope.status eq 'Y' || id.equals('admin') }">
      <li>
        <div class="iocn-link">
          <a href="#">
            <i class="fa-solid fa-database"></i>
            <span class="link_name">기준정보관리</span>
          </a>
          <i class="fa-solid fa-chevron-down"></i> <!--드롭다운 아이콘 클래스명 지정하는 곳-->
        </div>
        <ul class="sub-menu">
          <li><a class="link_name" href="#">기준정보관리</a></li>
          <li><a href="/product/firstpage">품목정보</a></li>
          <li><a href="/amount/firstpage">BOM정보</a></li>
          <li><a href="/business/firstpage">거래처정보</a></li>
        </ul>
      </li>
     </c:if>
     	<c:if test="${sessionScope.status eq 'N' }">
              <script>
              	$(document).ready(function(){
              		Swal.fire({
              			icon : 'info',
        				title : '메뉴가정지되었습니다!',
        				text : '관리자에게문의하세요!',
        				footer: '<a href="#">관리자번호 : 051-1234-1234</a>'
              		})
              		
              	});   
              </script>
      	</c:if>
     </c:when>
 <c:otherwise>
    <li>
      <div class="iocn-link">
        <c:choose>
          <c:when test="${not id.equals('admin')}">
            <a href="#" onclick="Deny()">
          </c:when>
          <c:otherwise>
            <a href="#">
          </c:otherwise>
        </c:choose>
        <i class="fa-solid fa-database"></i>
        <span class="link_name">기준정보관리</span>
      </a>
      <i class="fa-solid fa-chevron-down"></i>
    </div>
  </li>
 </c:otherwise>
</c:choose> 
   
   
 
  <c:choose>
     <c:when test="${auth.equals('영업관리') || id.equals('admin')}">
      <c:if test="${sessionScope.status eq 'Y' || id.equals('admin')}">
      <li>
        <div class="iocn-link">
          <a href="/sales/salesPlan">
			<i class="fa-solid fa-building"></i>
            <span class="link_name">영업관리</span>
          </a>
          <i class="fa-solid fa-chevron-down"></i> <!--드롭다운 아이콘 클래스명 지정하는 곳-->
        </div>
        <ul class="sub-menu">
          <li><a class="link_name" href="#">영업관리</a></li>
          <li><a href="/sales/salesPlan">수주관리</a></li>
          <li><a href="/sales/salesAccept">수주목록</a></li>
          <li><a href="/shipping/shipPlan">출하계획</a></li>
          <li><a href="/shipping/shipping">출하지시</a></li>
        </ul>
      </li>
      </c:if>
       	<c:if test="${sessionScope.status eq 'N' }">
              <script>
              	$(document).ready(function(){
              		Swal.fire({
              			icon : 'info',
        				title : '메뉴가정지되었습니다!',
        				text : '관리자에게문의하세요!',
        				footer: '<a href="#">관리자번호 : 051-1234-1234</a>'
              		})
              		
              	});   
              </script>
      	</c:if>
     </c:when>
	<c:otherwise>
	    <li>
	      <div class="iocn-link">
	        <c:choose>
	          <c:when test="${not id.equals('admin')}">
	            <a href="#" onclick="Deny()">
	          </c:when>
	          <c:otherwise>
	            <a href="#">
	          </c:otherwise>
	        </c:choose>
	        <i class="fa-solid fa-building"></i>
	        <span class="link_name">영업관리</span>
	      </a>
	      <i class="fa-solid fa-chevron-down"></i>
	    </div>
	  </li>
	 </c:otherwise>
	</c:choose> 
      
      
   <c:choose>
     <c:when test="${auth.equals('생산관리') || id.equals('admin')}">
      <c:if test="${sessionScope.status eq 'Y' || id.equals('admin')}">
      <li>
        <div class="iocn-link">
          <a href="#">
			<i class="fa-solid fa-industry"></i>
            <span class="link_name">생산관리</span>
          </a>
          <i class="fa-solid fa-chevron-down"></i> <!--드롭다운 아이콘 클래스명 지정하는 곳-->
        </div>
        <ul class="sub-menu">
          <li><a class="link_name" href="#">생산관리</a></li>
          <li><a href="/instructions/request">생산요청목록</a></li>
          <li><a href="/instructions/search">작업지시관리</a></li>
          <li><a href="/productionLine/search">생산라인관리</a></li>
          <li><a href="/product/search">생산품관리</a></li>
        </ul>
      </li>
      </c:if>
      	<c:if test="${sessionScope.status eq 'N' }">
             <script>
              	$(document).ready(function(){
              		Swal.fire({
              			icon : 'info',
        				title : '메뉴가정지되었습니다!',
        				text : '관리자에게문의하세요!',
        				footer: '<a href="#">관리자번호 : 051-1234-1234</a>'
              		})
              		
              	});   
              </script>
      	</c:if>
     </c:when>
     
      <c:otherwise>
	    <li>
	      <div class="iocn-link">
	        <c:choose>
	          <c:when test="${not id.equals('admin')}">
	            <a href="#" onclick="Deny()">
	          </c:when>
	          <c:otherwise>
	            <a href="#">
	          </c:otherwise>
	        </c:choose>
	        <i class="fa-solid fa-industry"></i>
	        <span class="link_name">생산관리</span>
	      </a>
	      <i class="fa-solid fa-chevron-down"></i>
	    </div>
	  </li>
	 </c:otherwise>
	</c:choose> 
  
  
   <c:choose>
     <c:when test="${auth.equals('자재관리') || id.equals('admin')}">
      <c:if test="${sessionScope.status eq 'Y' || id.equals('admin')}">
      <li>
        <div class="iocn-link">
          <a href="#">
			<i class="fa-solid fa-warehouse"></i>
            <span class="link_name">자재관리</span>
          </a>
          <i class="fa-solid fa-chevron-down"></i> <!--드롭다운 아이콘 클래스명 지정하는 곳-->
        </div>
        <ul class="sub-menu">
          <li><a class="link_name" href="#">자재관리</a></li>
          <li><a href="/materials/stockList">재고관리</a></li>
          <li><a href="/materials/inlist">입고관리</a></li>
          <li><a href="/materials/outList">출고관리</a></li>
          <li><a href="/materials/purchaselist">발주관리</a></li>
        </ul>
      </li>
      </c:if>
      	<c:if test="${sessionScope.status eq 'N' }">
             <script>
              	$(document).ready(function(){
              		Swal.fire({
              			icon : 'info',
        				title : '메뉴가정지되었습니다!',
        				text : '관리자에게문의하세요!',
        				footer: '<a href="#">관리자번호 : 051-1234-1234</a>'
              		})
              		
              	});   
              </script>
      	</c:if>
     </c:when>
     
     
     <c:otherwise>
	    <li>
	      <div class="iocn-link">
	        <c:choose>
	          <c:when test="${not id.equals('admin')}">
	            <a href="#" onclick="Deny()">
	          </c:when>
	          <c:otherwise>
	            <a href="#">
	          </c:otherwise>
	        </c:choose>
	        <i class="fa-solid fa-warehouse"></i>
	        <span class="link_name">자재관리</span>
	      </a>
	      <i class="fa-solid fa-chevron-down"></i>
	    </div>
	  </li>
	 </c:otherwise>
  </c:choose> 
  
  
  
   <!-- 공지사항 칸 -->
     <c:if test="${!empty id}"> 
      <li>
        <a href="/system/listAll">
          <i class="fa-solid fa-check"></i>
          <span class="link_name">공지사항</span>
        </a>
        <ul class="sub-menu blank">
          <li><a class="link_name" href="#">공지사항</a></li>
        </ul>
      </li>
    </c:if>
   <!-- 공지사항 칸 -->     
        
  
      
      
      
      
      
      
      
	  <li>
	    <div class="profile-details">
	      <div class="profile-content">
	        <img src="${pageContext.request.contextPath}/resources/img/members/${img}" onerror="this.onerror=null; this.src='/resources/img/members/default.jpg';"  class="user-img">
	      </div>
	      	<div class="name-job">
		        <div class="profile_name">${name}(${department})</div>
		        <div class="job">${position}</div>
     		 </div>   
	    </div>
	 </li>
	 
	 
	 
	</ul>
</div>
  
  <section class="home-section">
    <div class="home-content">
      <span class="text-header"><a href="/system/mainpage"><img src="${pageContext.request.contextPath}/resources/img/icons/AWESOMETIC.png" style="width : 200px; height:50px;"></a></span>
      	  <div class="user-greeting">
	      	  <div class="user-img-container">
	      	  	<img src="${pageContext.request.contextPath}/resources/img/members/${img}" class="user-img">
	      	  </div>
			<p class="id-greeting">${id } 님 안녕하세요</p>
			
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
          <i class="fa-solid fa-user-tie" style="color: #e8edf7; font-size: 21px;"></i>
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="/system/logout">로그아웃</a></li>
          <li><a class="dropdown-item"  data-bs-toggle="modal" data-bs-target="#myModal" href="javascript:void(0);" >상세정보</a></li>
        </ul>
       </div>
       
       
       <div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    		<div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="myModalLabel">상세정보</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                <jsp:include page="/WEB-INF/views/system/info.jsp"/>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
	            </div>
	        </div>
    	</div>
	</div>
       
	  </div>	
    </div>
  </section>


       
     
  







































  <script src="/resources/js/sidehead/sideheadscript.js"></script> <!-- 사이드바 script -->

  <!-- 부트스트랩 js 추가-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>


</body>
</html>