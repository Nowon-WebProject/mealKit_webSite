<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>


<form action="itemabout.do" method="post">
<input type="text" name="item_num" value="1">

<input type="submit" value="상품페이지로">
</form>




<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>