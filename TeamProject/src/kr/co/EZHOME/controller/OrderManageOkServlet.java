package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dao.OrderDAO;


/**
 * Servlet implementation class MovieList
 */
@WebServlet("/orderManageOk.do")
public class OrderManageOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderManageOkServlet() {
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
		
		// 관리자가 변경할 배송상태와 변경할 주문정보를 체크하여 값이 넘어옴.
		// 해당하는 주문정보들의 배송상태를 넘어온 값으로 변경함.
		
		request.setCharacterEncoding("utf-8");

		OrderDAO odao = OrderDAO.getInstance();
		String[] num = request.getParameterValues("order_num");
		String deli_status = request.getParameter("deli_status");
		
		for(int i=0;i<num.length;i++) {
		odao.updateDeli_Status(deli_status, num[i]);
		}
				
		ItemDAO idao = ItemDAO.getInstance();


		RequestDispatcher dispatcher = request.getRequestDispatcher("orderManage.do");
		dispatcher.forward(request, response);
	}

}
