<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

<style><!-- 쓸 데 없는 기능들 제거할 것-->
.menu {
	float:left;
    display: block;
    width: 200px;
    background-color: orange;
    color: #fff;
    border-radius: 20px;
    padding: 10px;
    box-sizing: border-box;
    overflow: hidden; /* 반응형 애니메이션용 */
   	transition: all 0.5s ease; /* 반응형 애니메이션 */
    padding-bottom:150;
    padding-
}
.menu ul {
    
    margin: 0;
    padding: 0;
}
.menu a, .menu > label {
    display: block;
    height: 100px;
    padding: 8px;
    cursor: pointer;
    color: #fff;
    text-decoration: none;
}

</style> 
</head>
<body>
	<div class="menu">
    <label for="expand-menu"><div>마이 페이지</div></label>
    <ul>
        <li><a href="#" class="item"><div><i class="bi bi-bag-fill"></i> 주문내역확인</div></a></li>
        <li><a href="#" class="item"><div>주문취소</div></a></li>
        <li><a href="modifyOK.jsp" class="item"><div>회원정보수정</div></a></li>
        <li><a href="delete.jsp" class="item"><div>탈퇴</div></a></li>
    </ul>
</div>
 
	<script src="side.js"></script>
</body>
</html>