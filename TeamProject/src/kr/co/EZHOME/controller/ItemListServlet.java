package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.ItemDAO;
import kr.co.EZHOME.dto.ItemDTO;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/itemList.do")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemListServlet() {
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
		
		
		// 상품 리스트 출력 서블릿
		// itemList.jsp에도 자바코드가 많이 사용되고 있음..
		// 카테고리 설정, 키워드 검색, ~개씩 보기, 가격순 정렬, 리스트(카드)형 보기 등 조건을 선택할 때 마다
		// 해당 조건에 맞는 리스트만 ilist에 담아 넘김. 
		
		

		request.setCharacterEncoding("utf-8");
		
		// 화면에 보여질 게시글의 개수를 지정
		int pageSize =Integer.parseInt(request.getParameter("pageSize"));
		String check = "";
		String category = "empty";
		String priceSort = "default";
		String view = "list";
		
		view = request.getParameter("view");
		priceSort = request.getParameter("priceSort");
		check =request.getParameter("check");
		category =request.getParameter("category");
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}

		int count = 0; // 전체 글의 개수 저장

		int currentPage = Integer.parseInt(pageNum);
		ItemDAO idao = ItemDAO.getInstance();
		
		// 현재 페이지에 보여 줄 시작 번호를 설정 ==> 데이터 베이스에서 불러올 시작 번호
		int startRow = (currentPage - 1) * pageSize + 1; // 1 11 21 31
		int endRow = currentPage * pageSize; // 10 20 30 40

		String keyword = "";
		
		if (check.equals("all")) {
			Vector<ItemDTO> vec = idao.getAllItem(startRow, endRow);
			request.setAttribute("ilist", vec);
			String title = "전체 밀키트";
			check = "all";
			request.setAttribute("title", title);
			request.setAttribute("check", check);

			keyword = request.getParameter("keyword");

			if (keyword != null) {
				vec = idao.itemSearch(keyword, category, check, priceSort, startRow, endRow);
				request.setAttribute("ilist", vec);
			}

		} else if (check.equals("new")) {
			Vector<ItemDTO> vec = idao.getNewItem(startRow, endRow);
			request.setAttribute("ilist", vec);
			String title = "신상 밀키트";
			request.setAttribute("title", title);
			check = "new";
			request.setAttribute("check", check);

			keyword = request.getParameter("keyword");
			if (keyword != null) {
				vec = idao.itemSearch(keyword, category, check, priceSort, startRow, endRow);
				request.setAttribute("ilist", vec);
			}

		} else if (check.equals("best")) {
			Vector<ItemDTO> vec = idao.getBestItem(startRow, endRow);
			request.setAttribute("ilist", vec);
			String title = "인기 밀키트";
			request.setAttribute("title", title);
			check = "best";
			request.setAttribute("check", check);

			keyword = request.getParameter("keyword");
			if (keyword != null) {
				vec = idao.itemSearch(keyword, category, check, priceSort, startRow, endRow);
				request.setAttribute("ilist", vec);
			}

		}
		request.setAttribute("view", view);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("currentPage", currentPage);
		
		Vector<ItemDTO> categoryList = idao.category();
		request.setAttribute("categoryList", categoryList);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("itemList.jsp");
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

	}

}
