var selectedOrder; 
var radioCnt;
$(document).ready(function() {
	radioCnt =0;

  
  $('.ck').on('change', function() {
   
    selectedOrder = $('input[name="order_code"]:checked').val();

 
  });

  
});

function showStatus() {
	radioCnt++;
	  const inputOptions = new Promise((resolve) => {
	    setTimeout(() => {
	      resolve({
	        "plan": "계획",
	        "waiting": "대기",
	        "instruction": "지시가능"
	      });
	    }, 1000);
	  });

	  Swal.fire({
	    title: "진행현황을 선택하세요.",
	    input: "radio",
	    inputOptions,
	    inputValidator: (value) => {
	      if (!value) {
	        return "아무것도 선택하지 않았습니다.";
	      }
	    }
	  }).then((result) => {
	    if (!result.isConfirmed) {
	      return;
	    }

	    const shipStatus = result.value;
	    if (shipStatus) {
	      $("#shipSta").val(shipStatus);
	      $("#sfrm").submit();
	    }
	  });
	}



 function reg(order_code){
	 Swal.fire({
		  title: "출하지시등록",
		  text: "주문번호 "+order_code+"의 출하지시등록을 진행하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#6e9888",
		  cancelButtonColor: "#666666",
		  confirmButtonText: "진행"
		}).then((result) => {
		  if (result.isConfirmed) {
			  $.ajax({
	 			  url:"updateIdCheck", 
	 			  type:"post",
	 			  dataType:"json",
	 			  data: {"order_code":order_code},
	 			  success: function(data) {
	 				
	 				 $('#shippngPlanModal').modal('show');

	 				    moReg(data,order_code);
	 			  }, 
	 			  error: function(){
	 				  Swal.fire({
					  title: "관계자에게 문의하세요",				
					  icon: "warning"
					});}
	 		  });
		  }
		});
 }
 

 function moReg(data,order_code){
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='reg_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='reg_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='reg_pw'/></div>"
	 listHtml += "<button type='button' class='btn dark-green-btn' onclick='return regPw(\"" + order_code + "\")'>비밀번호 확인</button>";

		 $("#shippngPlan-modal").html(listHtml);
 }
 
 function regPw(order_code){
	 var user_id = $("#reg_id").val();
	 var user_pw = $("#reg_pw").val();
	 $.ajax({
		  url: "updatePwCheck",
		  type:"post",
		  dataType: "json",
		  data: {"user_pw": user_pw, "order_code":order_code, "user_id": user_id},
		  success: function(data) {
			    register(data,order_code);
		  }, 
		  error: function() {
			  Swal.fire({
				  title: "관계자에게 문의하세요",				
				  icon: "warning"
				});
		  }
		});
	 
 }
 
 function register(data,order_code){
	 $("#mo-close").trigger('click');
	 
	 var schedule = new Date(data.scheduled_date);
	 var options = { year: 'numeric', month: '2-digit', day: '2-digit' };
	 var date = schedule.toLocaleDateString('ko-KR', options).replace(/\./g, '-').slice(0, -1);
	 
	 if(data.scheduled_date == null ){
		 Swal.fire({
			  title: "출하예정일 등록필요",
			  text: "의 출하예정일이 등록되지 않았습니다. 출하예정일을 등록해주세요",
			  icon: "warning"
			});
	 }
	 else{
	 Swal.fire({
		  title: "출하지시등록",
		  text: order_code+" 의 출하예정일은"+ date+"입니다. 출하지시등록을 진행하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#6e9888",
		  cancelButtonColor: "#666666",
		  confirmButtonText: "등록"
		}).then((result) => {
		  if (result.isConfirmed) {
			  
			  $.ajax({
				  url:"regShipping", 
				  type:"post",
				  dataType:"text",
				  data: {"order_code":order_code},
				  success: function(data) {
					 Swal.fire({
					    title: "지시완료",
					    text: "주문번호 " + data + "의 출하지시가 완료되었습니다.",
					    icon: "success"
					}).then(() => {
						$("#pageForm").submit();
						
					});

					
				  }, 
				  error: function(){
					 Swal.fire({
						  title: "등록오류",
						  text: "등록오류 발생, 관계자에게 문의하세요.",
						  icon: "warning"
						});
				  }
			  });

		  }
		});
	 }
 }
 


 function update(){
	 var ckArr = $(".ck");
	 var count = ckArr.filter(":checked").length; 
	 
	 if(count==0){	 	
	 	
		 Swal.fire({
			    title: "선택된 항목이 없습니다.",
			    icon: "warning"
			}).then(() => {
				
			    $("#mo-close").trigger("click");
			});
	 	
	 }
	 	 if(count==1){
	 		 $.ajax({
	 			  url:"updateIdCheck", 
	 			  type:"get",
	 			  dataType:"json",
	 			  data: {"order_code":selectedOrder},
	 			  success: function(data) {
	 				
	 				moUpdate(data);
	 		      },
	 			
	 			  error: function(){
	 				 Swal.fire({
						  title: "관계자에게 문의하세요",
						  icon: "warning"
						});
	 			  }
	 		  });
	 		 
	 	 }
	 
 }
 

 
 
 function moUpdate(data){
	
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='user_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='user_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	 listHtml += "<button type='button' class='btn dark-green-btn' onclick='return updatePw()'>비밀번호 확인</button>";
		 $("#shippngPlan-modal").html(listHtml);
 }
 
 function updatePw(){
	 var user_id = $("#user_id").val();
	 var user_pw = $("#user_pw").val();
	 $.ajax({
		  url: "updatePwCheck",
		  type:"post",
		  dataType: "json",
		  data: {"user_pw": user_pw, "order_code":selectedOrder, "user_id": user_id},
		  success: function(data) {
			    moUpdateCheck(data);
		  }, 
		  error: function() {
				 Swal.fire({
					  title: "관계자에게 문의하세요",
					  icon: "warning"
					});
		  }
		});
	 
 }
 
 function moUpdateCheck(data){
	 if(data.check == "true"){	
		 $("#mo-close").trigger('click');
		 
		 // 시작일 설정 (신청일 3주후)
		 	var requestDate = new Date(data.request_date);
		 	
		 	var minDate = new Date(requestDate);
		    minDate.setDate(requestDate.getDate());
		   
		    
		// 마지막일 설정 (납품요청일 4일전)    
		    var orderDate = new Date(data.order_date);
            
		    var maxDate = new Date(orderDate);
		    maxDate.setDate(orderDate.getDate() - 3);
		   
		    
		 Swal.fire({
			  title: "출하예정일을 입력하세요.",
			  input: "date",
			  didOpen: () => {
		            // date input의 max 속성 설정
		            const input = $('input[type="date"]');
		            
		            // maxDate를 'YYYY-MM-DD' 형식의 문자열로 변환하여 설정
		            input.attr('max', maxDate.toISOString().split('T')[0]);
		            
		         // date input의 min 속성 설정
		            input.attr('min', minDate.toISOString().split('T')[0]);
		        }
		    }).then((result) => {
			  if (result.value) {
			    const scheduled_date = result.value; 
			    
			    $.ajax({
					  url: "checkSchedule",
					  type: "post",
					  dataType: "json",
					  data: {"scheduled_date": scheduled_date},
					  success: function(data) {
						  scheduleCheck(data,scheduled_date);
					  },
					  error: function() {
							 Swal.fire({
								  title: "관계자에게 문의하세요",
								  icon: "warning"
								});
					  }
					});
			  }
			});
			
			
	 }else if(data.check == "false"){
		 Swal.fire({
			  title: "비밀번호 오류",
			  text: "담당자 비밀번호를 확인하세요.",
			  icon: "error"
			});
	 }
 }
 
 function scheduleCheck(data,scheduled_date){
	 Swal.fire({
		  title: "출하예정일을 수정하시겠습니까?",
		  text: "현재 "+scheduled_date+" 에 예정된 출하건은 "+data+"개 입니다. 해당일에 추가하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#6e9888",
		  cancelButtonColor: "#666666",
		  confirmButtonText: "확인"
		}).then((result) => {
		  if (result.isConfirmed) {
			  $.ajax({
				  url: "updateSchedule",
				  dataType: "text",
				  type: "post",
				  data: {"scheduled_date": scheduled_date, "order_code":selectedOrder},
				  success: scheduledUpdateSuccess,
				  error: function() {
						 Swal.fire({
							  title: "관계자에게 문의하세요",
							  icon: "warning"
							});
				  }
				});
		  }
		});
 }
 
function scheduledUpdateSuccess(data){
	Swal.fire({
	    title: "수정완료",
	    text: "주문번호 " + data + "의 출하예정일이 변경되었습니다.",
	    icon: "success"
	}).then(() => {
		
		$("#pageForm").submit();

	});
}


function checkSearchSub(e){
	if(radioCnt>=1){
		return true;
	}

	if($("#searchType").val() === ""){
		 Swal.fire({
		        title: "검색어를 입력하세요.",
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
		        title: "검색타입을 선택하세요.",
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
	        title: "검색타입을 선택하세요.",
	        icon: "warning"
	    }).then((result) => {
	        if (result.isConfirmed) {
	            $("#putSearch").focus();
	        }       
	    });
             return false;
	}
	
	


	
	
}
