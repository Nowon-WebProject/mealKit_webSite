<%@page import="kr.co.EZHOME.dto.UserDTO"%>
<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<script type="text/javascript" src="/TeamProject/js/member.js?version=1"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<br><br><br><br><br>
<!-- 정보  -->	
	<%
	String id=(String)session.getAttribute("id");
	UserDAO udao=UserDAO.getInstance();
	UserDTO udto=udao.getMember(id);
	
%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<form action="Modify.do" method="post" name="frm">
		<div id="wrap" align="center">	
		<h3>회원 정보 수정</h3><br>
				
		<table>
			<tr>
				<td>이름</td>
				<td><%=udto.getName() %><br></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><%=udto.getUserid() %>
				<input type="hidden" name="userid" value="<%=udto.getUserid() %>"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd" size="20"></td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20"></td>
			</tr>

			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"> @ <input type="text" name="eMailSite" value="" readonly>
					<select	id="eMailForm" name="eMailForm" size="1" onchange="email_check()">
						<option value="선택하세요">선택하세요</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="samsung.com">samsung.com</option>
						<option value="gmail.com">gmail.com</option>
						<option id="9">직접입력</option>
					</select></td>
				</tr>

				
				<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20" ></td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
				<input type="text" name="addr" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text"  name="addr1" id="sample4_roadAddress" placeholder="도로명주소">
				<input type="hidden" name="1" id="sample4_jibunAddress" placeholder="지번주소"><br>
				<span id="guide" name="deli1" style="color:#999;display:none"></span> <!-- 예상주소 -->
				<input type="text" name="addr2" id="sample4_detailAddress" placeholder="상세주소" size="60"><br>
				<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
				
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="확인" onclick="index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소"><br>
					<br>
				</td>
			</tr>
			
		</table>
		</div>
	</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>