<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>회원가입 화면</title>
    
    <style>
        #wrap{
            width:530px;
            margin-left:auto; 
            margin-right:auto;
            
        }
        
        table{
            border:3px solid #fd7e14
        }
        
        td{
            border:1px solid #fd7e14
        }
        
        #title{
            background-color:#fd7e14
        }
    </style>
    <script type="text/javascript" src="js/member.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<!-- 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
    <div align="center">
        <br><br>
        <b><font size="6" color="gray">회원가입</font></b>
        <br><br><br>
    </div>
    <div id="wrap">
<form action="join.do" method="post" name="frm">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" size="20">*</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="userid" size="20">*
				<input type="hidden" name="reid" size="20">
				<input type="button" value="중복체크" onclick="idCheck()">
			</td>
		</tr>
		<tr>
			<td>암호</td>
			<td><input type="password" name="pwd" size="20">*</td>
		</tr>
		<tr height="30">
			<td width="80">암호 확인</td>
			<td><input type="password" name="pwd_check" size="20">*</td>
		</tr>
		<tr>
			<td> 생년월일 </td>
			<td><input type="text" name="birth"></td>
		<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20">
				
					<select>
						<option>naver.com</option>
						<option>daum.com</option>
						<option>google.com</option>
						<option>직접입력</option>
					</select>
					
				</td>	
			</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" size="20"></td>
		</tr>
		
		<tr>
		<td>가입날짜</td>
		<td><input type="text" name="rdate"></td>
		</tr>
		<tr>
				<td>주소</td>
				<td>
				<input type="text" name="addr" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text"  name="deli" id="sample4_roadAddress" placeholder="도로명주소">
				<input type="text" name="deli" id="sample4_jibunAddress" placeholder="지번주소"><br>
				<span id="guide" name="deli" style="color:#999;display:none"></span>
				<input type="text" name="deli" id="sample4_detailAddress" placeholder="상세주소" size="60"><br>
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
				
				</td>
			</tr>
		<tr>
			<td>등급</td>
			<td>
				<input type="radio" name="admin" value="0" checked="checked">일반회원
				<input type="radio" name="admin" value="1">관리자
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="확인" onclick="return joinCheck()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
		<tr>
			<td colspan="2">${message}</td>
		</tr>
	</table>
</form>
</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>