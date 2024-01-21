
function goContent(order_code){
       
	  $.ajax({
		  url:"planContent",  
		  type:"post",
		  dataType:"json", 
		  data: {"order_code": order_code }, 
		  success: function(data) {
			  content(data,order_code);
		  },
		  error: function(){alert("error");}
	  });
	  
	  
  }

 function cancle(){
	
		  $("#view2").html("");
	  }
 
 function info(order_code){
	 
	 
	  $.ajax({
		  url:"getOrderInfo", 
		  type:"post",
		  dataType:"json", 
		  data: {"order_code" : order_code}, 
		  success: function (data) {
			  
		      moInfo(data,order_code); 
		    },
		  error: function(){
			  $("#mo-close").trigger('click');
			  Swal.fire({
				    title: "관계자에게 문의하십시오.",
				    icon: "warning"
				}).then((result) => {
				    if (result.isConfirmed) {
				        
				        $("#mo-close").trigger('click');
				    }
				});
				
		  }
	  });
 }
 
 function moInfo(data, order_code){
	 
	 var title = "<div>주문번호 : "+order_code+"</div>";
	 $("#exampleModalLabel").html(title);
	 var listHtml = "<p>&lt;회사정보></p>"
		 
	     listHtml += "<div>회사명 : <input type='text' value='"+data.company_name+"' readonly/></div>";
		 listHtml += "<div>회사코드 : <input type='text' value='"+data.company_code+"' readonly/></div>";
		 listHtml += "<div>회사주소 : <input type='text' value='"+data.company_address+"' readonly/></div>";
		 listHtml += "<div>회사전화번호 : <input type='text' value='"+data.company_call+"' readonly/></div>";
		 listHtml += "<hr>"
		 listHtml += "<p>&lt;담당자정보></p>"
		 listHtml += "<div>담당자id : <input type='text' value='"+data.user_id+"' readonly/></div>";
		 listHtml += "<div>담당자이름 : <input type='text' value='"+data.user_name+"' readonly/></div>";
		 listHtml += "<div>담당자부서 : <input type='text' value='"+data.user_department+"' readonly/></div>";
		 listHtml += "<div>담당자직책: <input type='text' value='"+data.user_position+"' readonly/></div>";
		 listHtml += "<div>담당자부서 : <input type='text' value='"+data.user_auth+"' readonly/></div>";	 
		 
	      
		 $("#shippngPlan-modal").html(listHtml);
 }

  function content(data,order_code){

	  var listHtml = "<div class='content-box'>"
		  listHtml += "<div class='content-container'>"
	      listHtml += "<div class='content-title'>"		  
		  listHtml += "<div>"
		  listHtml += "<p class='list-font' data-bs-toggle='modal' data-bs-target='#shippngPlanModal' onclick='info(\""+order_code+"\")'>주문번호: "+order_code+"</p>";	  
		  listHtml += "</div>"
		  listHtml += " <button type='button' class='btn-close' aria-label='Close' onclick='cancle()'></button>"
		  listHtml += "</div>";
	      listHtml += "<div class='list-box'>";
 	  
		  listHtml += "<table class='table table-hover content-tb'>";
		  listHtml += "<thead>";
		  listHtml += "<tr class='table-success' >";
		  listHtml += "<th style='text-align: center;'>수주번호</th>";
		  listHtml += "<th>제품명</th>";
		  listHtml += "<th>출하계획량</th>";
		  listHtml += "<th>제품진행</th>";
		  listHtml += "<th>처리</th>";
		  listHtml += "<th>출하준비</th>";
		  listHtml += "</tr>";
		  listHtml += "</thead>";
		  
		  listHtml += "<tbody>";
	  
	  $.each(data,function(index,obj){
		  listHtml += "<tr>";		
		  listHtml += "<td>"+obj.sales_code+"</td>";				  
		  listHtml += "<td>"+obj.product_name+"</td>";
		  listHtml += "<td>"+obj.sales_quantity+"</td>";
		  listHtml += "<td>"
		  if(obj.product_status == "waiting"){
		  listHtml += "<div class='gray-circle'/></div>  대기";
		  } else if(obj.product_status == "progressing"){
		  listHtml += "<div class='yellow-circle'/></div>  진행";  
		  }else if(obj.product_status == "complete"){
		  listHtml += "<div class='green-circle'/></div>  완료";  
		  }
		  listHtml += "</td>"
		  listHtml += "<td>"
		  if(obj.processing_reg=="stock"){
			  listHtml += "재고출하"; 
		  }else if(obj.processing_reg=="production"){
		  listHtml += "생산처리";  
		  }else if(obj.processing_reg=="multi"){
		  listHtml += "복합처리";  
		  }else {
			 listHtml += "미정";
		  }
		  listHtml += "</td>"
		  listHtml += "<td>"
		  if(obj.product_status == "waiting" || obj.product_status == "progressing"){
			  listHtml += "<div class='gray-circle'/></div>  대기";    
		  }else if(obj.product_status == "complete"){
		  listHtml += "<div class='green-circle'/></div>  완료";  
		  }
		  listHtml += "</td>"
		  listHtml += "</tr>";
	  });
    

	  listHtml += "</tbody>";
	  listHtml += "</table>";
	 
	
	  $("#view2").html(listHtml);
  }