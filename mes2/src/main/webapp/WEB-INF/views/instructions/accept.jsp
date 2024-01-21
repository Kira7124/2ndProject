<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/production/accept.css">
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
<style>
	.container {
    width: 50%;
    margin: auto;
    padding: 20px;
    background-color: #f8f9fa;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form {
    display: flex;
    flex-direction: column;
}

h3 {
    color: #007bff;
}

input[type="date"] {
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ced4da;
    border-radius: 4px;
}

label {
    display: block;
    margin-bottom: 10px;
}

select {
    padding: 8px;
    width: 100%;
    border: 1px solid #ced4da;
    border-radius: 4px;
}

#salesQuantity, #quantity {
    font-weight: bold;
    margin-bottom: 10px;
}

#submit {
    background-color: #a2c6a8;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

#submit:hover {
    background-color: #577D71;
}
</style>
</head>
<body>

	<div class="container">
		<form action="/instructions/accept" method="post">
			<h3>수주 번호 : ${instructionDTO.sopCode}</h3><br>
		
			<input type="hidden" name="sopCode" value="${instructionDTO.sopCode}">
		
			<span class="search-font">작업 날짜</span>
          	<input id="startDate" type="date" min="2023-12-01" max="2024-12-31" name="dueDate"/>
		
			<label id="selectLineLabel"> 생산 가능한 라인
				<select id="selectLine" name="line">
				</select>
			</label><br>
			
			<span id="salesQuantity">수주물량 : ${instructionDTO.salesQuantity}</span><br>
			<span id="quantity">
			생산 목표량 : 
			<fmt:formatNumber value="${instructionDTO.salesQuantity + (instructionDTO.salesQuantity/10) }" pattern=""/>			
			[수주물량 + 예비물량(10%)]
			
			</span><br>

			<input type="submit" value="작업지시 등록" id="submit">
		</form>
	</div>
	
	<script type="text/javascript">

	const sbutton = document.getElementById("submit")

	function end(){
		opener.document.location.reload();		
		self.close();
	}
	
	function lazyEnd(){
		setTimeout(end, 1000);
	}
	sbutton.addEventListener('click', () => {
		setTimeout(end, 1000);
		
   });
	
	</script>
	
	<script src="/resources/js/instructions/accept.js"></script>
</body>
</html>