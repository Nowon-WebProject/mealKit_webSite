<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/ui/nav.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%
		
	%>

	<div id="wrap" align="center">

		<h2>관리자 페이지</h2>
		<br>
		<br>
		<button type="button" class="btn btn-primary active" id="btn" color="orange"
			onclick="document.location.href='memberSearch.jsp'">회원 관리</button>
	</div>
	<br>
	<br>
	<jsp:include page="/ui/footer.jsp"></jsp:include>

</body>
</html>