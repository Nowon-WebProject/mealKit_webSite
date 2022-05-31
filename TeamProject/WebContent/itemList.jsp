<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@page import="kr.co.EZHOME.dto.ItemDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.EZHOME.dao.ItemDAO"%>
<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>이젠, 집에서 | 메인화면</title>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
input[type='number']{
    width: 50px;
} 

.form-input {
	width: 100%;
	padding: 10px 20px;
	font-size: 20px;
	outline: none;
	margin: 10px 0;
	border: 1px solid #efefef;
	box-sizing: border-box;
}

.form-input:focus {
	box-shadow: 3px 3px 5px rgba(0, 0, 0, 0.1);
	border: none;
}

.form-input--title {
	width: 100%;
	display: block;
	margin: 5px 0;
	box-sizing: border-box;
}

.form-btn {
	border: 0;
	display: block;
	width: 100%;
	font-size: 16px;
	height: 40px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}

.pageSize{
	border: 0;
	width: 100px;
	font-size: 16px;
	height: 30px;
	background-color: #fd7e14;
	color: #fff;
	box-sizing: border-box;
	margin: 5px 0;
	cursor: pointer;
}


}

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
}

table {
	border: 1px solid orange;
	text-align: center;
	width: 100%;
}

th {
	background-color: orange;
	border: 1px solid orange;
}

td {
	border: 1px solid orange;
}


</style>
<script type="text/javascript">

function loginCheck() {
	var userid = "${userid}";
	if (userid == "") {
		alert("로그인 후 이용 가능합니다.");
	} else {
		alert("장바구니에 담았습니다.");
	}
}
</script>        
        
</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>
	<%
		// 화면에 보여질 총 게시글 개수
		int pageSize = 0;
		String ps = request.getParameter("pageSize");
		if (ps == null)
			pageSize = 12;
		else
			pageSize = Integer.parseInt(ps);
			
		// 누른 페이지
		String pageNum = request.getParameter("pageNum");	
		
		// 처음엔 1페이지
		if (pageNum == null)
			pageNum = "1";

		// 현재 페이지 (누른 페이지 또는 1페이지)
		int currentPage = Integer.parseInt(pageNum);
		
		// 전체 글 개수
		int count = 0;
		// 페이지 숫자 세기
		int number = 0;
		
		
		String check = request.getParameter("check");
		String keyword = request.getParameter("keyword");
		String category = request.getParameter("category");
		String priceSort = request.getParameter("priceSort");
		String view = request.getParameter("view");
		ItemDAO idao = ItemDAO.getInstance();
		System.out.println("카테고리 : "+category);
		System.out.println("키워드 : "+keyword);
		

			if (check.equals("all")) {
				count = idao.itemSearchCnt(keyword, category, check);
			} else if (check.equals("best")) {
				count = idao.itemSearchCnt(keyword, category, check);
			} else if (check.equals("new")) {
				count = idao.itemSearchCnt(keyword, category, check);
			}
		
		

	
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = (currentPage * pageSize);

		List<ItemDTO> list = idao.selectAllItem();

	%>
				

        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
      			  <h1>${title}</h1>
      			  <%if(!category.equals("empty")||!priceSort.equals("default")||!keyword.equals("")){ 
      				  if(category.equals("empty")){
      					  category = "모든";
      				  }
      				  if(keyword.equals("")){
      					  keyword = "전체";
      				  }
      			  %>
      			  <h4>'<%=category%>' 카테고리의 '<%=keyword%>' 검색 결과입니다. 가격정렬<%=priceSort%></h4>
      			  <%
   				  category = request.getParameter("category");
   				  keyword = request.getParameter("keyword");
      				  } %>
      			  <hr>
			<div align="right">
			<a href="itemList.do?view=${view}&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=high&keyword=<%=keyword%>" style="color:white;background-color:#FF8868;border-radius:5px;text-decoration-line: none;">&nbsp;가격 높은 순<i class="bi-arrow-bar-up"></i>&nbsp;</a>
			<a href="itemList.do?view=${view}&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=low&keyword=<%=keyword%>" style="color:white;background-color:#FF8868;border-radius:5px;text-decoration-line: none;">&nbsp;가격 낮은순<i class="bi-arrow-bar-down"></i>&nbsp;</a>
			<a href="itemList.do?view=${view}&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=default&keyword=<%=keyword%>" style="color:white;background-color:#FF8868;border-radius:5px;text-decoration-line: none;">&nbsp;가격 정렬 기본&nbsp;</a>
