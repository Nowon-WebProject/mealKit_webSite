<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String addr = (String)session.getAttribute("addr");

String postcode = addr.substring(0,5);
String addr1 = addr.substring(6,addr.lastIndexOf(","));
String addr2 = addr.substring(addr.lastIndexOf(",")+2);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script type="text/javascript" src="js/member.js"></script>
<script type="text/javascript">
function setDisplay(){
    if($('input:radio[id=deli2]').is(':checked')){
        $('#divId').hide();
	      $("input:text[name=delitext]").attr("value","");
    }else{
        $('#divId').show();
    }
}

$(document).ready(function(){
	  $("input:radio[name=addrCheck]").click(function(){
	    if($("input[name=addrCheck]:checked").val() == "1"){
	      $("input:text[name=deli_name]").attr("value","");
	      $("input:text[name=deli_name]").attr("readonly",false);	      
	      $("input:text[name=deli_postcode]").attr("value","");
	      $("input:text[name=deli_postcode]").attr("readonly",false);	      
	      $("input:text[name=deli_addr1]").attr("value","");
	      $("input:text[name=deli_addr1]").attr("readonly",false);	      
	      $("input:text[name=deli_addr2]").attr("value","");
	      $("input:text[name=deli_addr2]").attr("readonly",false);	      
	      $("input:text[name=deli_phone]").attr("value","");
	      $("input:text[name=deli_phone]").attr("readonly",false);	      
	    	
	    }else if($("input[name=addrCheck]:checked").val() == "0"){
	      $("input:text[name=deli_name]").attr("value","<%=session.getAttribute("name")%>");
	      $("input:text[name=deli_name]").attr("readonly",true);
	      $("input:text[name=deli_postcode]").attr("value","000-000");
	      $("input:text[name=deli_postcode]").attr("readonly",true);
	      $("input:text[name=deli_addr1]").attr("value","<%=session.getAttribute("addr")%>");
	      $("input:text[name=deli_addr1]").attr("readonly",true);
	      $("input:text[name=deli_addr2]").attr("value","<%=session.getAttribute("addr")%>");
	      $("input:text[name=deli_addr2]").attr("readonly",true);
	      $("input:text[name=deli_phone]").attr("value","<%=session.getAttribute("phone")%>");
	      $("input:text[name=deli_phone]").attr("readonly",true);
	    }
	  });
	});
	
// 페이지 로드시 사용할 포인트, 최종금액 표시되게끔. 이게 없으면 빈칸으로 나타나고, 
// 아래에 있는 keyup 이벤트가 활성화돼야 그때 값이 들어감
$( document ).ready( function() {
	var a = $( '#a' ).val();
    var b = $( '#usePoint' ).val();
    var ab = a - b + 3000;
    $( '#ab' ).text( ab );
    $( '#usePointResult' ).text( b );
    } );
 
$( document ).ready( function() {
    $( '#a, #usePoint' ).keyup ( function() {
      var a = $( '#a' ).val();
      var b = $( '#usePoint' ).val();
      var ab = (a - b + 3000);
      
      $( '#ab' ).text( ab );
      $( '#usePointResult' ).text( b );
      
    } );
  } );
  
</script>
<style>
/* number값 화살표 없애기 */
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}


.cart table{
border:1px solid orange;
width:1000px;
text-align:center;

}

.cart th{
background-color:orange;
border:1px solid orange;
}
.cart td{
border:1px solid orange;
}

/* CSS RESET */
html, body {
	height: 100%;
}

* {
	padding: 0;
	margin: 0;
}

a {
	text-decoration: none;
}

li {
	list-style: none;
}

.full-bg {
	height: 100%;
}

.table {
	height: 100%;
	display: table;
	margin: 0 auto;
}

.table-cell {
	height: 100%;
	display: table-cell;
	vertical-align: middle;
}

.login-container {
	width: 1000px;
	background-color: #fff;
	padding: 70px 20px;
	box-sizing: border-box;
	
}

.login--title {
	width: 100%;
	text-align: center;
	font-size: 50px;
}

