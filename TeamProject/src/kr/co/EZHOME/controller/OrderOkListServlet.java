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
import kr.co.EZHOME.dao.ItemDAO;
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
		
		String pageNum = request.getParameter("pageNum");
		int pageSize =Integer.parseInt(request.getParameter("pageSize"));
		int currentPage = Integer.parseInt(pageNum);
		ItemDAO idao = ItemDAO.getInstance();
		
		// 현재 페이지에 보여 줄 시작 번호를 설정 ==> 데이터 베이스에서 불러올 시작 번호
		int startRow = (currentPage - 1) * pageSize + 1; // 1 11 21 31
		int endRow = currentPage * pageSize; // 10 20 30 40
		
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		
		OrderDAO odao=OrderDAO.getInstance();
		ArrayList<OrderDTO> olist = odao.selectOrderList(userid, startRow, endRow);
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
