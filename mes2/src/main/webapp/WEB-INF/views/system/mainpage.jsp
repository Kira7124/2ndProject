<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Jquery 라이브러리 추가 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="${pageContext.request.contextPath}/resources/fullcalander/main.css" rel='stylesheet' />
<script src="${pageContext.request.contextPath}/resources/fullcalander/main.js"></script>
<script src="${pageContext.request.contextPath}/resources/fullcalander/locales-all.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main/weather.js"></script>


 
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon">
<title>메인페이지</title>
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
   integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
   crossorigin="anonymous"></script>
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
   integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
   crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10"></link>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<!-- Font Awesome CDN 사용 예시 -->
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>


<style text="text/css">


.container {
   display: flex;
   flex-direction: column;
   align-items: flex-end;
   text-align: right;
}

div p {
   display: inline; /* 또는 display: inline-block; */
   margin: 0; /* 기본 마진 제거 */
}

.weather-container.offset-sm-3 {
   margin-left : 0px;
/*    margin-right: 130px; */
   margin-top: 25px;
   height: 350px;
   width: 910px;
}



#weather-card-body {
   display: flex;
   flex-direction: column;
   margin-right: 20px;
   text-align: right;
}

#weather-card-frame{
   margin-left : 50px;
   background-image: url('/resources/img/mainpage/people.png');
    background-size: contain; /* 배경 이미지를 요소에 맞춰서 보여주도록 설정 */
    background-repeat: no-repeat;
    background-position: center bottom;   
    height: 350px;
   
}


#card-body-first-mother{
   margin-top: 20;
   margin-left: 100;

}

.card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  /* Hover 효과 */
  .card:hover {
    transform: scale(1.05); /* 크기 확대 */
    box-shadow: 0 0 15px rgba(0, 128, 0, 0.9); /* 그림자 효과 강화 */
  }



</style>


</head>

	
<body>


	<%@ include file="sidehead.jsp"%>




<div class="row">


<div class="col-sm-6" id="status">

  <div class="row" style="margin-left: 90px;">
     <!-- 첫 번째 card -->
       <div class="col-md-3" id="card-odd" style="margin-top: 20px;">
            <div class="offset-sm-3 card border-success" style="width: 20rem; box-shadow: 0 0 10px rgba(60, 179, 113);">
           <div class="card-body" id="card-body-first">
             <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/professionals.png" style="width:35px; height:35px;"></h5>
             <h3>Employees</h3><br>
             <p class="card-text" style="font-weight: bold;">${total}명</p>
           </div>
         </div>
        </div>
        
       <div class="col-md-6" style="margin-top: 20px; margin-left: 50px;">
         <!-- 두 번째 card -->
            <div class="offset-sm-6 card border-warning" style="width: 20rem; box-shadow: 0 0 10px rgba(255, 165, 0, 0.8);">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/factory.png" style="width:35px; height:35px;"></h5>
                    <h3>Productions</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalProduct}개</p>
                </div>
            </div>
        </div>
   </div>
   
   <div class="row" style="margin-left: 90px;">
        <!-- 세 번째 card -->
        <div class="col-md-3" id="card-odd" style="margin-top: 20px;">
            <div class="offset-sm-3 card border-primary"" style="width: 20rem; box-shadow: 0 0 10px rgba(29, 117, 211, 1);">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title"><img src="${pageContext.request.contextPath}/resources/img/icons/stock.png" style="width:35px; height:35px;"></h5>
                      <h3>Stocks</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalIn}개</p>
                </div>
            </div>
        </div>
   
  
        <div class="col-md-6" style="margin-top: 20px; margin-left: 50px;">
           <!-- 네 번째 card -->
            <div class="offset-sm-6 card border-danger " style="width: 20rem; box-shadow: 0 0 10px rgba(235, 62, 10, 1); ">
                <div class="card-body" id="card-body-first">
                    <h5 class="card-title" ><img src="${pageContext.request.contextPath}/resources/img/icons/out-of-stock.png" style="width:35px; height:35px;"></h5>
                     <h3 >Out of Stocks</h3><br>
                    <p class="card-text" style="font-weight: bold;">${totalOut}개</p>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
   
   
