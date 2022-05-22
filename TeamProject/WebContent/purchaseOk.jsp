<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3;url=purchaseoklist.do">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<br>
	<div align="center">
		<div style="width: 60%; margin-left: auto; margin-right: auto;">
			<div align="right">
				<span style="color:gray">01 장바구니 <i class="bi-caret-right-fill"></i></span>
				<span style="color:gray"> 02 주문서작성/결제 <i class="bi-caret-right-fill"></i></span>
				<span style="color:black"><strong> 03 주문완료 </strong></span>
			</div>
			<hr>
			<br>
			<div style="font-size:200px;color:orange"><i class="bi-bag-check"></i></div>
			<div style="font-size:30px;color:gray">결제가 정상적으로 완료되었습니다.<br>
			잠시 후 결제 내역페이지로 이동합니다.
			</div>
		</div>
	</div>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
