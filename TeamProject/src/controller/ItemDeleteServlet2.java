package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO2;
import dto.ItemVO2;

/**
 * Servlet implementation class itemDeleteServlet
 */
@WebServlet("/itemDelete2.do")
public class ItemDeleteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDeleteServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	
		String item_num = request.getParameter("item_num");
		ItemDAO2 iDao2 = ItemDAO2.getInstance();
		ItemVO2 iVo2 = iDao2.selectItemByItem_num(item_num);
		request.setAttribute("item", iVo2);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("item/itemDelete2.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		String item_num = request.getParameter("item_num");
		
		ItemDAO2 iDao2 = ItemDAO2.getInstance();
		iDao2.deleteItem(item_num);
		response.sendRedirect("itemList2.do");
	}

}
