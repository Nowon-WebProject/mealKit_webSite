<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 회원가입 페이지 -->
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="/TeamProject/css/styles.css" rel="stylesheet" />
    <title>회원가입 화면</title>
    
    <style>
        #wrap{
            width:600px;

            margin-left:auto; 
            margin-right:auto;

            /*text-align:center;*/
        }
        
        table{
           /* border:3px solid #fd7e14 */
        }
        
        td{
            border:1px solid #fd7e14
        }
        
        #title{
            background-color:#fd7e14
        }
        
        .txt_guide {
        	display: none;
        }
        
        .badId, .badCheckedId {
/* 		    display: none; */
		    padding: 10px 0 9px;
		    font-size: 12px;
		    color: red;
		    line-height: 18px;
	   		word-break: break-all; 
		    letter-spacing: -.1px;
 		    clear: both;
		}
 		.badId:before, .badCheckedId:before{ 
 			/* \2715 : x 아이콘*/
     		content: '\2715'; 
     		display: inline-block; 
    		padding: 0 4px 0 2px; 
    		font-size: 12px; 
     		vertical-align: 0; 
 		} 
 		
 		.goodId, .goodCheckedId {
/* 		    display: none; */
			/* \2713 : 체크아이콘 */
			content: '\2713';
		    padding: 10px 0 9px;
		    font-size: 12px;
		    color: green;
		    line-height: 18px;
	   		word-break: break-all; 
		    letter-spacing: -.1px;
 		    clear: both;
		}
 		
 		.goodId:before, .goodCheckedId:before {
    		content: '\2713';
    		display: inline-block;
    		padding: 0 4px 0 2px;
    		font-size: 12px;
    		vertical-align: 0;
 		}
		
    </style>
    <script type="text/javascript" src="/TeamProject/js/libs/jquery-3.6.0.min.js"></script>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="/TeamProject/js/member.js?test=40"></script>
    <!-- 도로명 주소 검색시 사용하는 daum api -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
<jsp:include page="/ui/nav.jsp"></jsp:include>
<!-- 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
    <div align="center">
        <br><br>
        <b><font size="6" color="gray">회원가입</font></b>
        <br><br><br>
    </div>
<div id="wrap">
	<form action="/TeamProject/join.do" method="post" name="frm">
		<table>
			<tr>
			<td colspan="2"><hr> </td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20">*</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" id="userid" name="userid" size="20" maxlength="16">*
					<input type="hidden" name="reid" size="20">
					<input type="button" value="중복체크" onclick="idCheck()">
				</td>
			</tr>
			<tr>
				<td/>
				<td>
					<p class="txt_guide" >
						<span class="badId">4자 이상의 영문 혹은 영문과 숫자를 조합</span>
						<input type="hidden" name="idValid" size="20">
						<br>
						<span class="badCheckedId">아이디 중복확인</span>
					</p>
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
				<td>생년월일</td>
				<td>
					<input type="text" name="birth" placeholder="8자리입력(예.19951129)" maxlength="8">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type='tel' class="phone" name='phone' maxlength="13"/>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" id="sample4_postcode" name="addr1" placeholder="우편번호">
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample4_roadAddress" name="roadAddr" placeholder="도로명주소" size="60" ><br>
					<input type="hidden" id="sample4_jibunAddress" name="jibunAddr" placeholder="지번주소"  size="60">
					<span id="guide" style="color:#999;display:none"></span>
					<input type="text" id="sample4_detailAddress" name="addr3" placeholder="상세주소"  size="60"><br>
					<input type="hidden" id="sample4_extraAddress" name="cham" placeholder="참고항목"  size="60">
					<input type="hidden" id="sample4_engAddress" name="engAddr" placeholder="영문주소"  size="60" >
				</td>
			</tr>
			<tr>
				
			</tr>
			<tr>
				<td colspan="2"><hr></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="admin" value="0">
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

<jsp:include page="/ui/footer.jsp"></jsp:include>
</body>
</html>