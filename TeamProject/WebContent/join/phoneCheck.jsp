<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/TeamProject/js/libs/jquery-3.6.0.min.js?ver=1"></script>
<script>
$(document).ready(function() {
	var timer = null;
	var isRunning = false;

	
	$("#btn_send").click(function(e){
		var display = $('.time');
		var leftSec = 180;
		// 남은 시간
		// 이미 타이머가 작동중이면 중지
		if (isRunning){
		    clearInterval(timer);
		    display.html("");
		    startTimer(leftSec, display);
		}else{
		    startTimer(leftSec, display);
		    		
		}
	});

	
	    
	function startTimer(count, display) {
	            
	    		var minutes, seconds;
	            timer = setInterval(function () {
	            minutes = parseInt(count / 60, 10);
	            seconds = parseInt(count % 60, 10);
	     
	            minutes = minutes < 10 ? "0" + minutes : minutes;
	            seconds = seconds < 10 ? "0" + seconds : seconds;
	     
	            display.html(minutes + ":" + seconds);
	     
	            // 타이머 끝
	            if (--count < 0) {
	    	     clearInterval(timer);
	    	     alert("시간초과");
	    	     display.html("시간초과");
	    	     $('.btn_chk').attr("disabled","disabled");
	    	     isRunning = false;
	            }
	        }, 1000);
	             isRunning = true;
	}
	
	function phoneok(){
		//opener.frm.reid.value=document.frm.userid.value;
		self.close();
	}
});
</script>
</head>
<body>
<h2>휴대폰 번호 인증</h2>
	<form>
		<table>
			<tr>
				<td>
					<input type="text" id="certificationNumber" placeholder="숫자 6자리입력">
				</td>
				<td>
					<input type="button" id="btn_send" value="인증번호 발송">			
				</td>
			</tr>
			<tr>
				<td>
					<span class="time"></span>
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