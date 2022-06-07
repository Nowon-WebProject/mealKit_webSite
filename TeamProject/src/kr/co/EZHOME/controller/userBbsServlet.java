package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.BbsDAO;
import kr.co.EZHOME.dto.BbsDTO;

/**
 * Servlet implementation class userBbsServlet
 */
@WebServlet("/userBbs.do")
public class userBbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userBbsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		BbsDAO bdao=BbsDAO.getInstance();
		Vector<BbsDTO> vec=new Vector<BbsDTO>();
		Vector<BbsDTO> vec1=new Vector<BbsDTO>();
		String page =request.getParameter("page");
		String size = request.getParameter("size");
		
		if(page == null || page == "") {page = "1";}
		if(size == null || size == "") {size = "10";}
		int pageNum = Integer.parseInt(page);
		int sizeNum = Integer.parseInt(size);
		String[] arr = {"","",""};
		
		switch(sizeNum) {
		case 10 : arr[0] = "selected"; break;
		case 15 : arr[1] = "selected"; break;
		case 20 : arr[2] = "selected"; break;
		}
		
		/*1 2 3 4
		
		1 11 21 31
		
		10 20 30 40*/
		vec = bdao.bbsList();

		int all = vec.size();
		int count = 0;
		if(all % sizeNum !=0 ){ count = 1;}
		all = all /sizeNum;
		if(count == 1) {all = all+1;}
			
		int endNum = pageNum * sizeNum;
		if(endNum > vec.size()) {endNum = vec.size();}
		int startNum = endNum - sizeNum + 1;
		
		for(int i=startNum; i<=endNum ; i++) {
			BbsDTO bdto=new BbsDTO();
			bdto=null;
			bdto=vec.get(i-1);
			vec1.add(bdto);
		}
	
		int start = ((pageNum / 10) * 10) + 1;
		int end = start + 9 ;
		if(end > all) {end = all;}

		request.setAttribute("page", page);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("all", all);
		request.setAttribute("vec", vec1);
		request.setAttribute("arr", arr);
		
		
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/managePage/userbbs.jsp");
		dispatcher.forward(request, response);
		
	}

}
