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
 * Servlet implementation class findIdServlet
 */
@WebServlet("/findId.do")
public class findIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findIdServlet() {
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
		String userId;
		String name = request.getParameter("name");
		String phone = request.getParameter("checkedPhone");
		String message;
		//DB에 접근해서 해당 이름과 핸드폰 번호를 가진 유저가 존재하는지 확인
		UserDAO userDAO = UserDAO.getInstance();
		userId = userDAO.checkPhoneUser(name, phone);
		if (userId != null) {
			message = "회원님의 아이디는 " + userId + "입니다";
		}
		else {
			message = "회원님의 아이디가 존재하지 않습니다";
		}
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/login/findIdResult.jsp");
		dispatcher.forward(request, response);
	}

}
