<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<script type="text/javascript" src="js/item.js"></script>
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 등록-관리자 페이지</h1>
		<form action="itemWrite2.do" method="post" enctype="multipart/form-data" name="frm">
			<table>
				<tr>
					<th>카테고리</th>
					<td>
						<select name="item_category">
						<option value="한식">한식</option>
						<option value="양식">양식</option>
						<option value="중식">중식</option>
						<option value="일식">일식</option>
						<option value="샐러드">샐러드</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="item_name" size="80"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="80" rows="10" name="item_content"></textarea></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="item_price">원</td>
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="text" name="item_quantity">개</td>
				</tr>
				<tr>
					<th>인분</th>
					<td><input type="text" name="item_total"></td>
				</tr>
				<tr>
					<th>조리시간</th>
					<td><input type="text" name="item_time">분</td>
				</tr>
				<tr>
					<th>사진</th>
					<td><input type="file" name="item_pictureUrl"><br>
						(주의 사항: 이미지를 넣으려고 할 때만 선택하세요.)
					</td>
				</tr>
			</table><br>
			<input type="submit" value="등록" onclick="return itemCheck()">
			<input type="reset" value="다시 작성">
			<input type="button" value="목록" onclick="location.href='itemList2.do'">
		</form>
	</div>
</body>
</html>