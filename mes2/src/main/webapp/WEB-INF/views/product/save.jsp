<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

TestPage
<form action="/product/test" method="post">
	
	<input type="submit" value="종료" id="submit">
	<input type="hidden" name="tt" value="hou" >
	<input type="number" name="qcResult">
	
	
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