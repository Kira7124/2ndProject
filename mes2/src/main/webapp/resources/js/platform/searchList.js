var header = "X-CSRF-TOKEN";
var token = $("meta[name='_csrf']").attr("content");

function searchProduct() {
	var searchForm = document.querySelector("#searchForm");
	var searchType = document.querySelector("#searchType");
	var search = document.querySelector("#search");
	
	console.log(search);
	
	if(searchType.value == '') {
		Swal.fire({
			text: "구분을 선택하세요.",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
	if(search.value == "") {
		Swal.fire({
			text: "검색어를 입력하세요",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	searchForm.submit();
}

function registProduct() {
	var idx = document.querySelector("input[name='idx']:checked");
	console.log(idx);

	if (idx) {
		idx = idx.value;

		var product_code = document.querySelector("input[name='product_code" + idx + "']").value;
		console.log(product_code);
		$.ajax({
			url : "/restPlatform/registProduct",
			method : "post",
			data : {"product_code" : product_code},
			dataType : "json",
			beforeSend: function(xhr) {
				xhr.setRequestHeader(header, token)
			},
			async: false,
			success : function(data) {
				console.log(data);
				$("#insertProductList", opener.document).append(
						"<tr><td><input type='hidden' name='product_code' id='"+data.product_code+"' value='"+data.product_code+"'>"+data.product_code+"</td>" +
						"<td>"+data.name+"</td>" +
						"<td><input type='hidden' name='price' id='p"+data.product_code+"' value='"+data.price+"'>"+new Intl.NumberFormat('ko-KR').format(data.price)+"원</td>" +
						"<td><input type='number' name='sales_quantity' id='q"+data.product_code+"' step='500' min='500' oninput='cal(this);'>EA</td>" +
						"<td><input type='text' id='s"+data.product_code+"' readonly>원</td>" +
						"<td><button type='button' onclick='trRemove(this);' class='deleteBtn'>x</button></td></tr>");
				window.close();
			},
			error : function() {
				Swal.fire({
					text: "제품 등록에 실패했습니다.",
					confirmButtonColor: "#577D71",
					icon: "error"
				});
			}
		});
	} else {
		Swal.fire({
			text: "제품을 선택하세요",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
	}
}



//$(".pQuantity").click(function(){
//	var selectedIdx = document.querySelector("input[name='idx']:checked");
//	
//	if(selectedIdx) {
//		var idx = selectedIdx.value;
//		var price = document.querySelector("input[name='price" + idx + "']").value;
//		var quantityId = $(this).attr('id');
//		var quantityIdx = quantityId.substring(quantityId.length - idx.length);
//		if(idx == quantityIdx) {
//			$("#" + quantityId).on("input", function(){
//				var quantity = $("#" + quantityId).val();
//				var total = quantity * price;
//				var formattedTotal = new Intl.NumberFormat('ko-KR').format(total);
//				$('#sum' + idx).val(formattedTotal);
//			});
//		} else {
//			alert('선택한 제품만 입력 가능합니다');
//		}
//	} else {
//		alert('제품을 먼저 선택하세요');
//	}
//});

//var product_code = document.querySelector("input[name='product_code" + idx + "']").value;
//var name = document.querySelector("input[name='name" + idx + "']").value;
//var price = document.querySelector("input[name='price" + idx + "']").value;
//var sales_quantity = document.querySelector("input[name='sales_quantity" + idx + "']").value;
//var sum = document.querySelector("input[name='sum" + idx + "']").value;
//
//var formattedPrice = new Intl.NumberFormat('ko-KR').format(price);
//
//$("#insertProductList", opener.document).append(
//	"<tr><td><input type='hidden' name='product_code' value='"+product_code+"'>"+product_code+"</td>" +
//	"<td><input type='hidden' name='name' value='"+name+"'>"+name+"</td>" +
//	"<td><input type='hidden' name='price' value='"+price+"'>"+formattedPrice+"원</td>" +
//	"<td><input type='hidden' name='sales_quantity' value='"+sales_quantity+"'>"+sales_quantity+"EA</td>" +
//	"<td>"+sum+"원</td>" +
//	"<td><button type='button' onclick='trRemove(this);' class='deleteBtn'>x</button></td></tr>"
//);
//window.close();
