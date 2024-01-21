<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f8f9fa;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
    color: #007bff;
}

label {
    display: block;
    margin-bottom: 10px;
}

input[type="number"] {
    padding: 8px;
    width: 100%;
    border: 1px solid #ced4da;
    border-radius: 4px;
    margin-top: 5px;
}

#submit {
    background-color: #28a745;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

#submit:hover {
    background-color: #218838;
}

#theme {
    font-size: 24px;
    font-weight: bold;
    color: #007bff;
    margin-bottom: 20px;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
</head>
<body>

	
	<form action="/instructions/result" method="post">
		
		<h2>작업결과</h2>

		<br>
		<label id="isCode">작업코드 : ${instruction.code}</label>		
		<br>
		<label id="mdpCOde">생산품 코드 : ${instruction.mdpCode}</label>
		<br>
		<label id="sopCode">수주번호 : ${instruction.sopCode}</label>
		<br>
		<label id="emp">대표작업자 : ${instruction.empId}</label>
		<br>
		<label id="emp">생산목표량 : ${instruction.targetQuantity}</label>
		<br>
		<br>
		<label id="quantityLabel">양품 수량 입력 <input type="number" name ="quantity" id="quantity"></label>
		<label id="faultLabel">불량품 수량 입력 <input type="number" name ="fault" id="fault" value="0"></label>
		<input type="hidden" name="isCode" value="${instruction.code}">
				
		<input type="submit" value="완료" id="submit">

	</form>


	<script type="text/javascript">
	</script>
</body>
</html>