/**
 * 
 */

  var updateBtn = false;
 
 $(document).ready(function(){
	  loadList();
  });


  function loadList(){
	  
	  $.ajax({
		  url:"testList.do", //목록 담아오기 (리스트 DTO랑 연결)
		  type:"get",
		  dataType:"json",
		  success: list,
		  error: function(){alert("error");}
	  });
	  
	  
  } 


 function list(data){
	 // loadList()에서 가져온 데이터로 리스트만들기
	 
	  var listHtml = "<table class='table table-hover'>";
	  listHtml += "<thead>";
	  listHtml += "<tr class='table-success'>";
	  listHtml += "<th></th>";
	  listHtml += "<th scope='col'>번호</th>";
	  listHtml += "<th scope='col'>제목</th>";
	  listHtml += "<th scope='col'>등록자</th>";
      
	  listHtml += "</tr>";
	  listHtml += "</thead>";
	  listHtml += "<tbody>";
	  

	// 반복문 돌면서 가져온 데이터 넣어주기 obj.변수명으로 입력하면 된다. 
	  $.each(data,function(index,obj){
		  listHtml += "<tr>";
		  listHtml += "<td scope='row'><input type='checkbox' class='ck' name='idx' value='"+obj.idx+"' id='"+obj.idx+"'/></td>";
		  listHtml += "<td>"+obj.idx+"</td>";
	// 상세보기 클릭하면 goContetn함수 호출 
	// 매개변수로 고유값을 넣어주면 된다.(obj.idx) 		  
listHtml += "<td><a href='javascript:goContent("+obj.idx+")'>" + obj.title + "</a></td>";
		  listHtml += "<td>"+obj.writer+"</td>";
		  
		  listHtml += "</tr>";
	  });
	  listHtml += "</tbody>"; 
	  listHtml += "</table>";
	 
	// 위 리스트가 들어갈 곳의 아이디를 가져와서 넣어주기 
	  
	  $("#view").html(listHtml);
  } 