package kr.co.EZHOME.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.MyAddrDAO;
import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.MyAddrDTO;

/**
 * Servlet implementation class MyAddrModify
 */
@WebServlet("/myaddrmodify.do")
public class MyAddrModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyAddrModifyServlet() {
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
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		request.setCharacterEncoding("utf-8");
		int my_deli_addr_seq =  Integer.parseInt(request.getParameter("my_deli_addr_seq"));
		String my_deli_nick = request.getParameter("deli_nick");
		String my_deli_name = request.getParameter("deli_name");
		String my_deli_addr = "("+request.getParameter("deli_postcode")+") "+ request.getParameter("deli_addr1") +", "+ request.getParameter("deli_addr2");
		String my_deli_phone = request.getParameter("deli_phone");
		String my_deli_msg = request.getParameter("deli_msg");
		String my_deli_pwd = request.getParameter("deli_pwd");
		MyAddrDTO madto = new MyAddrDTO();
		madto.setMy_deli_addr_seq(my_deli_addr_seq);
		madto.setMy_deli_nick(my_deli_nick);
		madto.setMy_deli_name(my_deli_name);
		madto.setMy_deli_addr(my_deli_addr);
		madto.setMy_deli_phone(my_deli_phone);
		madto.setMy_deli_msg(my_deli_msg);
		madto.setMy_deli_pwd(my_deli_pwd);
		MyAddrDAO madao = MyAddrDAO.getInstance();
		madao.updateMyAddr(madto);
		
		
		response.sendRedirect("addrmanage.do?userid="+userid);
		
		
		
	}

}
