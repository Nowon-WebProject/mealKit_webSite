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
 * Servlet implementation class bbsViewServlet
 */
@WebServlet("/bbsView.do")
public class bbsViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bbsViewServlet() {
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
		String bbsid = request.getParameter("bbsid");
		String url = "";
		String update = request.getParameter("update");
		
		String file1 = "";
		String file2 = "";
		int count = 0;
		
		BbsDAO bdao=BbsDAO.getInstance();
		BbsDTO bdto=new BbsDTO();
		Vector<BbsDTO> vec=new Vector<BbsDTO>();
		
			bdto=bdao.findUser(bbsid);
			vec.add(bdto);
			
			if(update == null || update =="") { url = "/managePage/bbsView.jsp";
			bdto.setBbscount(bdto.getBbscount() + 1);
			int result = bdao.bbscount(bdto);
			}else {url = "/managePage/bbsUpdate.jsp"; }
			
			if(bdto.getBbsimg() == "" || bdto.getBbsimg() == null) {}
			else {
			for(int i = 0; i<bdto.getBbsimg().length(); i++) {
				if(bdto.getBbsimg().charAt(i) == ',') {count = i;}
			}
			
			if( count != 0) {
				for(int i=0; i<count; i++) { file1 = file1 + bdto.getBbsimg().charAt(i);}
				for(int i=count+1; i<bdto.getBbsimg().length();i++) {file2= file2 + bdto.getBbsimg().charAt(i);}
			}else { file1 = bdto.getBbsimg();}
			}
		
		//String content = bdto.getBbscontent();
		//String title = bdto.getBbstitle();
			request.setAttribute("file1", file1);
			request.setAttribute("file2", file2);
			request.setAttribute("vec", vec);
			
			RequestDispatcher dispatcher=request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		
		
	}

}
