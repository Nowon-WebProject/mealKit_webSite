package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.MyAddrDAO;

/**
 * Servlet implementation class AddrDeleteServlet
 */
@WebServlet("/myAddrDelete.do")
public class MyAddrDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAddrDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 나의 배송지 삭제 서블릿
		// seq값을 받아와 해당하는 데이터를 삭제함.
		
		String userid = request.getParameter("userid");
		
		
		int my_deli_addr_seq = Integer.parseInt(request.getParameter("my_deli_addr_seq"));
		MyAddrDAO madao = MyAddrDAO.getInstance();
		madao.deleteAddr(my_deli_addr_seq);
		
		response.sendRedirect("myAddrManage.do?userid="+userid);
	}

}
