// 재고 검색
function searchStock() {
	var searchForm = document.querySelector("#searchForm");
	var searchType = document.querySelector("#searchType");
	var search = document.querySelector("#search");
	
	console.log(search);
	
	if(searchType.value == 'default') {
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

// 재고 상세 조회
function getStockDetail(product_code) {
	window.open("/materials/stockDetail?product_code=" + product_code, "_blank","height=600, width=600");
}