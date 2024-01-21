
var radioCnt;
  
  $(document).ready(function() {
	  
		radioCnt =0;
		
		
		

	  
	});
  
  function showStatus() {
  const inputOptions = new Promise((resolve) => {
    setTimeout(() => {
      resolve({
        "Y": "처리완료",
        "N": "처리대기",
        "true": "신규"
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

    const selectedValue = result.value;
    if (selectedValue === "true") {
    	radioCnt++;
        $("#newO").val(selectedValue);
        $("#sfrm").submit();
    } else {
    	radioCnt++;
    	$("#instruct").val(selectedValue);
        $("#sfrm").submit();
    }
  });
}
function checkSearchSub(e){
	if(radioCnt>=1){
		return true;
	}
	
	else if ($("#searchType").val() === "") {
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
	
	else if($("#searchType").val() == "order_code" && $("#putSearch").val() == "") {
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
	
	
	else if($("#searchType").val() == "company_name" && $("#putSearch").val() == "") {
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
