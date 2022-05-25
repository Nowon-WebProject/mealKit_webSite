<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
		$(".addDelay").click(function(e) {
			var display = $('.time');
			var count = leftSec;
			// 남은 시간
			// 이미 타이머가 작동중이면 중지
			if (isRunning) {
				//clearInterval: setInterval을 통해 진행되고 있는 함수의 동작을 멈춘다
				clearInterval(timer);
				displayTime(count - 1, display);
				startTimer(count - 2, display);
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
		
		$("#btn_check").click(function() {
			var inputText = $("#inputCertificationNumber").val();
			var certificationNumber = $("#certificationNumber").val();
			var status = $("#certificationStatus");
			
			if (certificationNumber === inputText) {
				alert("인증에 성공하셨습니다");
				status.val("true");			
			}
			else {
				alert("인증에 실패하셨습니다");
				status.val("false");
			}
			
		});
		
	});
	
	
	
	function phoneok() {
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
</script>
</head>
<body>
<h2>휴대폰 번호 인증</h2>
	<form>
		<table>
			<tr>
				<td>
					<input type="text" id="inputCertificationNumber" placeholder="숫자 6자리입력" maxlength="6">
				</td>
				<td>	
					<input type="button" id="btn_check" value="인증번호 확인">
					<!-- 실제 발급된 인증번호를 저장하는 hidden 타입의 input -->	
					<input type="hidden" id="certificationNumber" value="${certificationNumber}">
					${certificationNumber}
					<!-- 인증을 성공했는지 안했는지 확인하기 위한 hidden 타입의 input -->
					<input type="hidden" id="certificationStatus" value="false">
				</td>
			</tr>
			<tr>
				<td>
					<span class="time"></span>
					<script>
						displayTime(leftSec, $(".time"));
					</script>
				</td>
				<td>
					<a href=# class="reSend">재발송</a>&#9<a href=# class="addDelay">유효시간 연장</a>
				</td>
			</tr>
				

			<tr>
				<td>
					<input type="button" value="취소" onclick="phoneok()">
				</td>
				<td>
					<input type="submit" value="확인">
				</td>
			</tr>
		</table>
	</form>
	

</body>
</html>