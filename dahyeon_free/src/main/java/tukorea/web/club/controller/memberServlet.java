package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tukorea.web.club.domain.memberVO;
import tukorea.web.club.persistance.memberDAO;

@WebServlet("/memberServlet")
public class memberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");

		if (cmdReq.equals("join")) {
			response.sendRedirect("register.html");
		} else if (cmdReq.equals("list")) {
			memberDAO dao = new memberDAO();
			ArrayList<memberVO> memberList = dao.getmemberList();
			request.setAttribute("memberList", memberList);
			RequestDispatcher view = request.getRequestDispatcher("voteList.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("delete")) {
			memberDAO dao = new memberDAO();
			String strId = request.getParameter("id");
			dao.delete(strId);

			ArrayList<memberVO> studentList = dao.getmemberList();
			request.setAttribute("studentList", studentList);
			RequestDispatcher view = request.getRequestDispatcher("votelist.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("update")) {
			memberDAO dao = new memberDAO();
			memberVO student = dao.read(request.getParameter("id"));
			request.setAttribute("student", student);
			RequestDispatcher view = request.getRequestDispatcher("update.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		if(cmdReq.equals("join")) {
			memberVO memberVO = new memberVO();
			
			memberVO.setId(request.getParameter("id"));
			memberVO.setPasswd(request.getParameter("passwd"));
			memberVO.setUsername(request.getParameter("username"));
			memberVO.setNickname(request.getParameter("nickname"));
			memberVO.setMobile(request.getParameter("mobile"));
			memberVO.setEmail(request.getParameter("email"));
			
			memberDAO memberDao = new memberDAO();
			
			String message = "";
			if(memberDao.addMember(memberVO)) message = "가입 축하합니다!";
			else message = "가입 실패입니다.";
			
			request.setAttribute("greeting", message);
			request.setAttribute("student", memberVO);
			
			RequestDispatcher view = request.getRequestDispatcher("joinComplete.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("update")) {
			memberVO memberVO = new memberVO();
			
			memberVO.setId(request.getParameter("id"));
			memberVO.setPasswd(request.getParameter("passwd"));
			memberVO.setUsername(request.getParameter("username"));
			memberVO.setNickname(request.getParameter("nickname"));
			memberVO.setMobile(request.getParameter("mobile"));
			memberVO.setEmail(request.getParameter("email"));
			
			memberDAO dao = new memberDAO();
			
			String message = "";
			if(dao.update(memberVO)) message = "수정이 완료되었습니다.";
			else message = "수정 실패입니다.";
			
			request.setAttribute("greetings", message);
			request.setAttribute("student", memberVO);
			
			RequestDispatcher view = request.getRequestDispatcher("joinComplete.jsp");
			view.forward(request, response);
		}	
	}
}