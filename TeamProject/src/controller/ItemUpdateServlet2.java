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

import dao.ItemDAO2;
import dto.ItemVO2;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/itemUpdate2.do")
public class ItemUpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemUpdateServlet2() {
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
		
		ItemDAO2 iDao2 = ItemDAO2.getInstance();
		ItemVO2 iVo2 = iDao2.selectItemByItem_num(item_num);

		request.setAttribute("item", iVo2);
		RequestDispatcher dispatcher = request.getRequestDispatcher("item/itemUpdate2.jsp");
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
		ServletContext context = getServletContext();
		String path = context.getRealPath("upload");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());
		
		String item_category = multi.getParameter("item_category");
		String item_name = multi.getParameter("item_name");
		String item_content = multi.getParameter("item_content");
		String item_price = multi.getParameter("item_price");
		String item_quantity = multi.getParameter("item_quantity");
		String item_total = multi.getParameter("item_total");
		String item_time = multi.getParameter("item_time");
		String item_pictureUrl = multi.getFilesystemName("item_pictureUrl");
		String item_num = multi.getParameter("item_num");
		
		if (item_pictureUrl == null) {
			item_pictureUrl = multi.getParameter("nonmakeImg");
		}
		
		ItemVO2 iVo2 = new ItemVO2();
		iVo2.setItem_category(item_category);
		iVo2.setItem_name(item_name);
		iVo2.setItem_content(item_content);
		iVo2.setItem_price(Integer.parseInt(item_price));
		iVo2.setItem_quantity(Integer.parseInt(item_quantity));
		iVo2.setItem_total(item_total);
		iVo2.setItem_time(item_time);
		iVo2.setItem_pictureUrl(item_pictureUrl);
		iVo2.setItem_num(Integer.parseInt(item_num));

		ItemDAO2 iDao2 = ItemDAO2.getInstance();
		iDao2.updateItem(iVo2);

		response.sendRedirect("itemList2.do");
	}

}
