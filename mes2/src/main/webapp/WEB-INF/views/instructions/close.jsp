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
<form action="/product/test" onsubmit="lazyEnd();" method="post">
	<input type="hidden" value="hou" name="tt">
	<input type="submit" value="종료" id="submit">
</form>


<script type="text/javascript">


		opener.document.location.reload();		
		self.close();

	
</script>
</body>
</html>