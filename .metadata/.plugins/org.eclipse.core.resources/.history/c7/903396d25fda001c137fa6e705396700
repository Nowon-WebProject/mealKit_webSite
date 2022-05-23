<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/member.js"></script>
<style type="text/css">
.info table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

.info th {
	background-color: orange;
	border: 1px solid orange;
}

.info td {
	border: 1px solid orange;
}
</style>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<div align="center">
	<div class="info">
<%if ((int) session.getAttribute("addrcnt") != 0){  %>
	<div style="width: 60%; margin-left: auto; margin-right: auto;">
	<hr>
	<h2>최근 배송지</h2>
	<table>
		<tr>
			<th>받으실 분</th>
			<th>정보</th>
			<th></th>
		</tr>
		<c:forEach var="addr" items="${alist}">
			<tr>
				<td>${addr.deli_name}</td>
				<td align="left">주소 : ${addr.deli_addr}<br>
				전화번호 : ${addr.deli_phone}<br>
				배송메세지 : ${addr.deli_msg}<br>
				공동현관 비밀번호 : ${addr.deli_pwd}
				</td>
				<td>
					<form action="addrdelete.do" method="post">
						<input type="hidden" name="deli_addr_seq" value="${addr.deli_addr_seq}">
						<input type="submit" value="삭제">
					</form>
				</td>

			</tr>
		</c:forEach>
	</table>
	</div>
		<%}else{ %>
			<div style="font-size:200px;color:orange"><i class="bi-geo-alt-fill"></i></div>
			<div style="font-size:30px;color:gray">최근 배송지가 없습니다.</div>
	<%
		}
	%>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>