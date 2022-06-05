package kr.co.EZHOME.controller;

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

import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dto.ItemDTO;

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
		System.out.println(multi.getParameter("item_category"));
		System.out.println(multi.getParameter("item_content"));
		if(item_category.equals("new")) {
		item_category = multi.getParameter("NewCategory");
		}
		String item_name = multi.getParameter("item_name");
		String item_content = multi.getParameter("item_content");
		String item_price = multi.getParameter("item_price");
		String item_quantity = multi.getParameter("item_quantity");
		String item_total = multi.getParameter("item_total");
		String item_time = multi.getParameter("item_time");
		String item_main = multi.getParameter("item_main");
		int item_sales = Integer.parseInt(multi.getParameter("item_sales"));
		String item_pictureUrl1 = multi.getFilesystemName("item_pictureUrl1");
		String item_pictureUrl2 = multi.getFilesystemName("item_pictureUrl2");

		ItemDTO iVo3 = new ItemDTO();
		iVo3.setItem_category(item_category);
		iVo3.setItem_name(item_name);
		iVo3.setItem_content(item_content);
		iVo3.setItem_price(Integer.parseInt(item_price));
		iVo3.setItem_quantity(Integer.parseInt(item_quantity));
		iVo3.setItem_total(item_total);
		iVo3.setItem_time(item_time);
		iVo3.setItem_main(item_main);
		iVo3.setItem_sales(item_sales);
		
		iVo3.setItem_pictureUrl1(item_pictureUrl1);
		iVo3.setItem_pictureUrl2(item_pictureUrl2);
		
		ItemDAO iDao3 = ItemDAO.getInstance();
		iDao3.insertItem(iVo3);

		response.sendRedirect("index.jsp");
	}

}
