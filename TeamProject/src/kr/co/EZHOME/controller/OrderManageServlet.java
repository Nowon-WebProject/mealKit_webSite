package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.EZHOME.dao.OrderDAO;
import kr.co.EZHOME.dto.OrderDTO;


/**
 * Servlet implementation class MovieList
 */
@WebServlet("/orderManage.do")
public class OrderManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManageServlet() {
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
		
		// 모든 유저의 결제정보를 출력하기 위한 서블릿
		
		OrderDAO odao=OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectAllOrderList();
		request.setAttribute("olist", olist);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("orderManage.jsp");
		dispatcher.forward(request, response);
	}

}
