package jp.co.aforce.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/sign-up")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDAO dao = new UserDAO();
		try {
			int line = dao.createUser(id, password);
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.setAttribute("id", id);
			request.getRequestDispatcher("/jsp/signup.jsp")
				.forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("/jsp/sign-upSuccess.jsp")
		.forward(request, response);
	}

}
