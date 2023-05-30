package jp.co.aforce.login;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.UserBean;
import jp.co.aforce.dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.setAttribute("error", false);
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserDAO dao = new UserDAO();
		try {
			UserBean user = dao.getUser(id, password);
			
			if(Objects.isNull(user)) {
				request.setAttribute("error", true);
				request.setAttribute("id", id);
				request.getRequestDispatcher("/jsp/login.jsp")
					.forward(request, response);
				return;
			}
			HttpSession ses = request.getSession();
			ses.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("/jsp/loginSuccess.jsp")
			.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		ses.removeAttribute("user");
		req.setAttribute("logout", true);
		
		req.getRequestDispatcher("/jsp/login.jsp")
			.forward(req, resp);
	}
}
