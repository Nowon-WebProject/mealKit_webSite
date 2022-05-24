package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO3;
import dto.ItemVO3;

/**
 * Servlet implementation class itemDeleteServlet
 */
@WebServlet("/itemDelete3.do")
public class ItemDeleteServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDeleteServlet3() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String item_num = request.getParameter("item_num");
		/*
		if (item_num == null)
			request.setAttribute("deleteAll", true);
		*/
		
		ItemDAO3 iDao3 = ItemDAO3.getInstance();
		ItemVO3 iVo3 = iDao3.selectItemByItem_num(item_num);
		request.setAttribute("item", iVo3);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("item/itemDelete3.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		String item_num = request.getParameter("item_num");

		ItemDAO3 iDao3 = ItemDAO3.getInstance();
		iDao3.deleteItem(item_num);
		response.sendRedirect("itemList3.do");
	}

}
