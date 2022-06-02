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

import dao.ItemDAO3;
import dto.ItemVO3;

/**
 * Servlet implementation class itemWriteServlet
 */
@WebServlet("/itemWrite3.do")
public class ItemWriteServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemWriteServlet3() {
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("item/itemWrite3.jsp");
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
		String item_main = multi.getParameter("item_main");
		String item_sales = multi.getParameter("item_sales");
		String item_discount = multi.getParameter("item_discount");
		String item_starsAvg = multi.getParameter("item_starsAvg");
		String item_pictureUrl1 = multi.getFilesystemName("item_pictureUrl1");
		String item_pictureUrl2 = multi.getFilesystemName("item_pictureUrl2");

		ItemVO3 iVo3 = new ItemVO3();
		iVo3.setItem_category(item_category);
		iVo3.setItem_name(item_name);
		iVo3.setItem_content(item_content);
		iVo3.setItem_price(Integer.parseInt(item_price));
		iVo3.setItem_quantity(Integer.parseInt(item_quantity));
		iVo3.setItem_total(item_total);
		iVo3.setItem_time(item_time);
		iVo3.setItem_main(item_main);
		iVo3.setItem_sales(Integer.parseInt(item_sales));
		iVo3.setItem_discount(Double.parseDouble(item_discount));
		iVo3.setItem_starsAvg(Double.parseDouble(item_starsAvg));
		
		iVo3.setItem_pictureUrl1(item_pictureUrl1);
		iVo3.setItem_pictureUrl2(item_pictureUrl2);
		
		ItemDAO3 iDao3 = ItemDAO3.getInstance();
		iDao3.insertItem(iVo3);

		response.sendRedirect("itemList3.do");
	}

}
