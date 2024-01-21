 function goContent(product_code){
       
	  $.ajax({
		  url:"acceptContent", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"product_code": product_code }, // 받은 매개변수 전달
		  success: function(data) {
			  content(data, product_code);
		  },
		  error: function(){alert("error");}
	  });
	  
	  
  }
 
 function cancle(){
		
	  $("#salesAcceptContent").html("");
 }
 
 

function content(data, product_code) { // 에이젝스에서 받은 값으로 출력하기

	var listHtml = "<div class='list-box'>";
	if (!updateBtn) {
		listHtml += "<button type='button' class='btn btn-secondary' onclick='up("
				+ data.idx + ")'>수정</button>";
	}
	listHtml += "<button type='button' class='btn btn-secondary' onclick='cancle()'>닫기</button>";
	if (updateBtn) {
		listHtml += "<button type='reset' class='btn btn-secondary'>취소</button>";
	}

	if (updateBtn) {
		listHtml += "<button type='button' class='btn btn-secondary' onclick='save("
				+ data.idx + ")'>저장</button>";
	}

	listHtml += "<table class='table table-hover'>";
	listHtml += "<thead>";
	listHtml += "<tr class='table-success'>";
	listHtml += "<th scope='col'>입고코드</th>";
	listHtml += "<th scope='col'>품목코드</th>";
	listHtml += "<th scope='col'>로트번호</th>";
	listHtml += "<th scope='col'>품목명</th>";

	listHtml += "<th scope='col'>수량</th>";
	listHtml += "<th scope='col'>단위</th>";
	listHtml += "<th scope='col'>카테고리</th>";
	listHtml += "<th scope='col'>입고등록일</th>";
	listHtml += "<th scope='col'>담당자</th>";
	listHtml += "<th scope='col'>진행상황</th>";

	listHtml += "</tr>";
	listHtml += "</thead>";
	listHtml += "<tbody>";

	listHtml += "<tr>";
	 listHtml += "<td><input type='text'  value='" + data.product_code
     + "' readonly/></td>";
listHtml += "<td><input type='text'  value='" + data.code_group_name
     + "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.unit
			+ "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.category
			+ "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.name
			+ "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.in_quantity
			+ "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.in_regdate
			+ "' readonly/></td>";
	listHtml += "<td><input type='text'  value='" + data.user_department
			+ "' readonly/></td>";

	if (updateBtn) {
		listHtml += "<td><input type='text' id='titlec' value='" + data.title
				+ "' /></td>";
	} else {
		listHtml += "<td><input type='text' id='titlec' value='" + data.title
				+ "' readonly/></td>";
	}

	listHtml += "<td><input type='text'  value='" + data.writer
			+ "'readonly/></td>";

	listHtml += "</tr>";
	
	listHtml += "</tbody>";
	listHtml += "</table>";

	// 위 표가 들어갈 자리의 아이디값 가져와서 입력하기
	$("#view2").html(listHtml);
}