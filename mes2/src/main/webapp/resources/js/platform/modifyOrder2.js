//var header = "X-CSRF-TOKEN";
var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

// 수정하기
function modifyOrder(order_code, sales_status) {
	var allProduct_code = document.querySelectorAll('[name="product_code"]');

	// 품목 1개 이상 선택 제어
	if(allProduct_code.length === 0) {
		Swal.fire({
			text: "품목추가 버튼을 통해 품목을 1개 이상 선택하세요",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
	// DTO 값 json으로 변경
	var productTable = document.getElementById('modifyProductList');
	var sopList = [];
	for(var i = 0; i<productTable.rows.length; i++) {
		var row = productTable.rows[i];
		var sopDTO = {
			order_code: order_code,
			product_code: row.querySelector('[name="product_code"]').value,
			sales_quantity: row.querySelector('[name="sales_quantity"]').value
		};
		
		sopList.push(sopDTO);
	}
	
	var jsonSopList = JSON.stringify(sopList);
	
	console.log(jsonSopList);
	
	if(sopList != null) {
		Swal.fire({
			text: "발주를 수정하시겠습니까?",
			icon: "question",
			showCancelButton: true,
			confirmButtonColor: "#577D71", // confirm 버튼 색상
			cancelButtonColor: '#d33', // cancle 버튼 색상
			confirmButtonText: '확인', // confirm 버튼 텍스트 지정
			cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		}).then((result) => {
			if (result.isConfirmed) {
				$.ajax({
					url : "/platform/modifyOrder",
					method : "post",
					data : jsonSopList,
					contentType : 'application/json; charset=utf-8',
					beforeSend: function(xhr) {
						xhr.setRequestHeader(header, token)
					},
					async: false,
					success : function(data) {
						Swal.fire({
							text: "발주 수정이 완료되었습니다.",
							confirmButtonColor: "#577D71",
							icon: "success"
						})
						.then(function(){
							location.href='/platform/orderList';
						});
					},
					error : function() {
						Swal.fire({
							text: "발주 수정에 실패했습니다.",
							confirmButtonColor: "#577D71",
							icon: "error"
						})
						.then(function(){
							location.reload();
						});
					}
				});
			} else {
				return false;
			}
		});
	}
}

// 수정 취소하기
function cancleModify() {
		Swal.fire({
			text: "수정을 취소하시겠습니까?",
			icon: "question",
			showCancelButton: true,
			confirmButtonColor: "#577D71", // confirm 버튼 색상
			cancelButtonColor: '#d33', // cancle 버튼 색상
			confirmButtonText: '확인', // confirm 버튼 텍스트 지정
			cancelButtonText: '취소', // cancel 버튼 텍스트 지정
		}).then((result) => {
			if (result.isConfirmed) {
				location.reload();
			} else {
				return false;
			}
		});
}

function trRemove(ths) {
	var $tr = $(ths).parents("tr");
	$tr.remove();
}