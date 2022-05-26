<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/TeamProject/js/libs/jquery-3.6.0.min.js?ver=1"></script>
<script>
var leftSec = 180;
var timer = null;
var isRunning = false;



$(document).ready(function() {
		

		var display = $('.time');
		var count = leftSec;
		var delayCount = 0;
		// 남은 시간
		// 이미 타이머가 작동중이면 중지

		if (isRunning) {
			//clearInterval: setInterval을 통해 진행되고 있는 함수의 동작을 멈춘다
			clearInterval(timer);
			displayTime(count, display);
			startTimer(count - 1, display);
		} else {
			startTimer(count - 1, display);
		}

		$("#addDelay").click(function(e) {
			var display = $('.time');
			var count = leftSec;
			// 남은 시간
			// 이미 타이머가 작동중이면 중지
			if (delayCount < 1) {
				//clearInterval: setInterval을 통해 진행되고 있는 함수의 동작을 멈춘다
				clearInterval(timer);
				displayTime(count - 1, display);
				startTimer(count - 2, display);
				delayCount++;
			}
			else {
				alert("시간연장은 1회만 가능합니다.")
			}
		});

		function startTimer(count, display) {

			var minutes, seconds;
			//setInterval 주기적으로 함수를 실행하는 함수 
			//setInterval(function, ms) ms = 1000 당 1초
			timer = setInterval(function() {
				displayTime(count, display);

				// 타이머 끝
				if (--count < 0) {
					//setInterval을 통해 진행되고 있는 함수의 동작을 멈춘다
					clearInterval(timer);
					alert("시간초과");
					display.html("시간초과");
					isRunning = false;
					self.close();
				}
				//1초마다 함수실행
			}, 1000);
			isRunning = true;
		}

// 		$("#btn_check").click(function() {
// 			var inputText = $("#inputCertificationNumber").val();
// 			var certificationNumber = $("#certificationNumber").val();
// 			var status = $("#certificationStatus");

// 			if (certificationNumber === inputText) {
// 				alert("인증에 성공하셨습니다");
// 				status.val("true");
// 			} else {
// 				alert("인증에 실패하셨습니다");
// 				status.val("false");
// 			}

// 		});

		$("#reSend").click(function() {
			//jstl 을 이용하면 js 에서도 el 을 사용할 수 있다
			var sendCount = "<c:out value='${sendCount}'/>";

			if (sendCount > 5) {
				alert("재발송횟수를 초과하였습니다. 창을 닫고 다시 인증해주세요.");
			}
		});

	});

	function close() {
		//opener.frm.reid.value=document.frm.userid.value;
		self.close();
	}

	function displayTime(count, display) {
		display.html(getMin(count) + ":" + getSecond(count));
	}

	function getMin(count) {
		minutes = parseInt(count / 60, 10);
		//10보다 작을경우 앞에 0을 넣어준다 ex) 9 -> 09
		minutes = minutes < 10 ? "0" + minutes : minutes;
		return minutes
	}

	function getSecond(count) {
		seconds = parseInt(count % 60, 10);
		//10보다 작을경우 앞에 0을 넣어준다 ex) 9 -> 09
		seconds = seconds < 10 ? "0" + seconds : seconds;
		return seconds
	}
	
	function certificationok() {
		var certificationNumber = "<c:out value='${certificationNumber}'/>";
		var inputCertificationNumber = $("#inputCertificationNumber").val();

		//인증번호를 잘못 입력했을때
		if (certificationNumber === inputCeritifcationNumber) {
			alert("인증번호가 잘못됐습니다1. 다시 한번 확인해주세요");
			return false;
		}
		else {
			alert("인증번호가 잘못됐습니다2. 다시 한번 확인해주세요");
			//location
		}
		return true;
	}
</script>
<style type="text/css">
	input:focus { outline: none; }
	
	.inputBox {
		border: 1px solid;
		padding: 4px;
	}
	.actionBox {
		font-size: 10px;
		float: right;
	}
	table {
		border-collapse: seperate;
		border-spacing: 0, 40px; 
		position: relative;
		margin-left:auto; 
        margin-right:auto;
	}
	.time {
		color: red;
	}
	.btn1{
		background-color: #615952;
		color: white;
		margin-bottom: 5px;
		width: 100%;
		border: 0;
		outline: none;
		padding: 4px;
	}
	
	.btn2 {
		padding: 4px;
		outline: none;
		width: 100%
	}
	
	th {
		font-size: 20px;
	}
</style>
</head>
<body>
<section>
	<form action="/TeamProject/checkUser.do" method="post">
		<table>
			<tr>
				<th colspan="2">
					휴대폰 번호 인증
				</th>
			</tr>
			<tr>
				<td>
					<div class="inputBox">
						<input type="text" id="inputCertificationNumber" placeholder="숫자 6자리입력" maxlength="6"
						style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;">
						<span class="time"></span>
						<script>
							displayTime(leftSec, $(".time"));
						</script>
					</div>	
				</td>
				<td class="actionBox">		
					<a href="SendMessage.do" id="reSend">재발송</a> <br>		
					<a href=# id="addDelay">시간연장</a>
					<!-- 실제 발급된 인증번호를 저장하는 hidden 타입의 input -->	
					<input type="hidden" id="certificationNumber" value="${certificationNumber}">
					<!-- 인증을 성공했는지 안했는지 확인하기 위한 hidden 타입의 input -->
					<input type="hidden" id="certificationStatus" value="false">					
				</td>

			</tr>

			<tr>
				<td colspan="2">
					<input type="submit" class="btn1" value="확인" onclick="return certificationok()">
					<input type="button" class="btn2" value="취소" onclick="close()">
				</td>
				
			</tr>
		</table>
		${certificationNumber}
	</form>
	
</section>
</body>
</html>