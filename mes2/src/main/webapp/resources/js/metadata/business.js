//추가 시 주소 api
function sample1_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수  
                var row = $(join_button).closest('tr');
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    //document.getElementById("join_detailAddress").value = extraAddr;
                
                } else {
                    //document.getElementById("join_detailAddress").value = '';
                }
                
                var fullAddr = addr + extraAddr;
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                //document.getElementById('join_postcode').value = data.zonecode;
                document.getElementById("join_address").value = fullAddr;
                row.find('.b input[name="address"]').val(fullAddr);
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("join_address").focus();
                console.log("Full Address:", fullAddr);
                
                
            }
        }).open();
}

//상단 오른쪽 추가 버튼 js
function replaceButton() {
            
	var addbtn = document.getElementById('addbtn');
    var updatebtn = document.getElementById('updatebtn');
            
    addbtn.style.display = 'none';
    updatebtn.style.display = 'none';
            
    var canclebtn = document.getElementById('canclebtn');
    var submitbtn = document.getElementById('submitbtn');
    
    canclebtn.style.display = 'inline-block';
    submitbtn.style.display = 'inline-block';
   
    var row = document.getElementById("insert_hang");
    
    if (row.style.display === "none") {
        row.style.display = "table-row";
    } else {
        row.style.display = "none";
    }
    
            
}



//  상단 오른쪽 수정 버튼 js
function replaceButton2() {
    
    var addbtn = document.getElementById('addbtn');
    var updatebtn = document.getElementById('updatebtn');
    
    addbtn.style.display = 'none';
    updatebtn.style.display = 'none';
    
    var canclebtn = document.getElementById('canclebtn');
    var submitbtn2 = document.getElementById('submitbtn2');
    var submitbtn3 = document.getElementById('submitbtn3');
    
    submitbtn2.style.display = 'inline-block';
    submitbtn3.style.display = 'inline-block';
    canclebtn.style.display = 'inline-block';    
    
    var checkboxes = document.querySelectorAll(".updatecheckbox");
    
    checkboxes.forEach(function(checkbox) {
    	
    	if (checkbox.style.display === "none") {
            checkbox.style.display = "block";
        }
    	else {
        checkbox.style.display = "none";
        }
     	
	    checkbox.addEventListener('change', () => {
	    	      
	    	checkboxes.forEach(otherCheckbox => {
	    	        
		    	if (otherCheckbox !== checkbox) {
		    	otherCheckbox.checked = false;
		    	}
	    	    	  
	    	});
	    })
    
    });
   
}

//  다른체크박스 클릭시 창닫기 js
function a(checkbox) {
    
	var row = checkbox.closest('tr');

    if (row) {
        var classAElements = row.getElementsByClassName('a');
        var classBElements = row.getElementsByClassName('b');

        if (checkbox.checked) {
            // 체크일 때
            for (var i = 0; i < classAElements.length; i++) {
                classAElements[i].style.display = 'none';
            }
            for (var i = 0; i < classBElements.length; i++) {
                classBElements[i].style.display = 'table-cell';
            }
        } else {
            // 체크가 해제일 때
            for (var i = 0; i < classAElements.length; i++) {
                classAElements[i].style.display = 'table-cell';
            }
            for (var i = 0; i < classBElements.length; i++) {
                classBElements[i].style.display = 'none';
            }
        }
    }    
    //?
    var allRows = document.getElementsByTagName('tr');
    
    for (var j = 0; j < allRows.length; j++) {
        
    	if (allRows[j] !== row) {
            var otherClassAElements = allRows[j].getElementsByClassName('a');
            var otherClassBElements = allRows[j].getElementsByClassName('b');

            // class="a"인 열 보이게 설정
            for (var i = 0; i < otherClassAElements.length; i++) {
                otherClassAElements[i].style.display = 'table-cell';
            }

            // class="b"인 열 감추기
            for (var i = 0; i < otherClassBElements.length; i++) {
                otherClassBElements[i].style.display = 'none';
            }
        }
    }   
}




