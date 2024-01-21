var header = $("meta[name='_csrf_header']").attr("content");
var token = $("meta[name='_csrf']").attr("content");

let arrow = document.querySelectorAll(".fa-chevron-down"); /* 드롭다운할 때 여기도 아이콘명 변경해야함 */
for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e)=>{
 let arrowParent = e.target.parentElement.parentElement; //selecting main parent of arrow
 arrowParent.classList.toggle("showMenu");
  });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".fa-bars"); /* 햄버거클릭할 때 여기도 아이콘명 변경해야함 */
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", ()=>{
  sidebar.classList.toggle("close");
});

var pw = document.querySelector("#pw");
var nowPw = document.querySelector("#nowPw");
var newPw = document.querySelector("#newPw");
var checkPw = document.querySelector("#checkPw");
var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,12}$/;

// 비밀번호 유효성 검사
$("#newPw").keyup(
	function() {
		var newPwVal = newPw.value;
		if(!reg.test(newPwVal)) {
			$(".explainNewPw").css('color', 'red');
		} else {
			$(".explainNewPw").css('color', 'blue');
		}
	}
);

// 비밀번호 일치 확인
$("#checkPw").keyup(
		function() {
			var newPwVal = newPw.value;
			var checkPwVal = checkPw.value;
			if(newPwVal != checkPwVal) {
				$(".explainCheckPw").css('color', 'red');
			} else {
				$(".explainCheckPw").css('color', 'blue');
			}
		}
);

// 비밀번호 수정
function modifyPw() {
	var pwVal = pw.value; // 입력한 현재 비밀번호
	var nowPwVal = nowPw.value; // 실제 현재 비밀번호
	var newPwVal = newPw.value; // 수정 비밀번호
	var checkPwVal = checkPw.value; // 비밀번호 확인
	
	if(pwVal != nowPwVal) {
		Swal.fire({
			text: "현재 비밀번호가 틀립니다",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
	if(!reg.test(newPwVal) || newPwVal != checkPwVal) {
		Swal.fire({
			text: "수정 비밀번호를 확인하세요",
			confirmButtonColor: "#577D71",
			icon: "warning"
		});
		return false;
	}
	
	$.ajax({
		url : "/platform/modifyPw",
		method : "post",
		data : JSON.stringify({
					"pw" : pwVal,
					"checkPw" : checkPwVal
				}),
		contentType : 'application/json; charset=utf-8',
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token)
		},
		async: false,
		success : function(){
			Swal.fire({
				text: "비밀번호를 수정했습니다. 다시 로그인해주세요",
				confirmButtonColor: "#577D71",
				icon: "success"
			})
			.then(function(){
				location.href = "/platform/logout";
			});
		},
		error : function(){
			Swal.fire({
				text: "비밀번호 수정에 실패했습니다.",
				confirmButtonColor: "#577D71",
				icon: "error"
			});
		}
	});
}