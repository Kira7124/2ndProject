var header = "X-CSRF-TOKEN";
var token = $("meta[name='_csrf']").attr("content");

// 수정하기
function modifyOrder(order_code, order_date, sales_status) {
	console.log(sales_status);

	if (sales_status != 'requested') {
		Swal.fire({
			text: "신청중인 발주만 수정 가능합니다.",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}

	$.ajax({
		url : "/platform/modifyOrder?order_code=" + order_code,
		method : "GET",
		data : {
			"order_date" : order_date
		},
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			Swal.fire({
				text: "발주 수정이 불가능합니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}

// 취소하기
function deleteOrder(order_code, sales_status) {
	console.log(sales_status);

	if (sales_status != 'requested') {
		Swal.fire({
			text: "신청중인 발주만 취소 가능합니다.",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}

	Swal.fire({
		text: "발주를 취소하시겠습니까?",
		icon: "question",
		showCancelButton: true,
		confirmButtonColor: "#577D71", // confirm 버튼 색상
		cancelButtonColor: '#d33', // cancle 버튼 색상
		confirmButtonText: '확인', // confirm 버튼 텍스트 지정
		cancelButtonText: '취소', // cancel 버튼 텍스트 지정
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				text: "발주를 취소합니다.",
				confirmButtonColor: "#577D71",
			})
			.then(function(){
				location.href = "/platform/deleteOrder?order_code=" + order_code;
			});
		} else {
			Swal.fire({
				text: "발주 현황을 유지합니다.",
				confirmButtonColor: "#577D71",
			});
			return false;
		}
	});
}

//수령 완료 처리
function completeOrder(order_code) {
	var complete = window.open("/platform/completeOrder?order_code="+order_code, "_blank","height=500, width=600");
}

// 서명 확인
function getSignature(order_code) {
	var complete = window.open("/platform/signature?order_code="+order_code, "_blank","height=500, width=600");
}