// 추가 ajax
function submitData() {
    // 폼 데이터 수집
    var data = {
        category: $('#typeSelect').val(),
        pw: $('input[name="pw"]').val(),
        name: $('input[name="name"]').val(),
        manager: $('input[name="manager"]').val(),
        address: $('input[name="address"]').val(),
        call: $('input[name="call"]').val(),
        fax: $('input[name="fax"]').val(),
        email: $('input[name="email"]').val(),
        
    };
    
    $.ajax({
        url: '/business/insertbusiness',
        type: 'POST',
        data: data,
        success: function() {
        	Swal.fire({
                text: "거래처정보가 추가되었습니다.",
                confirmButtonColor: "#577D71",
                icon: "success"
             })
             .then(function(){
          	   location.reload();
             });
        },
        error: function(error) {
            console.error('에러 발생:', error);
        }
    });
} 



// 수정 ajax
function submitData2(submitbtn2) {
    
	var row = $(submitbtn2).closest('tr');
	
	var company_code = row.find('.b:eq(0)').text();
    var pw = row.find('.b input[name="pw"]').val();
    var name = row.find('.b input[name="name"]').val();
    var manager = row.find('.b input[name="manager"]').val();
    var address = row.find('.b input[name="address"]').val();
    var call = row.find('.b input[name="call"]').val();
    var fax = row.find('.b input[name="fax"]').val();
    var email = row.find('.b input[name="email"]').val();
    var contractStatus = row.find('.b select[name="upd_status"]').val();
    
    // 추가 행에서 카테고리, 단위 확인 alert 창
    if (contractStatus === "거래상황") {        
    	          
    	Swal.fire({
            text: "거래상황을 다시 확인해주세요.",
            confirmButtonColor: "#577D71",
            icon: "warning"
         })
         .then(function(){
      	   
         });
    	
    	return;
    }
    
    
    var data = {
        
    	company_code: company_code,
        pw: pw,
        name: name,
        manager: manager,
        address: address,
        call: call,
        fax: fax,
        email: email,
        contract_status: contractStatus
    };
    
    $.ajax({
        url: '/business/updatebusiness',
        type: 'POST',
        data: data,
        success: function(response) {
        	
        	Swal.fire({
                text: "거래처정보가 수정되었습니다.",
                confirmButtonColor: "#577D71",
                icon: "success"
             })
             .then(function(){
          	   location.reload();
             });
            
        },
        error: function(error) {
            // 에러 시 처리
            console.error('에러 발생:', error);
        }
    });
}


// 삭제 ajax -->
function submitData3(submitbtn3) {
	
	var row = $(submitbtn3).closest('tr');
    var company_Code = row.find('.b:eq(0)').text();

    $.ajax({
        url: '/business/deletebusiness',
        type: 'POST',
        data: {
            company_code: company_Code,
        },
        success: function(response) {
        	Swal.fire({
                text: "거래처정보가 삭제되었습니다.",
                confirmButtonColor: "#577D71",
                icon: "success"
             })
             .then(function(){
          	   location.reload();
             });
        },
        error: function(error) {
            console.error(error);
        }
    });
	
}


// 취소버튼 js -->
function redirectToFirstPage() {
    window.location.href = '/business/firstpage';
}


//추가할 때 유통업체면 비밀번호를 못치게끔 하는 js
function handleTypeSelect() {
    var typeSelect = document.getElementById("typeSelect");
    var pwInput = document.querySelector('input[name="pw"]');
    

    if (typeSelect.value === "유통업체") {
        // 선택된 값이 "유통업체"인 경우 pwInput을 비활성화하고 내용을 지움
        pwInput.disabled = true;
        pwInput.value = "";
        pwInput.style.display = "none";
        
    } else {
        // 다른 경우 pwInput을 활성화dd
        pwInput.disabled = false;
        pwInput.value = "1234";
        pwInput.style.display = "block";
    }
}


