/**
 * 
 */
function loadSearchList(typeValue, keyValue){
	
	  $.ajax({
		  url:"testList.do", 
		  type:"post",
		  data: {"type":typeValue,"key":keyValue},
		  dataType:"json",
		  success: list,
		  error: function(){alert("error검색");}
	  });
	  
	
  } 



function searchBtnC(){
	  var typeValue = $("#sfm select[name='type']").val();
      var keyValue = $("#sfm input[name='key']").val();
      loadSearchList(typeValue, keyValue);
  }