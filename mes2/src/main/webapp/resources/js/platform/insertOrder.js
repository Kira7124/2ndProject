// 팝업창 중간으로
function openProductList() {
	var productList = window.open("/platform/searchList", "_blank", "width=1000, height=600");
}

function cal(ths) {
	var row = $(ths).closest("tr");
	
	var price = row.find('[name="price"]').val();
	var sq = row.find('[name="sales_quantity"]').val();
	var total = row.find('[id^="s"]');
	
	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
	total.val(sum);
}

//var sq = document.querySelector('[name="sales_quantity"]');
//
//$(document).on('keyup', sq, function(){
//	var pCode = document.querySelector().value;
//	var priceText = document.getElementById("price").innerHTML;
//	var price = parseInt(priceText.replace(/\D/g, ''), 10);
//	console.log('price : ' + price)
//	
//	var sq = $('.sales_quantity').val();
//	console.log('sq: ' + sq);
//	
//	var sum = new Intl.NumberFormat('ko-KR').format(price * sq);
//	console.log(sum);
//	
//	$('.sum').val(sum);
//});