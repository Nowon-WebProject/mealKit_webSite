package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.dto.UserDTO;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
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
		String update = request.getParameter("update");	
		String  name=request.getParameter("name");
		String  userid=request.getParameter("userid");
		String  pwd=request.getParameter("pwd");
		String  email=request.getParameter("email");
		String  phone=request.getParameter("phone");
		String  admin=request.getParameter("admin");
		
		if(name!=null) {
			UserDTO udto=new UserDTO(name,userid,pwd,email,phone,Integer.parseInt(admin));
			udto.setName(name);
			udto.setUserid(userid);
			udto.setPwd(pwd);
			udto.setEmail(email);
			udto.setPhone(phone);
			udto.setAdmin(Integer.parseInt(admin));
			
			UserDAO udao=UserDAO.getInstance();
			udao.updateMember(udto);
			
			
			
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher=request.getRequestDispatcher("/managePage/memberSearch.jsp");
			 dispatcher.forward(request, response);
			
		}else {
		request.setAttribute("update", update);
		
		ServletContext context = getServletContext();
		 RequestDispatcher dispatcher = context.getRequestDispatcher("//managePage/memberUpdate.jsp");
		 
		 dispatcher.forward(request, response);
		}
		}
		
		
		
	}