</div>   
   

  <div class="col-sm-6 order-sm-3" id="weather">   
   <div class="offset-sm-3 weather-container">
      <div class="card mb-3" id="weather-card-frame">
         <div class="row g-0">
            <div class="col-md-4">
            </div>
            <div class="col-md-8">
               <div class="card-body" id="weather-card-body">
                  <h3 class="location"></h3><br>   
                  <h5 class="current-time"></h5>
                  <p class="current-temp"><br>
                  <p class="weather-like"><br>                        
               </div>
            </div>
         </div>
      </div>
   
      

   </div>
  </div>      
</div>
   <!-- <i class="fa-solid fa-cloud"></i> -->
   <!-- <i class="fa-solid fa-sun"></i> -->
   <!-- <i class="fa-solid fa-umbrella"></i> -->



   <div class="row-second" style="display: flex; width: 100%; justify-content: space-between; margin-right: 13%; margin-top: 10px;">
   
       <div style="width: 10%;"> <!-- 나열할 두 번째 요소 -->
           <div id="piechart" style="width: 580px; height: 600px; margin-left: 10px; margin-top: 10px;"></div>
       </div>
      
       <div style="width: 10%;"> <!-- 나열할 두 번째 요소 -->
           <div id="chart_div" style="width: 580px; height: 500px; margin-left: -10px; margin-top: 10px;"></div>
       </div>
      
      
      
   
       <div style="width: 51%;  margin-right: 50px;"> <!-- 나열할 첫 번째 요소 -->
           <%@ include file="listAll2.jsp"%>
       </div>
   
    
   </div>




   
   
   
   <!-- 구글차트 js -->
   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
      
       var totalOkValue = ${totalOk != null && !totalOk.equals('[null]') ? totalOk : 0};     
         var totalNoValue = ${totalNo != null && !totalNo.equals('[null]') ? totalNo : 0};       
         
         
        var data = google.visualization.arrayToDataTable([
          ['인사', 'Hours per Day'],
          ['양품',    totalOkValue],
          ['불량품',      totalNoValue]
        
 
        ]);

        var options = {
          title: '불량품비율',
          backgroundColor: '#F5FBF0',
          is3D: true
            
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>





   <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    google.charts.load('current', {packages: ['corechart']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic() {
         
       var   firstLineValue = ${firstLine != null && !firstLine.equals('[null]') ? firstLine : 0};     
         var secondLineValue = ${secondLine != null && !secondLine.equals('[null]') ? secondLine : 0}; 
         var thirdLineValue = ${thirdLine != null && !thirdLine.equals('[null]') ? thirdLine : 0}; 
         var forthLineValue = ${forthLine != null && !forthLine.equals('[null]') ? forthLine : 0}; 
         var fifthLineValue = ${fifthLine != null && !fifthLine.equals('[null]') ? fifthLine : 0}; 
         var sixthLineValue = ${sixthLine != null && !sixthLine.equals('[null]') ? sixthLine : 0}; 
         var sevenLineValue = ${sevenLine != null && !sevenLine.equals('[null]') ? sevenLine : 0}; 
       
       
       
       
          var data = google.visualization.arrayToDataTable([
             ['Line', '공정률',{ role: 'style' }],
              ['1', firstLineValue,'#3366cc'],
              ['2', secondLineValue, '#dc3912'],
              ['3', thirdLineValue, '#ff9900'],
              ['4', forthLineValue, '#109618'],
              ['5', fifthLineValue, '#990099'],
              ['6', sixthLineValue, '#0099c6'],
              ['7', sevenLineValue, '#dd4477']
          ]);

          var options = {
            title: '라인별공정률',
            chartArea: {width: '55%'},
            backgroundColor: '#F5FBF0',
//             hAxis: {
//               title: '라인별공정률',
//               minValue: 0
//             }
           
          };

          var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));

          chart.draw(data, options);
        }
    </script>








</body>
</html>