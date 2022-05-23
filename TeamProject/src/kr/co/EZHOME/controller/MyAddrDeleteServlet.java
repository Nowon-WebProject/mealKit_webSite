package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.AddrDAO;
import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.MyAddrDAO;

/**
 * Servlet implementation class AddrDeleteServlet
 */
@WebServlet("/myaddrdelete.do")
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
		String userid = request.getParameter("userid");
		
		
		int my_deli_addr_seq = Integer.parseInt(request.getParameter("my_deli_addr_seq"));
		MyAddrDAO madao = MyAddrDAO.getInstance();
		madao.deleteAddr(my_deli_addr_seq);
		
		response.sendRedirect("addrmanage.do?userid="+userid);
	}

}