<br>
					<c:choose>
			<c:when test="${view eq 'card'}">	
			<a href="itemList.do?view=list&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>" style="color:white;background-color:skyblue;border-radius:5px;text-decoration-line: none;">&nbsp;리스트형 보기&nbsp;</a>
      			  </c:when>
      			  <c:otherwise>
			<a href="itemList.do?view=card&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>" style="color:white;background-color:skyblue;border-radius:5px;text-decoration-line: none;">&nbsp;카드형 보기&nbsp;</a>
      			  </c:otherwise>
      			  </c:choose>
      			  <br>
      			  <c:set var="pageSize" value="${pageSize}"></c:set>
				<c:choose>
					<c:when test="${pageSize == 8}">
						<form action="itemList.do">
							<select name="pageSize">
								<option value="8" selected>8</option>
								<option value="12">12</option>
								<option value="16">16</option>
								<option value="20">20</option>
							</select>
							<input type="hidden" name="view" value="${view}">
							<input type="hidden" name="priceSort" value="<%=priceSort%>">
							<input type="hidden" name="category" value="<%=category%>">
							<input type="hidden" name="check" value="${check}">
							<input type="hidden" name="keyword" value="<%=keyword%>">
							<button type="submit" class="pageSize" >개씩 보기</button>
						</form>
					</c:when>
					<c:when test="${pageSize == 12}">
						<form action="itemList.do">
							<select name="pageSize">
								<option value="8">8</option>
								<option value="12" selected>12</option>
								<option value="16">16</option>
								<option value="20">20</option>
							</select>
							<input type="hidden" name="view" value="${view}">
							<input type="hidden" name="priceSort" value="<%=priceSort%>">
							<input type="hidden" name="category" value="<%=category%>">
							<input type="hidden" name="check" value="${check}">
							<input type="hidden" name="keyword" value="<%=keyword%>">
							<button type="submit" class="pageSize" >개씩 보기</button>
						</form>
					</c:when>
					<c:when test="${pageSize == 16}">
						<form action="itemList.do">
							<select name="pageSize">
								<option value="8">8</option>
								<option value="12">12</option>
								<option value="16" selected>16</option>
								<option value="20">20</option>
							</select>
							<input type="hidden" name="view" value="${view}">
							<input type="hidden" name="priceSort" value="<%=priceSort%>">
							<input type="hidden" name="category" value="<%=category%>">
							<input type="hidden" name="check" value="${check}">
							<input type="hidden" name="keyword" value="<%=keyword%>">
							<button type="submit" class="pageSize" >개씩 보기</button>
						</form>
					</c:when>
					<c:when test="${pageSize == 20}">
						<form action="itemList.do">
							<select name="pageSize">
								<option value="8">8</option>
								<option value="12">12</option>
								<option value="16">16</option>
								<option value="20" selected>20</option>
							</select>
							<input type="hidden" name="view" value="${view}">
							<input type="hidden" name="priceSort" value="<%=priceSort%>">
							<input type="hidden" name="category" value="<%=category%>">
							<input type="hidden" name="check" value="${check}">
							<input type="hidden" name="keyword" value="<%=keyword%>">
							<button type="submit" class="pageSize" >개씩 보기</button>
						</form>
					</c:when>
				</c:choose>

		
			</div>
			<br>
	<% if(count != 0) {		%>
			<c:choose>
			<c:when test="${view eq 'card'}">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach var="item" items="${ilist}" varStatus="i">
                	<form action="cartInsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
                        <c:choose>
                        <c:when test="${check eq 'best'}">
                        	<c:choose>
                        		<c:when test="${i.count eq '1'}">
                  			 	     <div style="top: 0.1rem; left: 0.1rem; position:absolute"><img src="images/금.png"></div>
                  			      </c:when>
                        		<c:when test="${i.count eq '2'}">
                  			 	     <div style="top: 0.1rem; left: 0.1rem; position:absolute"><img src="images/은.png"></div>
                  			      </c:when>
                        		<c:when test="${i.count eq '3'}">
                  			 	     <div style="top: 0.1rem; left: 0.1rem; position:absolute"><img src="images/동.png"></div>
                  			      </c:when>
                        	</c:choose>
                        </c:when>
                        </c:choose>
                        <c:choose>
						<c:when test="${item.item_pictureUrl1 == null}">
                            <!-- Product image-->
                             <a href="itemAbout.do?item_num=${item.item_num}">
                            <img class="card-img-top" src="upload/no_image1.jpg" alt="..." />
                            </a>
                            </c:when>
                            <c:otherwise>
                            	<c:choose>
                            	<c:when test="${item.item_quantity != 0}">
                          	    <a href="itemAbout.do?item_num=${item.item_num}">
                         	   <img class="card-img-top" src="upload/${item.item_pictureUrl1}" alt="..." />
                         	   </a>
                         	   </c:when>
                        	    <c:otherwise>
                              <a href="itemAbout.do?item_num=${item.item_num}">
                            <img style="background:#000;opacity:0.2" class="card-img-top" src="upload/${item.item_pictureUrl1}" alt="..." />
                            </a>
                            
                            </c:otherwise>
                            </c:choose>
                            </c:otherwise>
                            </c:choose>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                	<div style="height:50px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
                                    <!-- Product name-->
                                    <c:choose>
                                    <c:when test="${fn:length(item.item_name)>11}">
                                  	   <marquee width="100%" scrollamount="5"><h5 class="fw-bolder">${item.item_name}</h5></marquee>
                                    </c:when>
                                    <c:otherwise>
                                  	  <h5 class="fw-bolder">${item.item_name}</h5>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                	<div style="height:50px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
                                    <!-- Product name-->
                                    <c:choose>
                                    <c:when test="${fn:length(item.item_content)>15}">
                                    <marquee width="100%"><p style="color:gray">${item.item_content}</p> </marquee>
                                    </c:when>
                                    <c:otherwise>
                                    <p style="color:gray">${item.item_content}</p>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                    
                                    
                                    
                                    
                                    <p>
                                    <c:choose>
                                    <c:when test="${star == 5}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    </c:when>
                                    <c:when test="${star >= 4.5}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    </c:when>
                                    <c:when test="${star >= 4.0}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 3.5}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 3.0}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 2.5}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 2.0}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 1.5}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 1.0}">
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:when test="${star >= 0.5}">
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:when>
                                    <c:otherwise>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    </c:otherwise>
                                    </c:choose>
                                    <br>
                                    <br>

                                    <!-- Product price-->
                                    <fmt:formatNumber value="${item.item_price}"/>원
                                    
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <c:choose>
                            	<c:when test="${item.item_quantity != 0}">
                            	<% String userid = (String) session.getAttribute("userid");  %>
                         		<c:set var="userid" value="<%=userid%>"/>
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto"><i class="bi-cart-fill me-1"></i>
                    <input type="hidden" name="userid" value="${userid}">
                    <input type="hidden" name="item_quantity" value="${item.item_quantity}">
                    <input type="hidden" name="item_pictureUrl1" value="${item.item_pictureUrl1}">
                    <input type="hidden" name="item_num" value="${item.item_num}">
                    <input type="hidden" name="item_name" value="${item.item_name}">
                    <input type="hidden" name="item_price" value="${item.item_price}">
                    
                    
                    <i class="bi-dash-circle" onclick="count('minus', this)"></i>
                    <input type="number" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" id="item_cnt" name="item_cnt" value="1" min="1" max="${item.item_quantity}">
					<i class="bi-plus-circle" onclick="count('plus', this)"></i>
								 <input type="submit" class="form-btn" value="장바구니에 담기" onClick="loginCheck()"></a></div>
                  				  </c:when>
                  				  <c:otherwise>
                  				  <div class="text-center">
                  				  <br>
                  				  <a class="btn btn-outline-dark mt-auto">품절된 상품입니다.<br></a>
                  				  <br>
                  				  <br>
                  				  </div>
                  				  </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
                    </div>
                    </form>
                    </c:forEach>
                    </div>
                   </c:when>
                   <c:otherwise>
				<c:forEach var="item" items="${ilist}">
                	<form action="cartInsert.do" method="post">
                		<table width="100%">
                			<tr>
                				<td width="100px" height="100px"> 
                				                        <c:choose>
						<c:when test="${item.item_pictureUrl1 == null}">
                            <!-- Product image-->
                             <a href="itemAbout.do?item_num=${item.item_num}">
                            <img class="card-img-top" src="upload/no_image1.jpg" alt="..." />
                            </a>
                            </c:when>
                            <c:otherwise>
                              <a href="itemAbout.do?item_num=${item.item_num}">
                            <img class="card-img-top" src="upload/${item.item_pictureUrl1}" alt="..." />
                            </a>
                            </c:otherwise>
                            </c:choose>
                				
                				 </td>
                            	<td width="40%">${item.item_name}<br>
                            	${item.item_content}
                            	</td>
                            	<td width="20%"><fmt:formatNumber value="${item.item_price}"/></td>
                            	<c:choose>
                            	<c:when test="${item.item_quantity != 0}">
                            	<% String userid = (String) session.getAttribute("userid");  %>
                         		<c:set var="userid" value="<%=userid%>"/>
                            	<td width="30%"><a class="btn btn-outline-dark mt-auto"><i class="bi-cart-fill me-1"></i>
               					     <input type="hidden" name="userid" value="${userid}">
              					     <input type="hidden" name="item_quantity" value="${item.item_quantity}">
               					     <input type="hidden" name="item_pictureUrl1" value="${item.item_pictureUrl1}">
               					     <input type="hidden" name="item_num" value="${item.item_num}">
               					     <input type="hidden" name="item_name" value="${item.item_name}">
               				     <input type="hidden" name="item_price" value="${item.item_price}">
               				     
               				     <input type="number" name="item_cnt" value="1" min="1" max="${item.item_quantity}">
               				     
               				     
								 <input type="submit" class="form-btn" value="장바구니에 담기" onClick="loginCheck()"></a></td>
								 </c:when>
                  				 <c:otherwise>
                  				 <td width="30%">품절된 상품입니다</td>
               					  </c:otherwise>
           		                 </c:choose>
                			</tr>
                		</table>
                    </form>
			</c:forEach>
		</c:otherwise> 
		</c:choose>
                </div>
        </section>
    <%} else {%>
		<div align="center">
			<i style="font-size:200px;color:orange" class="bi-search"></i>
			<div style="font-size:30px;color:gray">검색 결과가 없습니다.</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
	<%
		}
	%>
	</div>
        <hr>
        <div align="center">
        <form action="itemList.do">
        <input type="hidden" name="priceSort" value="<%=priceSort%>">
        <input type="hidden" name="check" value="${check}">
        <input type="hidden" name="pageSize" value="<%=pageSize%>">
        <input type="hidden" name="view" value="<%=view%>">
		<select name="category">
			<option value="empty">선택안함</option>
        <c:forEach var="category" items="${categoryList}" >
			<option value="${category.item_category}">${category.item_category}</option>
			</c:forEach>
		</select>      
		&nbsp;
        <i class="bi-search" style="font-size:20px"></i>
		&nbsp;
        <input type="text" name="keyword" placeholder="검색할 상품을 입력해주세요" size="40">
        <input type="submit" value="검색">
        </form>
        </div>
        <hr>
        <div align="center">
        <h4>
		<%
			// 전체 페이지 개수 구하기
			// count: 전체 글 개수, pageSize: 화면에 보여질 총 게시글 개수
			int pageCount = count / pageSize + (count % pageSize == 0? 0:1);
		
			int startPage = 1;

			// 시작 페이지 구하기
			// currentPage: 현재 페이지
			if (currentPage % 10 != 0) {
				startPage = (currentPage / 10) * 10 + 1;
			} else {		
				startPage = (currentPage / 10 - 1) * 10 + 1;
			}
			
			// 끝 페이지 구하기
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			
			if(endPage > pageCount)
				endPage = pageCount;
					
			// 아래는 페이지 표시 과정

			if (startPage > 10) {
		%>
		<a href="itemList.do?pageNum=<%=startPage - 10 %>&view=<%=view%>&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>"  style="color:black;"><i class="bi-chevron-compact-left"></i></a>
		<%
			}
			for (int i = startPage; i <= endPage; i++) {
				if(currentPage == i){
					%>
		<a href="itemList.do?pageNum=<%=i %>&view=<%=view%>&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>" style="color:white;background-color:gray;border-radius:75px;text-decoration-line: none;">　<%=i %>　</a>
		<%
				}else{
					%>
		<a href="itemList.do?pageNum=<%=i %>&view=<%=view%>&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>" style="color:black;text-decoration-line: none;">　<%=i %>　</a>
					
					<%
				}
			}
			if (endPage < pageCount) {
		%>
		<a href="itemList.do?pageNum=<%=startPage + 10 %>&view=<%=view%>&pageSize=<%=pageSize%>&check=${check}&category=<%=category%>&priceSort=<%=priceSort%>&keyword=<%=keyword%>" style="color:black;"><i class="bi-chevron-compact-right"></i></a>
		<%
			}
		%>
		</h4>
		</div>
<Br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>