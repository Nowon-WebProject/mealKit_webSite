package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kr.co.EZHOME.dao.CartDAO;
import kr.co.EZHOME.dao.OrderDAO;
import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.CartDTO;
import kr.co.EZHOME.dto.OrderDTO;
import kr.co.EZHOME.dto.UserDTO;


/**
 * Servlet implementation class MovieList
 */
@WebServlet("/orderOkList.do")
public class OrderOkListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderOkListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 유저가 자신의 주문 정보를 불러오기 위한 서블릿
		
		String url="orderOkList.jsp";
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		OrderDAO odao=OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectOrderList(userid);
		request.setAttribute("olist", olist);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
			
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
