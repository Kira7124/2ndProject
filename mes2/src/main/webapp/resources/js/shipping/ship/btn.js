var selectedOrder; 
var radioCnt;
$(document).ready(function() {
  
	radioCnt =0;
	$('.waite-btn').each(function(index, element) {
		  var shipDateValue = $(element).closest('tr').find('.ship-date').text();
		  var shipDate = moment(shipDateValue, 'YYYY-MM-DD');
		  var today = moment();

	  if (shipDate.isSame(today, 'day')) {
		    // 오늘과 일치하는 경우 처리할 작업 수행
		    // 대기 버튼 클래스 변경
		   $(element).removeClass('btn-secondary').addClass('btn-warning');

		    
		  }
		  
		});



  
  $('.ck').on('change', function() {
   // 라디오버튼에 저장된 주문번호 가져오기
    selectedOrder = $('input[name="order_code"]:checked').val();

  });
  
  
  

  
});
 
function showStatus() {
	radioCnt++;
	  const inputOptions = new Promise((resolve) => {
	    setTimeout(() => {
	      resolve({
	        "waiting": "출하대기",
	        "shipping": "배송",
	        "complete": "출하완료"
	      });
	    }, 1000);
	  });

	  Swal.fire({
	    title: "진행현황을 선택하세요.",
	    input: "radio",
	    inputOptions,
	    inputValidator: (value) => {
	      if (!value) {
	        return "선택한 항목이 없습니다. ";
	      }
	    }
	  }).then((result) => {
	    if (!result.isConfirmed) {
	      return;
	    }

	    const progressStatus = result.value;
	    if (progressStatus) {
	      $("#progressSta").val(progressStatus);
	      $("#sfrm").submit();
	    }
	  });
	}

function complete(order_code,ship_date){
	
	 var shipDate = moment(ship_date, "ddd MMM DD HH:mm:ss Z YYYY").format("YYYY-MM-DD");
	    var today = moment(); 
	    if (moment(shipDate).isAfter(today)) {
	
	        Swal.fire({
			    title: "대기상태",
			    text: "배송을 지시할 수 없습니다.",
			    icon: "error"
			}).then(() => {
				$("#mo-close").trigger('click');
				
			});
	        return false;
	    } 
	  else{ 
		  
  

	 Swal.fire({
		  title: "배송처리",
		  text: "주문번호 "+order_code+"의 배송처리하시겠습니까?",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#6e9888",
		  cancelButtonColor: "#666666",
		  confirmButtonText: "확인"
		}).then((result) => {
		
		  if (result.isConfirmed) {
			  $.ajax({
	 			  url:"updateIdCheck", 
	 			  type:"post",
	 			  dataType:"json",
	 			  data: {"order_code":order_code},
	 			  success: function(data) {
	 				
	 				    deliverReg(data,order_code);
	 			  }, 
	 			  error: function(){
	 				 Swal.fire({
	 					  title: "관계자에게 문의하세요",				
	 					  icon: "warning"
	 					});
	 			  }
	 		  });
			 
		  }else{
			moClear();	 		 
		    $("#mo-close").trigger('click');
		  }
		});
}
}

function deliverReg(data,order_code){
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='deliver_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='deliver_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='deliver_pw'/></div>"
	 listHtml += "<button type='button' class='btn dark-green-btn' onclick='return deliverPw(\"" + order_code + "\")'>비밀번호 확인</button>";

		 $("#shippng-modal").html(listHtml);
}

function deliverPw(order_code){
	 var user_id = $("#deliver_id").val();
	 var user_pw = $("#deliver_pw").val();
	 $.ajax({
		  url: "updatePwCheck",
		  type:"post",
		  dataType: "json",
		  data: {"user_pw": user_pw, "order_code":order_code, "user_id": user_id},
		  success: function(data) {
			    deliverCheck(data,order_code);
		  }, 
		  error: function() {
			  Swal.fire({
				  title: "관계자에게 문의하세요",				
				  icon: "warning"
				});
		  }
		});
	 
}

