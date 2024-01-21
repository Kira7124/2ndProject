<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.10.1/dist/sweetalert2.all.min.js"></script>
</head>
<body>

	TestPage
	<form action="/instructions/save" method="post">
		
		<span class="search-font">검색시작일</span>
          <input id="startDate" type="date" min="2023-12-01" max="2024-12-31" name="dueDate"/>
		
		<label> 생산라인
			<select id="boundary" name="line">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
			</select>
		</label><br>
		
		<label> 생산품
			<select id="boundary" name="mdpCode">
				<option value="PS10001">PS10001</option>
				<option value="PS10002">PS10002</option>
				<option value="PS10003">PS10003</option>
			</select>
		</label><br>
		
		<label>
			<input type="text" name="soiCode">
		</label><br>
		
		<label> 생산유형
			<select id="boundary" name="type">
				<option value="F">최초생산</option>
				<option value="R">재생산</option>
			</select>
		</label><br>
		
		<label> 생산수량
			<input type="number" name="quantity">
		</label><br>
		
		<label> 상태
			<select id="boundary" name="state">
				<option value="REQUEST">생산요청</option>
			</select>
		</label><br>
				
		<input type="submit" value="종료" id="submit">


	</form>


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
</body>
</html>