<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			var check = "${ilist[0].item_name}";
			if(check == ""){
				alert("유효하지 않은 페이지입니다.");
				history.back();
			}
		});
$(document).ready(
		function() {
			var check = "${ilist[0].item_quantity}";
			if(check == 0){
				alert("품절된 상품입니다.");
				history.back();
			}
		});
	$(document).ready(
			function() {
				
				var price = $('#price').val();
				var cnt = $('#cnt').val();

				var total_price = price * cnt;
				$('#total_price').text(
						total_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g,
								','));
			});

	$(document).ready(
			function() {
				$('#cnt').click(
						function() {
							var price = $('#price').val();
							var cnt = $('#cnt').val();
							var total_price = price * cnt;
							$('#total_price').text(
									total_price.toString().replace(
											/\B(?=(\d{3})+(?!\d))/g, ','));
						});
			});
	$(document).ready(
			function() {
				$('#cnt').keyup(
						function() {
							var price = $('#price').val();
							var cnt = $('#cnt').val();
							var total_price = price * cnt;
							$('#total_price').text(
									total_price.toString().replace(
											/\B(?=(\d{3})+(?!\d))/g, ','));
						});
			});
	
</script>
<style>
.login-container {
	width: 500px;
	background-color: #fff;
	padding: 70px 20px;
	box-sizing: border-box;
}

.login--title {
	width: 100%;
	text-align: center;
	font-size: 50px;
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

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}

.Menu {
 	display: flex; 
	justify-content: space-evenly;
	background-color: #fff;
	width: 60%;
	align: center;
	margin-left: auto;
	margin-right: auto;
	border:2px solid gray;
}

.Menu a {
	color: black;
	font-size: 32px;
	padding: 5px 5px;
	text-decoration: none;
}

.Fixed {
	position: fixed;
	top: 0px;
	
	
	
	left: 50%;
	transform: translate(-50%, 0); @media ( max-width :480px) { .Menu a {
	font-size: 20px;
}
</style>

<script>
	$(document).ready(function() {
		var Offset = $('.Menu').offset();
		$(window).scroll(function() {
			if ($(document).scrollTop() > Offset.top) {
				$('.Menu').addClass('Fixed');
			} else {
				$('.Menu').removeClass('Fixed');
			}
		});
	});
</script>

</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<div style="width: 60%; margin-left: auto; margin-right: auto;"
		align="center">
		<div style="width: 60%; float: left;">
			<img width="70%" height="70%" src="${ilist[0].item_pictureUrl1}">
		</div>
		<div style="width: 40%; float: left;" align="left">
			<form action="cartinsert.do" method="post">
				<p>[${ilist[0].item_category}]</p>
				<p>
					<strong><h2>${ilist[0].item_name}</h2></strong>
				</p>
				<p style="color:gray">
					${ilist[0].item_content}
				</p>
				<hr>
				<p><fmt:formatNumber value="${ilist[0].item_price}"/>원</p>
				<p style="color:green">
					[구매 혜택 / 적립포인트 (개당) +
					<fmt:formatNumber value="${ilist[0].item_price*0.05}"/>p]
				</p>
				<hr>
				<p>몇인분 :${ilist[0].item_total}인분</p>
				<p>조리시간 : ${ilist[0].item_time}분</p>
				<p>
					수량 : <input type="number" name='item_cnt' id="cnt" value="1" min="1"
						max="${ilist[0].item_quantity}">
				</p>
				<p>
					남은 수량 : ${ilist[0].item_quantity}
				</p>
				<p>
					총 합계 금액 <span id="total_price"></span>원
				</p>
				<input type="hidden" name="item_quantity" value="${ilist[0].item_quantity}">
				<input type="hidden" name="item_num" value="${ilist[0].item_num}">
				<input type="hidden" name="item_pictureUrl1" value="${ilist[0].item_pictureUrl1}">
				<input type="hidden" name="item_name" value="${ilist[0].item_name}">
				<input type="hidden" name="item_price"
					value="${ilist[0].item_price}"> <input type="submit"
					class="form-btn" value="장바구니" id="cart">
			</form>
			<!-- 가격 자동 계산용 -->
			<input type="hidden" value="${ilist[0].item_price}" id="price">
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<div class="Menu">
		<a href="#info">상세정보</a> <a href="#review">후기</a> <a href="#qna">문의</a>
	</div>
	
	<div id="info"
		align="center">
		<br> <br> <br> <br> <br> <img
			src="${ilist[0].item_pictureUrl2}">
	</div>

	<div id="review"
		style="width: 60%; margin-left: auto; margin-right: auto;"
		align="center">
		<br> <br> <br> <br> <br>
		<div align="left">
			<h1>후기</h1>
		</div>
		<hr>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br>

	</div>

	<div id="qna"
		style="width: 60%; margin-left: auto; margin-right: auto;"
		align="center">
		<br> <br> <br> <br> <br> <br>
		<div align="left">
			<h1>문의</h1>
		</div>
		<hr>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br> a<br>
		a<br> a<br> a<br> a<br> a<br> a<br>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>