package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.PostScriptDAO;
import dto.PostScriptVO;

/**
 * Servlet implementation class PostScriptWriteServlet
 */
@WebServlet("/postWrite.do")
public class PostScriptWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostScriptWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		request.setCharacterEncoding("utf-8");
		ServletContext context = getServletContext();
		String path = context.getRealPath("images/product");
		String encType = "utf-8";
		int sizeLimit = 20 * 1024 * 1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
				encType, new DefaultFileRenamePolicy());
		String post_subject = multi.getParameter("post_subject");
		String post_writer = multi.getParameter("post_writer");
		double post_stars = Double.parseDouble(multi.getParameter("post_stars"));
		String post_content = multi.getParameter("post_content");

		String post_image = multi.getFilesystemName("post_image");
		
		PostScriptVO pVo = new PostScriptVO();
		pVo.setPost_subject(post_subject);
		pVo.setPost_writer(post_writer);
		pVo.setPost_help(0);
		pVo.setPost_hits(0);
		pVo.setPost_stars(post_stars);
		pVo.setPost_content(post_content);
		pVo.setPost_image(post_image);

		PostScriptDAO pDao = PostScriptDAO.getInstance();
		pDao.insertPostScript(pVo);

		response.sendRedirect("post/postScript2.jsp");
	}

}
