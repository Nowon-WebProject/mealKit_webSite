package kr.co.EZHOME.controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.UserDTO;
import kr.co.EZHOME.dto.UserVO;

/**
 * Servlet implementation class memberSearchServlet
 */
@WebServlet("/memberSearch.do")
public class memberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberSearchServlet() {
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
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		String delete=request.getParameter("delete");
		
		
		String[] arr= {"","",""};
		
		UserDAO mdao = UserDAO.getInstance();
		
		if(delete!=null) {
			try {mdao.deleteMember(delete);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		
		if(type.equals("userid")) {
			arr[0]="selected";
		}else if(type.equals("name")) {
			arr[1]="selected";
		}else if(type.equals("phone")) {
			arr[2]="selected";
		}
			
		Vector<UserVO> vec = mdao.MemberSearch(type, key);
		
		request.setAttribute("vec", vec);
		request.setAttribute("arr", arr);
		request.setAttribute("delete", delete);
		
		ServletContext context = getServletContext();
		 RequestDispatcher dispatcher = context.getRequestDispatcher("//managePage/memberSearch2.jsp");
		 
		 dispatcher.forward(request, response);
	}

}
