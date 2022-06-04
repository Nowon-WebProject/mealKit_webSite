package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.domain.DAOResult;
import kr.co.EZHOME.domain.User;

/**
 * Servlet implementation class updatePasswordServlet
 */
@WebServlet("/updatePassword.do")
public class updatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String password = request.getParameter("pwd");
		String userId = request.getParameter("userId");
		UserDAO userDAO = UserDAO.getInstance();
		String message;
		
		//1. 비밀번호가 이전 비밀번호와 일치하는지 확인하기
		try {
			User user = userDAO.findUser(userId);
			if (user.getPassword().equals(password)) {
				message = "비밀번호가 이전 비밀번호와 같습니다. 새로운 비밀번호를 입력해주세요";
				request.setAttribute("message", message);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/login/findPasswordResult.jsp");
				dispatcher.forward(request, response);
				return ;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//2. 일치하지 않다면 비밀번호 변경하기
		DAOResult result = userDAO.updatePassword(userId, password);
		
		if (result == DAOResult.Success) {
			message = "비밀번호 변경에 성공했습니다";
		}
		else {
			message = "비밀번호 변경에 실패했습니다";
		}
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
		dispatcher.forward(request, response);
	}

}
