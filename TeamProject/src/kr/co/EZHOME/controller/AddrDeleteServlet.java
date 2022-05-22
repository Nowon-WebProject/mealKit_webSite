package kr.co.EZHOME.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.AddrDAO;
import kr.co.EZHOME.dao.CartDAO;

/**
 * Servlet implementation class AddrDeleteServlet
 */
@WebServlet("/addrdelete.do")
public class AddrDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddrDeleteServlet() {
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
		int deli_addr_seq = Integer.parseInt(request.getParameter("deli_addr_seq"));
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		AddrDAO adao = AddrDAO.getInstance();
		adao.deleteAddr(deli_addr_seq);
		response.sendRedirect("addrlist.do?userid="+userid);
	}

}
