package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ItemDAO;
import dto.ItemVO;

/**
 * Servlet implementation class itemWriteServlet
 */
@WebServlet("/itemWrite.do")
public class ItemWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemWriteServlet() {
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("item/itemWrite.jsp");
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

		request.setCharacterEncoding("utf-8");
		String item_category = request.getParameter("item_category");
		String item_name = request.getParameter("item_name");
		String item_content = request.getParameter("item_content");
		String item_price = request.getParameter("item_price");
		String item_quantity = request.getParameter("item_quantity");
		String item_total = request.getParameter("item_total");
		String item_time = request.getParameter("item_time");
				
		ItemVO iVo = new ItemVO();
		iVo.setItem_category(item_category);
		iVo.setItem_name(item_name);
		iVo.setItem_content(item_content);
		iVo.setItem_price(Integer.parseInt(item_price));
		iVo.setItem_quantity(Integer.parseInt(item_quantity));
		iVo.setItem_total(item_total);
		iVo.setItem_time(item_time);
		
		ItemDAO iDao = ItemDAO.getInstance();
		iDao.insertItem(iVo);

		response.sendRedirect("itemList.do");
	}

}
