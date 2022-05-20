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
	<div id="wrap" style="width: 400px" align="center">
		<h1>상품 수정-관리자 페이지</h1>
		<form action="itemUpdate2.do" method="post" enctype="multipart/form-data" name="frm">
			<input type="hidden" name="item_num" value="${item.item_num}">
			<input type="hidden" name="nonmakeImg" value="${item.item_pictureUrl}">
			<table>
				<tr>
					<th>사진</th>
					<td>
						<c:choose>
							<c:when test="${empty item.item_pictureUrl}">
								<img id="imgUpdate" src="upload/noimage.gif">
							</c:when>
							<c:otherwise>
								<img id="imgUpdate" src="upload/${item.item_pictureUrl}">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td>
						<c:choose>
							<c:when test="${item.item_category == '한식'}">
							<select name="item_category">
								<option value="한식" selected>한식</option>
								<option value="양식">양식</option>
								<option value="중식">중식</option>
								<option value="일식">일식</option>
								<option value="샐러드">샐러드</option>
							</select>
							</c:when>
							<c:when test="${item.item_category == '양식'}">
							<select name="item_category">
								<option value="한식">한식</option>
								<option value="양식" selected>양식</option>
								<option value="중식">중식</option>
								<option value="일식">일식</option>
								<option value="샐러드">샐러드</option>
							</select>
							</c:when>
							<c:when test="${item.item_category == '중식'}">
							<select name="item_category">
								<option value="한식">한식</option>
								<option value="양식">양식</option>
								<option value="중식" selected>중식</option>
								<option value="일식">일식</option>
								<option value="샐러드">샐러드</option>
							</select>
							</c:when>
							<c:when test="${item.item_category == '일식'}">
							<select name="item_category">
								<option value="한식">한식</option>
								<option value="양식">양식</option>
								<option value="중식">중식</option>
								<option value="일식" selected>일식</option>
								<option value="샐러드">샐러드</option>
							</select>
							</c:when>
							<c:when test="${item.item_category == '샐러드'}">
							<select name="item_category">
								<option value="한식">한식</option>
								<option value="양식">양식</option>
								<option value="중식">중식</option>
								<option value="일식">일식</option>
								<option value="샐러드" selected>샐러드</option>
							</select>
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>상품명</th>
					<td><input type="text" name="item_name" size="16" value="${item.item_name}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="item_content">${item.item_content}</textarea></td>
				</tr>
				<tr>
					<th>가격</th>
					<td><input type="text" size="4" name="item_price" value="${item.item_price}">원</td>
					<!-- 문자열로 보내짐 -->
				</tr>
				<tr>
					<th>재고</th>
					<td><input type="text" size="3" name="item_quantity" value="${item.item_quantity}">개</td>
				</tr>
				<tr>
					<th>인분</th>
					<td><input type="text" size="1" name="item_total" value="${item.item_total}">(숫자만 입력하세요)</td>
				</tr>
				<tr>
					<th>조리시간</th>
					<td><input type="text" size="1" name="item_time" value="${item.item_time}">분</td>
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