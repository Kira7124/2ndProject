
 $(document).ready(function() {
        var pageFrm = $("#pageForm");

        $(".page-action a").on("click", function(e) {
            
            e.preventDefault(); //a태그기능막기
            var page = $(this).attr("href"); //페이지번호
            pageFrm.find("#page").val(page);
            pageFrm.submit();
        });
       
        
    }); 





function register(){
	 var ckArr = $(".ck");
	 var count = ckArr.filter(":checked").length; 
	 
	 if(count==0){
		 
		 Swal.fire({
			  title: "선택된 항목이 없습니다.",
			  icon: "warning"
			});
	 	
	 	
	 } if(count>=1){
	 		 $.ajax({
	 			  url:"regIdCheck",
	 			  type:"get",
	 			  dataType:"text", 	
	 			  success:moReg,
	 			  error: function(){
	 				  Swal.fire({
	 				  title: "관계자확인 필요",
	 				  text : "관계자에게 문의하세요",
	 				  icon: "error"
	 				});}
	 		  });	 		 
	 	 }
 }
 
 function moReg(data){
	 $("#salesModal").modal("show");
	 $("#salesModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>아이디 : <input type='text' id='user_id' value='"+data+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	 listHtml += "<button type='button' class='btn dark-green-btn' onclick='return regPw(\""+data+"\")'>비밀번호 확인</button>";
	 $("#sales-modal").html(listHtml);
 }
 
 function regPw(user_id){
	 var user_pw = $("#user_pw").val();

	 $.ajax({
		  url: "regPwCheck",
		  type: "post",
		  dataType: "text",
		  data: {"user_pw": user_pw},
		  success: function(data) {
			    moRegPwCheck(data, user_id);
		  }, 
		  error: function() {
			  Swal.fire({
 				  title: "관계자확인 필요",
 				  text : "관계자에게 문의하세요",
 				  icon: "error"
 				});
		  }
		});
	 
 }
 
 function moRegPwCheck(data, user_id){
	 
	 if(data == "true"){
          $("#mo-close").trigger("click");	 
		 Swal.fire({
			  title: "수주등록하시겠습니까?",
			  icon: "question",
			  showCancelButton: true,
			  confirmButtonColor: "#6e9888",
			  cancelButtonColor: "#666666",
			  confirmButtonText: "등록",
			  cancelButtonText: "취소"
			}).then((result) => {
			  if (result.isConfirmed) {
				  registration(user_id);
			  }
			});
		
	 }else if(data == "false"){
		  Swal.fire({
				  title: "비밀번호 오류",
				  icon: "error"
				});
	 }
 }
 
 function registration(user_id){
	 Swal.fire({
		  title: "등록되었습니다!",
		  confirmButtonColor: "#6e9888",
		  icon: "success"
		}).then((result) => {
			 $("#u_id").val(user_id).prop("disabled", false);
			 $('#planListForm').submit();
		});
	
 }

 function reject() {   
	    var ckArr = $(".ck");
	    var count = ckArr.filter(":checked").length;

	    if(count==0){
	    	
	    	 Swal.fire({
				  title: "선택된 항목이 없습니다.",
				  icon: "warning"
				});

	    } else {
	       
	        $.ajax({
	            url: "regIdCheck",
	            type: "get",
	            dataType: "text",
	            success: moRej,
	            error: function () { 
	            	Swal.fire({
	   				  title: "관계자확인 필요",
	   				  text : "관계자에게 문의하세요",
	   				  icon: "error"
	   				}); 
	            	}
	        });
	    }
	}

	function moRej(data) {
		$("#salesModal").modal("show");
		$("#salesModalLabel").html('비밀번호 확인');
	    var listHtml = "<div>아이디 : <input type='text' id='user_id' value='" + data + "' disabled/> </div>";
	    listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	    listHtml += "<button type='button' class='btn dark-green-btn' onclick='return rejPw()'>비밀번호 확인</button>";
	    $("#sales-modal").html(listHtml);
	}

	function rejPw() {
	    var user_pw = $("#user_pw").val();
	    $.ajax({
	        url: "regPwCheck",
	        type: "post",
	        dataType: "text",
	        data: { "user_pw": user_pw },
	        success: moRejPwCheck,
	        error: function () {
	        	Swal.fire({
	   				  title: "관계자확인 필요",
	   				  text : "관계자에게 문의하세요",
	   				  icon: "error"
	   				}); 
	        }
	    });
	}

	function moRejPwCheck(data) {
	    if (data === "true") {	      
	    	  $("#mo-close").trigger("click");	
	    	Swal.fire({
	    		  title: "해당 수주를 거절하시겠습니까?",	    		
	    		  icon: "question",
	    		  showCancelButton: true,
	    		  confirmButtonColor: "#6e9888",
	    		  cancelButtonColor: "#666666",
	    		  confirmButtonText: "확인",
	    		  cancelButtonText: "취소"
	    		}).then((result) => {
	    		  if (result.isConfirmed) {
	    			  refuse();
	    		  }
	    		});
	    	
	    } else if (data === "false") {
	      
	        Swal.fire({
	        	  title: "비밀번호 오류",
	        	  icon: "error"
	        	});
	    }
	}

 function refuse(){
	 var newAction = "rejectSales";
	 $("#planListForm").attr("action", newAction); 
	 $('#planListForm').submit();
 }
 



function checkSearchSub(e){

	if ($("#searchType").val() === "") {
	    Swal.fire({
	        title: "검색타입을 선택하세요.",
	        icon: "warning"
	    }).then((result) => {
	        if (result.isConfirmed) {
	            $("#searchType").focus();
	        }       
	    });
             return false;
	}
	

	
	if($("#searchType").val() == "order_code" && $("#putSearch").val() == ""){
		Swal.fire({
			  title: "검색어를 입력하세요.",			  
			  icon: "warning"
			}).then((result) => {
		        if (result.isConfirmed) {
		            $("#putSearch").focus();
		        }       
		    });
	             return false;
		}
	
	if($("#searchType").val() == "company_name" && $("#putSearch").val() == ""){
		Swal.fire({
			  title: "검색어를 입력하세요.",				  
			  icon: "warning"
			}).then((result) => {
		        if (result.isConfirmed) {
		            $("#putSearch").focus();
		        }       
		    });
	             return false;
		}
}
