package tukorea.web.club.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tukorea.web.club.domain.memberVO;
import tukorea.web.club.service.loginService;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");

		if (cmdReq.equals("login")) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");

			// Create a user object with the submitted ID and password
			memberVO vo = new memberVO();
			vo.setId(id);
			vo.setPasswd(passwd);

			HttpSession session = request.getSession();
			session.setAttribute("loggedInUser", vo);
			session.setMaxInactiveInterval(1800);
			
			// Create an instance of the LoginService
			loginService loginService = new loginService();

			// Authenticate the user
			boolean isAuthenticated = loginService.authenticate(vo);

			if (isAuthenticated) {
				// Redirect to a success page
				response.sendRedirect("loginComplete.jsp");
			} else {
				// Redirect to a failure page
				response.sendRedirect("loginFail.jsp");
			}
		}
	}
}