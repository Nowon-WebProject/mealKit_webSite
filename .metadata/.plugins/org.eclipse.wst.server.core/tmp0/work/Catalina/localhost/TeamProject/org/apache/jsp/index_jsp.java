/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.76
 * Generated at: 2022-05-26 02:29:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import kr.co.EZHOME.dao.UserDAO;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("kr.co.EZHOME.dao.UserDAO");
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
      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write(" <title>이젠, 집에서 | 메인화면</title>\r\n");
      out.write("        <!-- Bootstrap icons-->\r\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\" rel=\"stylesheet\" />\r\n");
      out.write("        <!-- Core theme CSS (includes Bootstrap)-->\r\n");
      out.write("        <link href=\"css/styles.css\" rel=\"stylesheet\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "nav.jsp", out, false);
      out.write("\r\n");
      out.write("        <!-- Header-->\r\n");
      out.write("        <header class=\"py-5\" style=\"background-color:#fd7e14\">\r\n");
      out.write("            <div class=\"container px-4 px-lg-5 my-5\">\r\n");
      out.write("                <div class=\"text-center text-white\">\r\n");
      out.write("                    <h1 class=\"display-4 fw-bolder\">이젠, 집에서</h1>\r\n");
      out.write("                    <p class=\"lead fw-normal text-white-50 mb-0\">엄마가 해주셨던 맛, 그대로</p>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </header>\r\n");
      out.write("        <!-- Section-->\r\n");
      out.write("        <section class=\"py-5\">\r\n");
      out.write("            <div class=\"container px-4 px-lg-5 mt-5\">\r\n");
      out.write("                <div class=\"row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center\">\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                             <a href=\"#제품에 대한 상세페이지\">\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/1.jpg\" alt=\"...\" />\r\n");
      out.write("                            </a>\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                            <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Sale badge-->\r\n");
      out.write("                            <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/2.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                             <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Sale badge-->\r\n");
      out.write("                            <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/3.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                              <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/4.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                              <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Sale badge-->\r\n");
      out.write("                            <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/5.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                              <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/6.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                              <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Sale badge-->\r\n");
      out.write("                            <div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale</div>\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/7.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                              <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"col mb-5\">\r\n");
      out.write("                        <div class=\"card h-100\">\r\n");
      out.write("                            <!-- Product image-->\r\n");
      out.write("                            <img class=\"card-img-top\" src=\"images/product/8.jpg\" alt=\"...\" />\r\n");
      out.write("                            <!-- Product details-->\r\n");
      out.write("                             <div class=\"card-body p-4\">\r\n");
      out.write("                                <div class=\"text-center\">\r\n");
      out.write("                                    <!-- Product name-->\r\n");
      out.write("                                    <h5 class=\"fw-bolder\">제품명</h5>\r\n");
      out.write("                                    <!-- Product price-->\r\n");
      out.write("                                    \\ 가격\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <!-- Product actions-->\r\n");
      out.write("                            <div class=\"card-footer p-4 pt-0 border-top-0 bg-transparent\">\r\n");
      out.write("                                <div class=\"text-center\"><a class=\"btn btn-outline-dark mt-auto\" href=\"#\"><i class=\"bi-cart-fill me-1\"></i>장바구니 담기</a></div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </section>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      out.write("<br>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\r\n");
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