.form-btn {
	display: block;
	width: 30%;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
	border:0;
}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div>
		<div align="center" class="cart">
		<!-- 장바구니 리스트 출력  -->
		<div><h2>결제정보</h2></div>
			<table>
				<tr>
					<th style="width:75px"><!-- 이미지 --></th>
					<th>상품정보</th>
					<th>판매금액</th>
					<th>수량</th>
					<th>합 금액</th>
				</tr>
				<c:set var="result" value="0" />
				<c:forEach var="cart" items="${clist}">
					<tr>
						<td><img alt="이미지" src="images/product/1.jpg" width="75px"
							height="75px"></td>
						<td>${cart.item_name}</td>
						<td><fmt:formatNumber value="${cart.item_price}"
								pattern="#,##0" />원</td>
						<td>${cart.item_cnt}</td>
						<td><fmt:formatNumber
								value="${cart.item_price*cart.item_cnt}" />원</td>
					</tr>
					<c:set var="result"
						value="${result+(cart.item_price*cart.item_cnt)}" />
				</c:forEach>
			</table>
<!-- 주문자 정보  -->
		</div>
		<form action="payment.jsp" method="post" name="frm">
			<div align="center">
				<div class="login-container">
					<div align="left">
						<h3>주문자 정보</h3>
						<table>
							<tr>
								<th>주문하시는 분</th>
								<td><%=session.getAttribute("name")%></td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
								<td><%=session.getAttribute("phone")%></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><%=session.getAttribute("email")%>
							</tr>
						</table>
						<br>
						<hr>
						<br>
						<!-- 배송정보 작성  -->
						<h3>배송정보</h3>
						<table>
							<tr>
								<th>배송지 확인</th>
								<td>주문자정보와 동일<input type="radio" name="addrCheck" value="0" checked> 
									직접입력<input type="radio" name="addrCheck"	value="1">
								</td>
							</tr>
							<tr>
								<th>받으실 분</th>
								<td><input type="text" name="deli_name" readonly
									value="<%=session.getAttribute("name")%>"></td>
							</tr>
							<tr>
								<th>받으실 곳</th>
								<td><input type="text" name="deli_postcode"id="sample4_postcode" value="<%=postcode%>" placeholder="우편번호" readonly>
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
								<input type="text" name="deli_addr1" id="sample4_roadAddress"  value="<%=addr1%>" placeholder="도로명주소" readonly><br>
								<input type="text" name="deli_addr2" id="sample4_detailAddress" value="<%=addr2%>"  placeholder="상세주소" size="60" readonly><br>
								<span id="guide" style="color:#999;display:none"></span>
								<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소">
								<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
								</td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
								<td><input type="text" name="deli_phone" readonly
									value="<%=session.getAttribute("phone")%>"></td>
							</tr>
							<tr>
								<th>배달 참고메세지</th>
								<td><input type="text" name="deli_msg"></td>
							</tr>
							
							<tr>
								<th>포인트</th>
								<td>
								<input id="a" type="hidden" value="${result}">
								<input id="usePoint" type="number" min="0" max="<%=session.getAttribute("point")%>" name="usePoint" value="0" size="3">
								사용가능한 포인트 : <%=session.getAttribute("point")%>p<br>
							</tr>
						</table>
						<br>
						<hr>
						<br>
						<h3>공동현관 출입방법</h3>
						<table>
							<tr>
								<th>출입방법</th>
								<td>공동출입문
								<input type="radio" name="deli" id="deli1" onchange="setDisplay()" checked>
								별도 출입제한 없음
								<input type="radio" name="deli" id="deli2" onchange="setDisplay()">
								<div id="divId">
									<input type="text" name="deli_pwd" size="40" placeholder="예) #1234*/ 열쇠버튼+abcd+OK버튼">
									</div>
							</tr>
						</table>
					</div>
						<br>
						<hr>
						<br>
					<h2>
						최종 결제 금액<br><br> 
					</h2>
						상품가격 <fmt:formatNumber value="${result}"/>원<br>
						- 사용할 포인트 <span id="usePointResult"></span>p<br>
						+ 배송비 <fmt:formatNumber value="3000"/>원<br>
						= <span id="ab"></span>원입니다.<br><br>
						<fmt:parseNumber var="point" integerOnly="true" value="${result/100*5}"/>
						적립될 포인트 : <fmt:formatNumber value="${point}"/>P입니다.
					<input type="hidden" name="item_name" value="${clist[0].item_name}">
					<input type="hidden" name="userid" value="<%=session.getAttribute("userid")%>">
					<input type="hidden" name="total_price" value="${result+3000}">
					<input type="hidden" name="deli_status" value="결제 완료">
					<input type="hidden" name="point" value="${point}">
					<input type="submit" id="submit" class="form-btn" value="결제">
				</div>
			</div>
		</form>
	</div>	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

