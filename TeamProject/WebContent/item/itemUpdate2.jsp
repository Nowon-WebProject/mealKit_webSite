<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1>상품 수정-관리자 페이지</h1>
		<form action="itemUpdate2.do" method="post" name="frm">
			<input type="hidden" name="item_num" value="${item.item_num}">
			<input type="hidden" name="nonmakeImg" value="${item.item_pictureUrl}">
			<table>
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty item.pictureUrl}">
								<img src="upload/noimage.gif">
							</c:when>
							<c:otherwise>
								<img src="upload/${item.pictureUrl}">
							</c:otherwise>
						</c:choose>
					</td>
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
					<td><input type="text" name="item_name" size="80" value="${item.item_name}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="80" rows="10" name="item_content"></textarea></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" name="item_price" value="${item.item_price}">원</td>
					<!-- 문자열로 보내짐 -->
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="text" name="item_quantity" value="${item.item_quantity}">개</td>
				</tr>
				<tr>
					<th>인분</th>
					<td><input type="text" name="item_total" value="${item.item_total}"></td>
				</tr>
				<tr>
					<th>조리시간</th>
					<td><input type="text" name="item_time" value="${item.item_time}">분</td>
				</tr>
				<tr>
					<th>사진</th>
					<td>
						<input type="file" name="item_pictureUrl"><br>
						(주의 사항: 이미지를 바꾸려고 할 때만 선택하세요.)									
					</td>
				</tr>
			</table><br>
			<input type="submit" value="수정" onclick="return itemCheck()">
			<input type="button" value="목록" onclick="location.href='itemList2.do'">
		</form>
	</div>
</body>
</html>