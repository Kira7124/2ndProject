 function goContent(in_code) {

	$.ajax({
		url : "detailList", // 
		type : "post",
		dataType : "json", // Jackson Databind사용해서 json으로 받기
		data : {
			"in_code" : in_code
		}, // 받은 매개변수 전달
		success : content,
		error : function() {
			alert("error");
		}
	});

}

function content(data) { // 에이젝스에서 받은 값으로 출력하기
	console.log(data);
	var listHtml = "<div class='list-box'>";

	listHtml += "<table class='table table-hover'>";
	listHtml += "<thead>";
	listHtml += "<tr class='table-success'>";
	listHtml += "<th scope='col'>입고코드</th>";
	listHtml += "<th scope='col'>발주코드</th>";
	listHtml += "<th scope='col'>품목코드</th>";
	listHtml += "<th scope='col'>품목명</th>";
	listHtml += "<th scope='col'>수량</th>";
	listHtml += "<th scope='col'>단위</th>";
	listHtml += "<th scope='col'>카테고리</th>";
	listHtml += "<th scope='col'>입고담당자</th>";
	listHtml += "</tr>";
	listHtml += "</thead>";
	listHtml += "<tbody>";

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

	listHtml += "<tr>";
	listHtml += "<td>" + data.in_code + "</td>";
	listHtml += "<td></td>";
	listHtml += "<td>" + data.product_code + "</td>";
	listHtml += "<td>" + data.name + "</td>";
	listHtml += "<td>" + data.in_quantity + "</td>";
	listHtml += "<td>" + data.unit + "</td>";
	listHtml += "<td>" + data.category + "</td>";
	listHtml += "<td>" + data.user_id + "</td>";
	listHtml += "</tr>";
	listHtml += "</tbody>";
	listHtml += "</table>";

	// 위 표가 들어갈 자리의 아이디값 가져와서 입력하기
	$("#view2").html(listHtml);
}