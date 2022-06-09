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

		ItemVO iVo = new ItemVO();
		iVo.setItem_category(item_category);
		iVo.setItem_name(item_name);
		iVo.setItem_content(item_content);
		iVo.setItem_price(Integer.parseInt(item_price));
		iVo.setItem_quantity(Integer.parseInt(item_quantity));
		iVo.setItem_total(item_total);
		iVo.setItem_time(item_time);
		iVo.setItem_main(item_main);
		iVo.setItem_sales(Integer.parseInt(item_sales));
		iVo.setItem_discount(Double.parseDouble(item_discount));
		iVo.setItem_starsAvg(Double.parseDouble(item_starsAvg));
		
		iVo.setItem_pictureUrl1(item_pictureUrl1);
		iVo.setItem_pictureUrl2(item_pictureUrl2);
		
		ItemDAO iDao = ItemDAO.getInstance();
		iDao.insertItem(iVo);

		response.sendRedirect("itemList.do");
	}

}
