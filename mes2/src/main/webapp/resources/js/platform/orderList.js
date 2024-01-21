var header = "X-CSRF-TOKEN";
var token = $("meta[name='_csrf']").attr("content");

// 검색
function searchList() {
	
}

// 발주 추가
function insertOrder() {
	$.ajax({
		url : "/platform/insertOrder",
		method : "GET",
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			Swal.fire({
				text: "발주 추가가 불가능합니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}

// 발주 상세 페이지
function getOrderDetail(order_code, order_date) {
	$.ajax({
		url : "/platform/orderDetail?order_code=" + order_code,
		method : "GET",
		data : {
			"order_date" : order_date
		},
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			Swal.fire({
				text: "상세 페이지 조회가 불가능합니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}


////수령 완료
//function completeOrder(order_code) {
//	console.log(order_code);
//	
//	Swal.fire({
//		text: "수령 완료 처리 하시겠습니까?",
//		icon: "question",
//		showCancelButton: true,
//		confirmButtonColor: "#577D71", // confirm 버튼 색상
//		cancelButtonColor: '#d33', // cancle 버튼 색상
//		confirmButtonText: '확인', // confirm 버튼 텍스트 지정
//		cancelButtonText: '취소', // cancel 버튼 텍스트 지정
//	}).then((result) => {
//		if (result.isConfirmed) {
//			$.ajax({
//				url : "/restPlatform/receiveDelivery",
//				type : "POST",
//				data : {
//					"order_code" : order_code
//				},
//				success : function() {
//					Swal.fire({
//						text: "수령 완료 처리 하였습니다.",
//						confirmButtonColor: "#577D71",
//					});
//					location.href = '/platform/orderList';
//				},
//				error : function() {
//					Swal.fire({
//						text: "수령 완료 처리에 실패했습니다.",
//						confirmButtonColor: "#577D71",
//					});
//				}
//			});
//		} else {
//			Swal.fire({
//				text: "수령 현황을 유지합니다.",
//				confirmButtonColor: "#577D71",
//			});
//		}
//	});
//}