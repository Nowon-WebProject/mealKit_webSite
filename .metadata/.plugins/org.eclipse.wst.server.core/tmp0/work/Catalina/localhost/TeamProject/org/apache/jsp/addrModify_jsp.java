/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.76
 * Generated at: 2022-05-23 07:03:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addrModify_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/mealKit_webSite/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/TeamProject/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1651802072000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
 String addr = request.getParameter("my_deli_addr");

String postcode = addr.substring(1,6);
String addr1 = addr.substring(8,addr.lastIndexOf(","));
String addr2 = addr.substring(addr.lastIndexOf(",")+2);


      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>이젠, 집에서 | 회원가입</title>\r\n");
      out.write("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\" rel=\"stylesheet\" />\r\n");
      out.write("<link href=\"css/styles.css\" rel=\"stylesheet\" />\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"http://dmaps.daum.net/map_js_init/postcode.v2.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"js/member.js\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("th{\r\n");
      out.write("width:40%;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<div align=\"center\">\r\n");
      out.write("\r\n");
      out.write("	<form action=\"myaddrmodify.do\" method=\"post\">\r\n");
      out.write("		<table>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>배송지 별명</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_nick\"\r\n");
      out.write("					value=\"");
      out.print(request.getParameter("my_deli_nick"));
      out.write("\" required></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>받으실 분</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_name\"\r\n");
      out.write("					 value=\"");
      out.print(request.getParameter("my_deli_name"));
      out.write("\" required></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>받으실 곳</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_postcode\" id=\"sample4_postcode\" value=\"");
      out.print(postcode);
      out.write("\" placeholder=\"우편번호\" size=\"5\"  required>\r\n");
      out.write("				<input type=\"button\" id=\"daumPostCode\" onclick=\"sample4_execDaumPostcode()\" value=\"우편번호 찾기\" ><br>\r\n");
      out.write("				<input type=\"text\" name=\"deli_addr1\" id=\"sample4_roadAddress\" value=\"");
      out.print(addr1);
      out.write("\" placeholder=\"도로명주소\" size=\"30\"  required><br>\r\n");
      out.write("				<input type=\"text\" name=\"deli_addr2\" id=\"sample4_detailAddress\" value=\"");
      out.print(addr2);
      out.write("\" placeholder=\"상세주소\"  required><br>\r\n");
      out.write("				<span id=\"guide\" style=\"color: #999; display: none\"></span>\r\n");
      out.write("				<input type=\"hidden\" id=\"sample4_jibunAddress\" placeholder=\"지번주소\">\r\n");
      out.write("				<input type=\"hidden\" id=\"sample4_extraAddress\" placeholder=\"참고항목\">\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>휴대폰 번호</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_phone\" \r\n");
      out.write("					value=\"");
      out.print(request.getParameter("my_deli_phone"));
      out.write("\" required></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>배달<br>참고메세지</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_msg\" value=\"");
      out.print(request.getParameter("my_deli_msg"));
      out.write("\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>공동현관<br>비밀번호</th>\r\n");
      out.write("				<td><input type=\"text\" name=\"deli_pwd\" size=\"35\" value=\"");
      out.print(request.getParameter("my_deli_pwd"));
      out.write("\" placeholder=\"예) #1234*/ 열쇠버튼+abcd+OK버튼\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("		</table>\r\n");
      out.write("		<button type=\"button\" onclick=\"history.back()\">이전</button>\r\n");
      out.write("		<input type=\"hidden\" name=\"my_deli_addr_seq\" value=\"");
      out.print(request.getParameter("my_deli_addr_seq"));
      out.write("\">\r\n");
      out.write("		<input type=\"submit\" value=\"수정\">\r\n");
      out.write("	</form>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
