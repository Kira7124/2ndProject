var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

const canvas = document.querySelector("#canvas");
canvas.width = "500";
canvas.height = "250";
const ctx = canvas.getContext("2d");

canvas.style.margin = "20px";
canvas.style.border = "3px double";
canvas.style.cursor = 'pointer';

let painting = false;

function stopPainting(event) {
  painting = false;
}

function startPainting() {
  painting = true;
}

ctx.lineWidth = 3;
function onMouseMove(event) {
  const x = event.offsetX;
  const y = event.offsetY;
  if (!painting) {
    ctx.beginPath();
    ctx.moveTo(x, y);
  } else {
    ctx.lineTo(x, y);
    ctx.stroke();
  }
}

if (canvas) {
  canvas.addEventListener("mousemove", onMouseMove);
  canvas.addEventListener("mousedown", startPainting);
  canvas.addEventListener("mouseup", stopPainting);
  canvas.addEventListener("mouseleave", stopPainting);
}

function completeOrder(order_code) {
	var imgDataUrl = canvas.toDataURL('image/png');
	var blobBin = atob(imgDataUrl.split(',')[1]); // imgBase64 데이터 디코딩
	
	var array = [];
	for (var i = 0; i < blobBin.length; i++) {
		array.push(blobBin.charCodeAt(i));
	}
	console.log(array);
  
	var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
	var fileName = order_code + ".png";
	var formdata = new FormData();	// formdata 생성
	formdata.append("file", file, fileName);	// file data 추가
	formdata.append("order_code", order_code); // 주문 코드 추가
	
	$.ajax({
		type : "POST",
		url : "/restPlatform/completeOrder",
		data : formdata,
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token)
		},
		cache : false,
		contentType : false, // application/x-www-form-urlencoded; 방지
		processData : false, // data 파라미터 강제 string 변환 방지
		async : false,
		success : function(data) {
			Swal.fire({
				text: "해당 발주 건을 수령처리 하였습니다.",
				confirmButtonColor: "#577D71",
				icon: "success"
			}).then(function(){
				window.close();
				opener.location.href = "/platform/orderList";
			});
		},
		error : function() {
			Swal.fire({
				text: "해당 발주 건 수령처리에 실패하였습니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}

//function completeOrder() {
//	const canvas = document.getElementById('canvas');
//	const imgDataUrl = canvas.toDataURL('image/png');
//	console.log(imgDataUrl);
//	const blobBin = atob(imgDataUrl.split(',')[1]); // imgBase64 데이터 디코딩
//	
//	var array = [];
//    for (var i = 0; i < blobBin.length; i++) {
//        array.push(blobBin.charCodeAt(i));
//    }
//    console.log(array);
//    
//    var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
//    var formdata = new FormData();	// formdata 생성
//    formdata.append("file", file);	// file data 추가
//
//    var signature = document.getElementById('signature');
//    signature.value(formdata);
//}

//function completeOrder() {
//	const canvas = document.getElementById('canvas');
//	const imgDataUrl = canvas.toDataURL('image/png');
//	console.log(imgDataUrl);
//	const blobBin = atob(imgDataUrl.split(',')[1]); // imgBase64 데이터 디코딩
//	
//	var array = [];
//	for (var i = 0; i < blobBin.length; i++) {
//		array.push(blobBin.charCodeAt(i));
//	}
//	console.log(array);
//	
//	var file = new Blob([new Uint8Array(array)], {type: 'image/png'});	// Blob 생성
//	var formdata = new FormData();	// formdata 생성
//	formdata.append("file", file);	// file data 추가
//	
//	$.ajax({
//		type : "POST",
//		url : "/platform/testCompleteOrder",
//		data : formdata,
//		cache : false,
//		contentType : false, // application/x-www-form-urlencoded; 방지
//		processData : false, // data 파라미터 강제 string 변환 방지
//		async : false,
//		success : function(response) {
//			alert(response["result"]);
//		},
//		error : function() {
//			alert("실패~");
//		}
//	});
//}