<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if (confirm("정말 모든 상품들을 삭제하시겠습니까?"))
		location.href = "itemDeleteDo";
	else
		history.go(-1);
</script>
</head>
<body>

</body>
</html>