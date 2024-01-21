//var header = "X-CSRF-TOKEN";
var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

function insertOrder2() {
	var order_date = document.querySelector('[name="order_date"]').value;
	var allProduct_code = document.querySelectorAll('[name="product_code"]');
	var sq = $("input[name=sales_quantity]").length;
	var sqArr = new Array(sq);
	
	for(var i=0; i<sq; i++) {
		sqArr[i] = $("input[name=sales_quantity]").eq(i).val();
	}
	
	// 수량 0 제어
	for(var i=0; i<sq; i++) {
		if(sqArr[i] == "" || sqArr[i] == 0) {
			Swal.fire({
				text: "수량을 입력하세요",
				confirmButtonColor: "#577D71",
				icon: "warning"
			});
			return false;
		}
	}
	

	// 날짜 선택 제어
	if(order_date == undefined || order_date === null || order_date === '') {
		Swal.fire({
			text: "납품 요청일을 선택하세요",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
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
	var productTable = document.getElementById('insertProductList');
	var sopList = [];
	for(var i = 0; i<productTable.rows.length; i++) {
		var row = productTable.rows[i];
		var sopDTO = {
			product_code: row.querySelector('[name="product_code"]').value,
			price: row.querySelector('[name="price"]').value,
			sales_quantity: row.querySelector('[name="sales_quantity"]').value
		};
		
		sopList.push(sopDTO);
	}
	
//	var jsonSopList = JSON.stringify(sopList);
//	console.log(order_date, sopList);
	
	if(sopList != null) {
		$.ajax({
			url : "/platform/insertOrder",
			method : "post",
			data : JSON.stringify({
					"sopList": sopList,
					"order_date" : order_date
					}),
			contentType : 'application/json; charset=utf-8',
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			async: false,
			success : function(data) {
				Swal.fire({
					text: "발주 신청이 완료되었습니다.",
					confirmButtonColor: "#577D71",
					icon: "success"
				})
				.then(function(){
					location.href = "/platform/orderList";
				});
			},
			error : function() {
				Swal.fire({
					text: "발주 신청에 실패했습니다.",
					confirmButtonColor: "#577D71",
					icon: "error"
				})
				.then(function(){
					location.reload();
				});
			}
		});
	}
}

function cancleOrder() {
	location.reload();
}

function trRemove(ths) {
	var $tr = $(ths).parents("tr");
	$tr.remove();
}