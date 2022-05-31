<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
.refund table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

.refund th {
	background-color: orange;
	border: 1px solid orange;
}

.refund td {
	border: 1px solid orange;
}

.form-input {
	width: 100%;
	padding: 10px 20px;
	font-size: 20px;
	outline: none;
	margin: 10px 0;
	border: 1px solid #efefef;
	box-sizing: border-box;
}

.form-input:focus {
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
	border: none;
}

.form-input--title {
	width: 100%;
	display: block;
	margin: 5px 0;
	box-sizing: border-box;
}

.form-btn {
	border: 0;
	display: block;
	width: 100%;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}

.pageSize {
	border: 0;
	width: 100px;
	font-size: 16px;
	height: 30px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}

}
.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="refund">
		<div style="width: 60%; margin-left: auto; margin-right: auto;">
			<br>
			<h2>취소/환불 요청 목록</h2>
			<br>
			<hr>
			<br>
			<form action="refundManageOk.do" method="post">
				<table>
					<tr>
						<th></th>
						<th>결제번호</th>
						<th>주문자</th>
						<th>상품명</th>
						<th>상품가격</th>
						<th>상품갯수</th>
						<th>환불/취소여부</th>
					</tr>
					<c:forEach var="list" items="${olist}">
						<tr>
							<td><input type="checkbox"
								value="${list.item_num}/${list.item_cnt}/${list.order_num}"
								name="orderInfo"></td>
							<td>${list.order_num}</td>
							<td>${list.userid}</td>
							<td>${list.item_name}</td>
							<td>${list.item_price}</td>
							<td>${list.item_cnt}</td>
							<td>${list.refund_status}</td>
						</tr>
					</c:forEach>

				</table>
				<input type="submit" class="form-btn" value="승인">
			</form>
		</div>
	</div>


	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>