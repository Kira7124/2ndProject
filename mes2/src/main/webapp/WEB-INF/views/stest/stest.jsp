<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

				<select id="boundary" name="searchType">
					<option value="">-- 검색선택 --</option>
					<option value="isCode">작업지시코드</option>
					<option value="soiCode">수주번호</option>
					<option value="mdpCode">제품번호</option>
				</select>




								<c:forEach var="item" items="${productionLineList}">
									<tr>
										<td scope="row"><input type="checkbox" class="ck" /></td>
										<td>${item.index}</td>
										<td>${item.line}</td>
										<td>${item.isCode}</td>
										<td>${item.startDate}</td>
										<td>${item.endDate}</td>
										<td>${item.status}</td>
									</tr>
								</c:forEach>
</body>
</html>