package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import dto.ItemVO;

/**
 * Servlet implementation class itemDeleteServlet
 */
@WebServlet("/itemDelete.do")
public class ItemDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemDeleteServlet() {
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

		String str = "item/itemDelete3.jsp";
		String item_num = request.getParameter("item_num");

		ItemDAO iDao3 = ItemDAO.getInstance();

		if (item_num == null) {
			iDao3.deleteAllItems();
			str = "item/itemList3.jsp";
		}

		ItemVO iVo3 = iDao3.selectItemByItem_num(item_num);
		request.setAttribute("item", iVo3);

		RequestDispatcher dispatcher = request.getRequestDispatcher(str);
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

		ItemDAO iDao3 = ItemDAO.getInstance();
		iDao3.deleteItem(item_num);
		response.sendRedirect("itemList3.do");
	}

}
