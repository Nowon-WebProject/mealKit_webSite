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

import kr.co.EZHOME.dao.BbsDAO;
import kr.co.EZHOME.dto.BbsDTO;

/**
 * Servlet implementation class bbsUpdateServlet
 */
@WebServlet("/bbsUpdate.do")
public class bbsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bbsUpdateServlet() {
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
		BbsDTO bdto=new BbsDTO();
		
		ServletContext context = getServletContext();
		String path = context.getRealPath("img");
		int size = 5 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request,path,size,"UTF-8",new DefaultFileRenamePolicy());
		String bbsid=multi.getParameter("bbsid");
		String bbstitle=multi.getParameter("bbstitle");
		String bbscontent=multi.getParameter("bbscontent");
		String file1 = multi.getFilesystemName("file1");
		String file2 = multi.getFilesystemName("file2");
		bbscontent = bbscontent.replace("\n", "<br>");
	
		bdto.setBbsid(Integer.parseInt(bbsid));
		bdto.setBbstitle(bbstitle);
		bdto.setBbscontent(bbscontent);
		
		if((file1 == null || file1 =="" )&& (file2 == null || file2 =="")) {
		}else if(file1 == null || file1 =="") { bdto.setBbsimg(file2);
		}else if(file2 == null || file2 =="") { bdto.setBbsimg(file1);
		}else {bdto.setBbsimg(file1 + "," + file2);}
		
		
		int result = bdao.updateMember(bdto);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("bbsList.do");
		dispatcher.forward(request, response);
		
		
	}

}
