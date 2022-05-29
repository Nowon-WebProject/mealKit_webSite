<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="js/item.js"></script>
</head>
<body>
	<div id="wrap" style="width: 400px" align="center">
		<h1>상품 등록-관리자 페이지</h1>
		<form action="itemWrite.do" method="post" enctype="multipart/form-data" name="frm">
			<table>
				<tr>
					<th>카테고리</th>
 				<td>	<select name="item_category">
							<option value="new">신규등록</option>
							<c:forEach var="category" items="${categoryList}">
							<option value="${category.item_category}">${category.item_category}</option>
							</c:forEach>
					</select>
							<input type="text" name="NewCategory" placeholder="신규등록일시 입력"></td> 
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="item_name" size="16"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="item_content"></textarea></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" size="4" name="item_price">원</td>
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="text" size="3" name="item_quantity">개</td>
				</tr>
				<tr>
					<th>인분</th>
					<td><input type="text" size="1" name="item_total">(숫자만 입력하세요)</td>
				</tr>
				<tr>
					<th>조리시간</th>
					<td><input type="text" size="1" name="item_time">분</td>
				</tr>
				<tr>
					<th>메인 등장 여부</th>
					<td><input type="text" size="1" name="item_main">(안 하면 0, 하면 1 입력)</td>
				</tr>
				<tr>
					<th>판매량</th>
					<td><input type="text" size="1" name="item_sales">개</td>
				</tr>
				<tr>
					<th>대표 사진</th>
					<td><input type="file" name="item_pictureUrl1"><br>
						(주의 사항: 이미지를 넣으려고 할 때만 선택하세요.)
					</td>
				</tr>
				<tr>
					<th>상세 사진</th>
					<td><input type="file" name="item_pictureUrl2"><br>
						(주의 사항: 이미지를 넣으려고 할 때만 선택하세요.)
					</td>
				</tr>
			</table><br>
			<input type="submit" value="등록">
			<input type="reset" value="다시 작성">
		</form>
	</div>
</body>
</html>