function deliverCheck(data,order_code){
	 
	 
	 if(data.check=="true"){
		
		 $("#mo-close").trigger('click');
		 $.ajax({
			  url:"outComplete", 
			  type: "POST",
			  dataType:"text",
			  data: {"order_code": order_code},
			  success: function(data) {	
				 
				  Swal.fire({
					    title: "배송처리 되었습니다.",
					    text: data,
					    icon: "success"
					}).then(() => {
						
						$("#pageForm").submit();
					});
			  }, 
			  error: function(){
				  Swal.fire({
					  title: "관계자에게 문의하세요",				
					  icon: "warning"
					});
			  }
		  });
	 }else{
		 Swal.fire({
			  title: "비밀번호 오류",				
			  icon: "warning"
			});
		 
	 }
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
	 				
	 				 $('#exampleModal').modal('show');

	 				    moReg(data,order_code);
	 			  }, 
	 			  error: function(){
	 				 Swal.fire({
	 					  title: "관계자에게 문의하세요",				
	 					  icon: "warning"
	 					});
	 			  }
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

		 $("#shippng-modal").html(listHtml);
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
	 
	// 선택된 라디오 버튼의 값을 가져옴
	 var selectedOrderCode = $("input[name='order_code']:checked").val();

	 // 선택된 행에서 클래스가 'pf'인 td 엘리먼트의 텍스트를 가져옴
	 var pfText = $("input[name='order_code'][value='" + selectedOrderCode + "']").closest('tr').find('.pf').text().trim();

	 // 텍스트가 '대기'인 경우 처리
	 if (pfText === '완료') {
	     Swal.fire({
			  title: "수정 불가능",
			  text:"이미 출하처리되었습니다.",
			  icon: "error"
			}).then((result) => {
	 			  if (result.isConfirmed) {
	 				
	 				 
	 				$("#mo-close").trigger('click');
	 		 
	 	 }
	 });
	     return false;
	 } 
	 if (pfText === '배송') {
	     // 처리 로직 추가
	    
	     Swal.fire({
			  title: "수정 불가능",
			  text:"배송 중입니다.",
			  icon: "error"
			}).then((result) => {
	 			  if (result.isConfirmed) {
	 				 $("#mo-close").trigger('click');
	 				
	 		 
	 	 }
	 });
	     return false;
	 }
	 
	 
	 
	 if(count==0){	 	
		 Swal.fire({
			  title: "선택된 항목이 없습니다.",
			  icon: "warning"
			}).then((result) => {
	 			  if (result.isConfirmed) {
	 				 $("#mo-close").trigger('click');
	 		 
	 	 }
	 });
	 	
	 }else{
	 	 if(count==1){
	 		Swal.fire({
	 			  title: "출하일정을 수정하시겠습니까?",
	 			  text: "이미 예약출하가 지시된 상태입니다. 출하일정을 수정을 진행하시겠습니까?",
	 			  icon: "warning",
	 			  showCancelButton: true,
	 			  confirmButtonColor: "#6e9888",
	 			  cancelButtonColor: "#666666",
	 			  confirmButtonText: "진행"
	 			}).then((result) => {
	 			  if (result.isConfirmed) {
	 				
	 				  $.ajax({
	 					 url:"updateIdCheck", 
			 			  type:"get",
			 			  dataType:"json",
			 			  data: {"order_code":selectedOrder},
			 			  success:moUpdate,
			 			  error: function(){
			 				 Swal.fire({
			 					  title: "수정오류",
			 					  text: "해당 주문 건의 관리자에게 문의하십시오.",
			 					  icon: "warning"
			 					});

	 				  }	 
	 				 });
	 		 
	 	 }else {
		    moClear();	 		 
	 		$("#mo-close").trigger('click');
	 
	 
	 		 
	        }
	 });
 }
 
	 }
 
 }
 
function moClear(){
 var listHtml = "";
 $("#shippng-modal").html(listHtml);
}

 
 function moUpdate(data){
	
	 $("#exampleModalLabel").html('비밀번호 확인');
	 var listHtml = "<div>담당자 아이디 : <input type='text' id='user_id' value='"+data.user_id+"' disabled/> </div>";
	 listHtml += "<div>담당자 이름 : <input type='text' id='user_name' value='"+data.user_name+"' disabled/> </div>";
	 listHtml += "<div>비밀번호: <input type='password' id='user_pw'/></div>"
	 listHtml += "<button type='button' class='btn dark-green-btn' onclick='return updatePw()'>비밀번호 확인</button>";
		 $("#shippng-modal").html(listHtml);
		
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
	 var ship_date = data.ship_date;
	
	 
	 if(data.check == "true"){	
		 $("#mo-close").trigger('click');
		 // 오늘 날짜
		 var today = new Date();

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
						  scheduleCheck(data,scheduled_date,ship_date);
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
 
 function scheduleCheck(data,scheduled_date,ship_date){
	 var date = new Date(ship_date);
	 var sd =date.toISOString().split('T')[0];
	 Swal.fire({
		  title: "예약출하 일정을 변경하시겠습니까?",
		  text: "해당 주문건의 예약출하일정은 "+sd+" 입니다.",
		  icon: "info",
		  showCancelButton: true,
		  cancelButtonText: "취소",
		  confirmButtonColor: "#6e9888",
		  cancelButtonColor: "#666666",
		  confirmButtonText: "확인"
		}).then((result) => {
		  if (result.isConfirmed) {
				 Swal.fire({
					  title: "예약출하 일정을 변경",
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
							  url: "changeShipSchedule",
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
	else if($("#searchType").val() === ""){
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
	
	else if($("#searchType").val() == "ship_code" && $("#putSearch").val() == ""){
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
	
	else if($("#searchType").val() == "company_name" && $("#putSearch").val() == ""){
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