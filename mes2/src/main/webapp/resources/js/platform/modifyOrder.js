/* Created by Tivotal */

function openProductList2() {
	var productList = window.open("/platform/searchList", "_blank","height=600, width=1000");
}

function cal(ths) {
	var row = $(ths).closest("tr");
	
	var price = row.find('[name="price"]').val();
	var sq = row.find('[name="sales_quantity"]').val();
	var total = row.find('[id^="s"]');
	
	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
	total.val(sum);
}