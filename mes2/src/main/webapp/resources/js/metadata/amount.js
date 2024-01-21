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
    // 데이터 수집
    var productCode = $("#ins_pcode").val();
    var materialCode = $("#ins_mcode").val();
    var amount = $("#amount").val();
    var amountUnit = $("#Unit").html();
    
    

    // 추가 행에서 완제품코드, 원재료 코드, 단위 확인 alert 창
    if (productCode === "완제품코드" || materialCode == "원재료코드") {
                
        Swal.fire({
            text: "드롭박스 옵션을 선택해주세요",
            confirmButtonColor: "#577D71",
            icon: "warning"
         })
         .then(function(){
      	   
         });
        
        return;
    }
    
    $.ajax({
      type: "POST",
      url: "/amount/insertamount",
      data: {
        product_code: productCode,
        material_code: materialCode,
        amount: amount,
        amount_unit: amountUnit,
       
      },
      success: function(response) {
    	  
    	  Swal.fire({
              text: "BOM정보가 추가되었습니다.",
              confirmButtonColor: "#577D71",
              icon: "success"
           })
           .then(function(){
        	   location.reload();
           });
    	
        
      },
      error: function(error) {
        // 오류 처리
        console.error(error);
      }
    });
  }



// 수정 ajax
function submitData2(submitbtn2) {
    var row = $(submitbtn2).closest('tr');
    
    var index = row.find('.b input[name="index"]').val();
    var productCode = row.find('.b select[name="product_code"] option:selected').text();
    var materialCode = row.find('.b select[name="material_code"] option:selected').text();
    var amount = row.find('.b input[name="amount"]').val();
    //var amountUnit = row.find('.b select[name="amount_unit"] option:selected').text();
    
    var data = {
        index: index,
        product_code: productCode,
        material_code: materialCode,
        amount: amount,
        //amount_unit: amountUnit
        
    };
    
 // 추가 행에서 완제품코드, 원재료 코드, 단위 확인 alert 창
    if (productCode === "완제품코드" || materialCode == "원재료코드") {
                
        Swal.fire({
            text: "드롭박스 옵션을 선택해주세요",
            confirmButtonColor: "#577D71",
            icon: "warning"
         })
         .then(function(){
      	   
         });
        
        return;
    }
       
    $.ajax({
        url: '/amount/updateamount', // 서버 측 엔드포인트 경로 지정
        type: 'POST',
        data: data,
        success: function(response) {
        	Swal.fire({
                text: "BOM정보가 수정되었습니다.",
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
	var index = row.find('.b input[name="index"]').val();

    $.ajax({
        url: '/amount/deleteamount',
        type: 'POST',
        data: {
            index: index,
        },
        success: function(response) {
        	Swal.fire({
                text: "BOM정보가 삭제되었습니다.",
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
    window.location.href = '/amount/firstpage';
}




//품목코드 가져오는 ajax -->
function processAjaxData(data) {
	
    var options = '';
    var options = '<option>완제품코드</option>';
    $.each(data, function(index, item) {
        options += '<option>' + item.product_code + '</option>';
    });

    $('#ins_pcode, #upd_pcode').html(options);
}

$.ajax({
    url: '/amount/amount2/product_code',
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        
        console.log(data);
        processAjaxData(data);
    },
    error: function(xhr, status, error) {
        console.error('Error:', status, error);
    }
});

//원재료코드 가져오는 ajax -->
function processAjaxData2(data) {
	
    var options = '';
    var options = '<option>원재료코드</option>';
    $.each(data, function(index, item) {
        options += '<option>' + item.product_code + '</option>';
    });

    $('#ins_mcode, #upd_mcode').html(options);
}

$.ajax({
    url: '/amount/amount2/product_code2',
    method: 'GET',
    dataType: 'json',
    success: function(data) {
        
        console.log(data);
        processAjaxData2(data);
    },
    error: function(xhr, status, error) {
        console.error('Error:', status, error);
    }
});



function selectunit2() {
		
	var materialCode = $("#ins_mcode").val();
	
	$.ajax({
	url: '/amount/amount2/unit',
	method: 'GET',
	//dataType: 'json',
	data: {
		product_code : materialCode,
	},
	success: function(data) {
		
		console.log(data);		
		
		$("#Unit").html(data);
		
	},
	error: function(xhr, status, error) {
		console.error('Error:', status, error);
	}
});			

}






function selectunit3(ths) {
	var row = $(ths).closest("tr");

    var materialCode = row.find('select[name="material_code"]').val();    
    var unit = row.find('#Unit3');    
        console.log(materialCode);

        $.ajax({
            url: '/amount/amount2/updunit',
            method: 'GET',
            data: {
                product_code: materialCode
            },
            success: function (data) {
                console.log(data);
                $(unit).html(data);
            },
            error: function (xhr, status, error) {
                console.error('Error:', status, error);
            }
        });

}


