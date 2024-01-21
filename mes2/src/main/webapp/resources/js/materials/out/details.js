/**
 * 
 */
function goContent(idx){
	
	  $.ajax({
		  url:"testContent.do", // 
		  type:"post",
		  dataType:"json", // Jackson Databind사용해서 json으로 받기
		  data: {"idx": idx }, // 받은 매개변수 전달
		  success:content,
		  error: function(){alert("error");}
	  });
	  
	  
  }
  
  function content(data){ //에이젝스에서 받은 값으로 출력하기 
	 
	  var listHtml ="<div class='list-box'>";


	  
	
	 if(!updateBtn){
	  listHtml +="<button type='button' class='btn btn-secondary' onclick='up("+data.idx+")'>수정</button>"; 
	 }
	  listHtml +="<button type='button' class='btn btn-secondary' onclick='cancle()'>닫기</button>";
	  if(updateBtn){
		listHtml +="<button type='reset' class='btn btn-secondary'>취소</button>";
	  }
	 
	  if(updateBtn){
		  listHtml +="<button type='button' class='btn btn-secondary' onclick='save("+data.idx+")'>저장</button>";
	  }
	  
	  listHtml += "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success'>";
	  listHtml += "<th scope='col'>번호</th>";
	  listHtml += "<th scope='col'>제목</th>";
	  listHtml += "<th scope='col'>등록자</th>";
	  
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  listHtml += "<tbody>";
	  listHtml += "</tbody>"; 
	  
		  listHtml += "<tr>";
		  listHtml += "<td><input type='text'  value='"+data.idx+"' readonly/></td>";
		  if(updateBtn){
			  listHtml += "<td><input type='text' id='titlec' value='"+data.title+"' /></td>";
		  }else{
			  listHtml += "<td><input type='text' id='titlec' value='"+data.title+"' readonly/></td>";
		  }
		  
		 
		  listHtml += "<td><input type='text'  value='"+data.writer+"'readonly/></td>";
		 
		
		  listHtml += "</tr>";
	 
	  listHtml += "</table>";
	 
	//위 표가 들어갈 자리의 아이디값 가져와서 입력하기 
	  $("#view2").html(listHtml);
  }