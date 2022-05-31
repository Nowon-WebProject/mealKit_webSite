package kr.co.EZHOME.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.EZHOME.dao.UserDAO;
import kr.co.EZHOME.domain.DataStatus;

/**
 * Servlet implementation class findPasswordServlet
 */
@WebServlet("/findPassword.do")
public class findPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findPasswordServlet() {
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
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String phone = request.getParameter("checkedPhone");
		String message = null;
		DataStatus result;
		
		UserDAO userDAO = UserDAO.getInstance();
		
		result = userDAO.checkPhoneUser(name, phone, userId);
		
		//해당 이름, 휴대폰번호, 아이디 에 맞는 DB가 존재했다면
		if (result == DataStatus.Exist) {
			message = "새로운 비밀번호를 입력해 주세요";
		}
		else {
			message = "입력하신 정보와 일치하는 회원정보가 없습니다.";
		}
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login/findPasswordResult.jsp");
		dispatcher.forward(request, response);
	}

}
