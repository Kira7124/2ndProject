// 출고 상세 페이지
function getOutDetail(out_index, out_code) {
	
	$.ajax({
		url : "/materials/outDetail",
		method : "GET",
		data : {
			"out_index" : out_index,
			"out_code" : out_code
		},
		success : function(response) {
			$("#bottomContent").html(response);
		},
		error : function() {
			console.log(out_index, out_code);
			Swal.fire({
				text: "상세 조회에 실패했습니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}

function trRemove(ths) {
	var $tr = $(ths).parents("tr");
	$tr.remove();
}

//	if(out_code === '') {
//		$.ajax({
//			url : "/materials/outDetail",
//			method : "GET",
//			data : {
//				"out_index" : out_index,
//				"product_code" : product_code
//			},
//			success : function(response) {
//				console.log(out_index, product_code, out_code);
//				$("#bottomContent").html(response);
//			},
//			error : function() {
//				console.log(out_index, product_code, out_code);
//				alert("fail");
//			}
//		});
//	} else {
//		$.ajax({
//			url : "/materials/outDetail",
//			method : "GET",
//			data : {
//				"out_index" : out_index,
//				"product_code" : product_code,
//				"out_code" : out_code
//			},
//			success : function(response) {
//				$("#bottomContent").html(response);
//			},
//			error : function() {
//				console.log(out_index, product_code, out_code);
//				alert("fail");
//			}
//		});
//	}