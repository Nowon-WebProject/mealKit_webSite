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
        <style type="text/css">
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

.form-btn:hover {
	background-color: #FF9900;
	box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
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
        <!-- Header-->
        <header class="py-5" style="background-color:#fd7e14">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">이젠, 집에서</h1>
                    <p class="lead fw-normal text-white-50 mb-0">엄마가 해주셨던 맛, 그대로</p>
                </div>
            </div>
        </header>
        <br>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
         <h2>현대인의 선택, 샐러드!</h2>   
       	 <hr>
     	  <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach var="item" items="${ilist1}">
                	<form action="cartInsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
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
                                  	   <marquee width="100%"><h5 class="fw-bolder">${item.item_name}</h5></marquee>
                                    </c:when>
                                    <c:otherwise>
                                  	  <h5 class="fw-bolder">${item.item_name}</h5>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                    <p>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i></p>
                                   <%--  <p style="color:gray">${item.item_content}</p> --%>
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
                    <input type="number" name="item_cnt" value="1" min="1" max="${item.item_quantity}">

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
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    
                    
                    
                             <h2>이젠, 집에서 즐기는 해외여행!</h2>   
       		 <hr>
     	  <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach var="item" items="${ilist2}">
                	<form action="cartInsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
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
                                  	   <marquee width="100%"><h5 class="fw-bolder">${item.item_name}</h5></marquee>
                                    </c:when>
                                    <c:otherwise>
                                  	  <h5 class="fw-bolder">${item.item_name}</h5>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                    <p>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i></p>
                                   <%--  <p style="color:gray">${item.item_content}</p> --%>
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
                    <input type="number" name="item_cnt" value="1" min="1" max="${item.item_quantity}">

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
                    <br>
                    <br>
                    <br>
                    <br>
                    <br>
                    
                                  
                             <h2>한국인의 사랑, 찌개!</h2>   
       		 <hr>
     	  <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach var="item" items="${ilist3}">
                	<form action="cartInsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
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
                                  	   <marquee width="100%"><h5 class="fw-bolder">${item.item_name}</h5></marquee>
                                    </c:when>
                                    <c:otherwise>
                                  	  <h5 class="fw-bolder">${item.item_name}</h5>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                    <p>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i></p>
                                   <%--  <p style="color:gray">${item.item_content}</p> --%>
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
                    <input type="number" name="item_cnt" value="1" min="1" max="${item.item_quantity}">

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
                    
                    
                                        
                                  
                             <h2>불금에 어울리는 음식!</h2>   
       		 <hr>
     	  <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                	<c:forEach var="item" items="${ilist4}">
                	<form action="cartInsert.do" method="post">
                    <div class="col mb-5">
                        <div class="card h-100">
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
                                  	   <marquee width="100%"><h5 class="fw-bolder">${item.item_name}</h5></marquee>
                                    </c:when>
                                    <c:otherwise>
                                  	  <h5 class="fw-bolder">${item.item_name}</h5>
                                    </c:otherwise>
                                    </c:choose>
                                    </div>
                                    <p>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-fill"></i>
                                    <i style="color:orange;" class="bi-star-half"></i>
                                    <i style="color:orange;" class="bi-star"></i>
                                    <i style="color:orange;" class="bi-star"></i></p>
                                   <%--  <p style="color:gray">${item.item_content}</p> --%>
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
                    <input type="number" name="item_cnt" value="1" min="1" max="${item.item_quantity}">

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
                    
                    
                    
                </div>
                </section>
                
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