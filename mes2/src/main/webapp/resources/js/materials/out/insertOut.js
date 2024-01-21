function insertOutProduct(product_code) {
	var productList = window.open("/materials/stockInsert?product_code=" + product_code, "_blank","height=600, width=1000");
}

//출고 등록
function insertOut() {
	// 수량 비교해서 제어
	var useQuantityList = [];
	var sum = 0;
	var quantity = document.querySelector("input[name='quantity']").value;
	
	$("[id^='useQuantity']").each(function(){
		useQuantityList.push($(this).val());
	});
	
	$.each(useQuantityList, function(idx, val){
		sum += Number(val);
	});
	
	if(sum != quantity) {
		Swal.fire({
			text: "요청 수량과 출고 수량이 다릅니다",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
    Swal.fire({
        text: "출고하시겠습니까?",
        icon: "question",
        showCancelButton: true,
        confirmButtonColor: "#577D71", // confirm 버튼 색상
        cancelButtonColor: '#d33', // cancle 버튼 색상
        confirmButtonText: '확인', // confirm 버튼 텍스트 지정
        cancelButtonText: '취소', // cancel 버튼 텍스트 지정
    }).then((result) => {
        if (result.isConfirmed) {
            document.getElementById('frm').submit();
        } else {
            return false;
        }
    });
	
}