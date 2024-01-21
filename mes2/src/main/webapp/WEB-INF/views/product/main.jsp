<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>

    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
  </head>
  <style>
    .search {
      display: flex;
      justify-content: center;
      align-items: center;
      gap: 30px;
      padding: 2px;
      margin-top: 40px;
    }

    .section1 {
      padding: 1px;
      background-color: #e6e6e6;
      width: 1000px;
      height: 500px;
      margin: 0 auto;
      margin-top: 30px;

      
    }

    .list-box {
      width: 800px;
      height: 300px;
      margin: 0 auto;
      overflow-y: auto; /* auto로 설정하여 필요할 때만 수직 스크롤바를 표시합니다 */
      overflow-x: hidden; /* hidden으로 설정하여 수평 스크롤바를 숨깁니다 */
    }

    .list-btn {
      display: flex;
      justify-content: flex-end;
      gap: 20px;
      margin-right: 30px;
      margin-top: 10px;
      margin-bottom: 10px;
    }

    .search-font {
      margin-right: 5px;
      margin-left: 10px;
    }
  </style>
  <body>
    <!-- 검색창 -->

    <section class="section1">
      <form action="/product/search" class="search" method="GET">
        <select id="boundary">
          <option value="">-- 검색선택 --</option>
          <option value="ship">수주번호</option>
          <option value="order">주문번호</option>
          <option value="order">거래항목</option>
          <option value="order">수주처</option>
          <option value="order">수주일</option>
          <option value="order">납기요청일</option>
        </select>

        <div>
          <span class="search-font">검색시작일</span>
          <input id="startDate" type="date" min="2023-12-01" max="2024-12-31" name="startDate" value="${startDate}"/>

          <span class="search-font">검색종료일</span>
          <input
            id="endDate"
            type="date"
            min="2020-01-01"
            max="2030-12-31"
            width="100px"
            name="endDate"
            value="${endDate}"
          />
        </div>

        <input type="text"  placeholder="검색어를 입력하세요" name="name"/>
        <input type="submit" value="검색" />
      </form>

      <!-- 표 -->
      <div class="list">
        <div class="list-btn">
          <button type="button" class="btn btn-secondary" id="input-btn" onclick="openInput();">추가</button>
          <button type="button" class="btn btn-secondary" id="delete-btn">삭제</button>
          <button type="button" class="btn btn-secondary" id="update-btn">수정</button>
        </div>

        <div class="list-box">
          <form id="main-form" class="list-form">
            <table class="table table-hover">
              <thead>
                <tr class="table-success">
                  <th></th>
                  <th scope="col">LOT No.</th>
                  <th scope="col">생산품</th>
                  <th scope="col">수주번호</th>
                  <th scope="col">수량</th>
                  <th scope="col">사용기한</th>
                  <th scope="col">생산날짜</th>
              
                </tr>
              </thead>
              <tbody>
              <c:forEach var="product" items="${productList}">
              	<tr>
                  <td scope="row"><input type="checkbox" class="ck" name="lot" value="${product.pd_lot}"/></td>
                  <td><a href="상세보기확인">${product.pd_lot}</a></td>
                  <td>${product.pd_mdp_code}</td>
                  <td>${product.pd_soi_id}</td>
                  <td>${product.pd_quantity}</td>
                  <td>${product.pd_period}</td>
                  <td>${product.pd_date}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </form>
        </div>
      </div>
    </section>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
    
    <script>
/*     document.getElementById('startDate').value = new Date().toISOString().substring(0, 10);
    document.getElementById('endDate').value = new Date().toISOString().substring(0, 10); */
/* 
    // 페이지 로드 시 실행되는 함수
    window.onload = function() {
      // 현재 날짜를 얻기
      var currentDate = new Date();

      // startDate 값이 없을 때, 초기값 설정: 현재 날짜보다 1주일 전
      var startDateInput = document.getElementById('startDate');
      if (!startDateInput.value) {
        var oneWeekAgo = new Date(currentDate);
        oneWeekAgo.setDate(currentDate.getDate() - 7);
        startDateInput.valueAsDate = oneWeekAgo;
      }

      // endDate 값이 없을 때, 초기값 설정: 현재 날짜
      var endDateInput = document.getElementById('endDate');
      if (!endDateInput.value) {
        endDateInput.valueAsDate = currentDate;
      }
    }; */
    
   
    
    
    $(document).ready(function(){
    	
    });
    
    var formObj = document.getElementById('main-form')
    
    $("#delete-btn").click(function(){
    	formObj.attr("action","/product/delete")
    	formObj.attr("method", "GET");
    	formObj.sumbit();
    });
    
    function openInput(){
        window.open("/product/save","save","width=800px, height=640px")
    }
    
    
    </script>
  </body>
</html>