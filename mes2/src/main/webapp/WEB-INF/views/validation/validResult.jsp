<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Document</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

<link rel="stylesheet" href="/resources/css/production/instructionList.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>

	<!-- 검색창 -->
	<div class="container">
		<section class="section1">
			<form class="row g-3">
				<div class="col-auto">
				
				
					<label for="inputPassword2" class="form-label">불량품 개수</label>
					<input type="number" class="form-control" id="qcFailed"
						placeholder="불량품 개수">
						
					<label for="inputPassword2" class="form-label">양품 개수</label>
					<input type="number" class="form-control" id="qcQuantity" readonly="readonly">
						
				<div class="col-auto">
					<button type="submit" class="btn btn-primary mb-3"> 등록하기</button>
				</div>
				</div>
				
			</form>
		</section>

		<div id="bottomContent"></div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous">
	</script>
	<script src="/resources/js/platform/orderList.js"></script>

	<script type="text/javascript">
	var isCode = '${item.code}'
	
    function openIsSave(){
        window.open("/instructions/save","save","width=800px, height=640px")
    }
/* 	
    function openIsResult(){
        window.open("/instructions/result?isCode"+isCode,"result","width=800px, height=640px")
    }
    */ 

	
	function validateSearchForm() {
        var searchType = document.getElementById("boundary").value;
        
        if (searchType === "") {
            alert("좋은말로 할 때 검색 타입을 선택하세요.");
            return false; 
        }
        
        return true; 
    }
    
    var searchType = '${searchType}'; 
    var searchState = '${searchState}'; 
    // JavaScript를 통해 동적으로 선택된 값을 설정
    document.getElementById('boundary').value = searchType;
    document.getElementById('state').value = searchState;
		
	
	</script>
	<script src="/resources/js/instructions/instructionList.js"></script>

</body>
</html>