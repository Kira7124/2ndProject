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