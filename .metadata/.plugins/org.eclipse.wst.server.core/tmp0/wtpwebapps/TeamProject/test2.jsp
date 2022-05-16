<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
 <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script>
    $( document ).ready( function() {
        $( '#a, #b' ).keyup( function() {
          var a = $( '#a' ).val();
          var b = $( '#b' ).val();
          var ab = a - b;
          $( '#ab' ).text( ab );
        } );
      } );
    </script>
    <style>
      * {
        font-family: Consolas;
      }
    </style>
  </head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
   <p>A <input type="number" id="a"></p>
    <p>B <input type="number" id="b"></p>
    <p>A * C = <span id="ab"></span></p>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>