// 출고 품목 입력
function registProduct() {
	var stock_indexList = [];
	
	$("input[name='stock_index']:checked").each(function(){
		stock_indexList.push($(this).val());
	});
	
	var outQuantity = true;
	
	// 출고 수량 제어
	if(stock_indexList.length != 0) {
		$.each(stock_indexList, function(idx, val){
			var stock_index = val;
			var quantity = document.querySelector("input[name='quantity" + stock_index + "']").value;
			var useQuantity = document.querySelector("input[name='useQuantity" + stock_index + "']").value;
			
			if(parseInt(quantity) < parseInt(useQuantity)) {
				outQuantity = false;
				Swal.fire({
					text: "출고 수량이 재고를 초과할 수 없습니다.",
					confirmButtonColor: "#577D71",
					icon: "error"
				}).then(function(){
					return false;
				});
			}
			
			if(parseInt(useQuantity) <= 0) {
				outQuantity = false;
				Swal.fire({
					text: "출고 수량은 1개 이상이어야합니다.",
					confirmButtonColor: "#577D71",
					icon: "error"
				}).then(function(){
					return false;
				});
			}
		});
	}
	
	if (outQuantity === false) {
		return false;
	}
	// 출고 수량 제어 끝
	
	var index = 0;
	if(stock_indexList.length != 0) {
		$.each(stock_indexList, function(idx, val){
			var stock_index = val;
			var pd_lot = document.querySelector("input[name='pd_lot" + stock_index + "']").value;
			var product_code= document.querySelector("input[name='product_code']").value;
			var quantity = document.querySelector("input[name='quantity" + stock_index + "']").value;
			var useQuantity = document.querySelector("input[name='useQuantity" + stock_index + "']").value;
			
			$("#outProductList", opener.document).append(
				"<tr><td><input type='hidden' name='StockDTO["+index+"].stock_index' id='"+stock_index+"' value='"+stock_index+"'></td>" +
				"<td><input type='hidden' name='StockDTO["+index+"].pd_lot' id='pd_lot"+stock_index+"' value='"+pd_lot+"'>"+pd_lot+"</td>" +
				"<td><input type='hidden' name='StockDTO["+index+"].product_code' id='product_code"+stock_index+"' value='"+product_code+"'>"+product_code+"</td>" +
				"<td><input type='hidden' name='StockDTO["+index+"].useQuantity' id='useQuantity"+stock_index+"' value='"+useQuantity+"'>"+useQuantity+"</td>" +
				"<td><button type='button' onclick='trRemove(this);' class='deleteBtn'>x</button></td></tr>"
			);
			
			index++;
		});
		window.close();
	} else {
		Swal.fire({
			text: "재고를 하나 이상 선택하세요.",
			confirmButtonColor: "#577D71",
			icon: "error"
		});
	}
	
}

//	var stock_indexList = [];
//	var stockList = [];
//	
//	$("input[name='stock_index']:checked").each(function(){
//		stock_indexList.push($(this).val());
//	});
//	
//	$.each(stock_indexList, function(idx, val){
//		var stockInfo = {
//			"stock_index" : val,
//			"useQuantity" : $("input[name='useQuantity"+val+"']").val()
//		};
//		
//		stockList.push(stockInfo);
//	});
//	
//	if(stockList.length != 0) {
//		$.ajax({
//			url : "/restOut/registProduct",
//			method : "post",
//			data : JSON.stringify(stockList),
//			contentType : "application/json",
//			success : function(data) {
//				console.log(data);
//				$("#outProductList", opener.document).append(
//						"<tr><td><input type='hidden' name='stock_index' id='"+data.stock_index+"' value='"+data.stock_index+"'>"+data.stock_index+"</td>" +
//						"<td>"+data.name+"</td>" +
//						"<td><input type='hidden' name='price' id='p"+data.product_code+"' value='"+data.price+"'>"+new Intl.NumberFormat('ko-KR').format(data.price)+"원</td>" +
//						"<td><input type='number' name='sales_quantity' id='q"+data.product_code+"' step='100' min='300' oninput='cal(this);'>EA</td>" +
//						"<td><input type='text' id='s"+data.product_code+"' readonly>원</td>" +
//						"<td><button type='button' onclick='trRemove(this);' class='deleteBtn'>x</button></td></tr>");
//				window.close();
//			},
//			error : function() {
//				alert("fail");
//			}
//		});
//	} else {
//		alert('재고를 하나 이상 선택하세요');
//	}