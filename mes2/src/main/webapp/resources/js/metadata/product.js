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
	
    var productCode = $('input[name="product_code"]').val();
    var name = $('input[name="name"]').val();
    var category = $('#ins_cate option:selected').text();    
    var unit = $('#ins_unit option:selected').text();   
    var cost = $('input[name="cost"]').val();
    var price = $('input[name="price"]').val();   
    
    var formData = new FormData();
	formData.append('product_code', productCode);
	formData.append('name', name);
	formData.append('category', category);
	formData.append('unit', unit);
	formData.append('cost', cost);
	formData.append('price', price);
	
	// 추가 행에서 카테고리, 단위 확인 alert 창
    if (category === "카테고리" || unit == "단위") {        
    	              
    	Swal.fire({
            text: "드롭박스 옵션을 다시 확인해주세요.",
            confirmButtonColor: "#577D71",
            icon: "warning"
         })
         .then(function(){
      	   
         });
    	
    	return;
    }

	var fileInput = $('#file_insert')[0];
	var file = fileInput.files[0];
	formData.append('file', file);
	
    $.ajax({
        url: '/product/insertproduct',
        type: "POST",
        enctype: 'multipart/form-data',
        data: formData,        
        processData: false,
        contentType: false,
        success: function() {
        	
        	Swal.fire({
                text: "품목정보가 추가되었습니다.",
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
	
    var hiddenProductCode = row.find('.b:eq(0)').text();
    var hiddenName = row.find('.b input[name="name"]').val();
    //var hiddenCategory = row.find('.b input[name="category"]').val();
    var hiddenUnit = row.find('.b select[name="upd_unit"] option:selected').text();    
    var hiddenCost = row.find('.b input[name="cost"]').val();
    var hiddenPrice = row.find('.b input[name="price"]').val();
    var hiddenProductionStatus = row.find('.b select[name="upd_status"]').val();

    // 수정 행에서 카테고리, 단위 확인 alert 창
    if (hiddenUnit == "단위" || hiddenProductionStatus == "생산유무") {
                  
        Swal.fire({
            text: "드롭박스 옵션을 다시 확인해주세요.",
            confirmButtonColor: "#577D71",
            icon: "warning"
         })
         .then(function(){
      	   
         });
        return;
    }

    var formData = new FormData();
	formData.append('product_code', hiddenProductCode);
	formData.append('name', hiddenName);
	//formData.append('category', hiddenCategory);
	formData.append('unit', hiddenUnit);
	formData.append('cost', hiddenCost);
	formData.append('price', hiddenPrice);
	formData.append('production_status', hiddenProductionStatus);
		
  	var fileInput = row.find('#file_update')[0];
  	var file = fileInput.files[0];
  	formData.append('file', file);
	
    $.ajax({
        url: '/product/updateproduct',
        type: 'POST',
        data: formData,        
        processData: false,
        contentType: false,
        
        success: function(response) {
            
        	Swal.fire({
                text: "품목정보가 수정되었습니다.",
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


// 삭제 ajax -->
function submitData3(submitbtn3) {
	
	var row = $(submitbtn3).closest('tr');
    var ProductCode = row.find('.b:eq(0)').text();

    $.ajax({
        url: '/product/deleteproduct',
        type: 'POST',
        data: {
            product_code: ProductCode,
        },
        success: function(response) {
        	Swal.fire({
                text: "품목정보가 삭제되었습니다.",
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
    window.location.href = '/product/firstpage';
}


//이미지 미리보기
var sel_file;
 
$(document).ready(function() {
	$("#file_insert, #file_update").on("change", handleImgFileSelect);

});

function handleImgFileSelect(e) {
	var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);
    var reg = /(.*?)\/(jpg|jpeg|png|bmp)$/; 
        
    filesArr.forEach(function(f) {
            
    	if (!f.type.match(reg)) {     
    		alert("확장자는 이미지 확장자만 가능합니다.");   
    		return;
            }
 
    	sel_file = f;
 
        var reader = new FileReader();
        reader.onload = function(e) {
        	
        	$("#img, #img2").attr("src", e.target.result);
        	        	
        }
	    	reader.readAsDataURL(f);
    });
}



// 카테고리 공통코드 가져오는 ajax -->
function processAjaxData(data) {
	
    var options = '';
    var options = '<option>카테고리</option>';
    $.each(data, function(index, item) {
        options += '<option>' + item.code_name + '</option>';
    });

    $('#ins_cate').html(options);
}

$.ajax({
    url: '/product/product2/category',
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

//단위 공통코드 가져오는 ajax -->
function processAjaxData2(data) {
    
    var options = '';
    var options = '<option>단위</option>';
    
    $.each(data, function(index, item) {
        options += '<option >' + item.code_code + '</option>';
    });
    
    
    $('#ins_unit, #upd_unit').html(options);
}

$.ajax({
    url: '/product/product2/unit',
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

