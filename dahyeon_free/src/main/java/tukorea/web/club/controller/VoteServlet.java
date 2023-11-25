package tukorea.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tukorea.web.club.domain.voteVO;
import tukorea.web.club.domain.memberVO;
import tukorea.web.club.persistance.voteDAO;

@WebServlet("/voteServlet")
public class VoteServlet extends HttpServlet {
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

		if (cmdReq.equals("generate")) {
			response.sendRedirect("voteGenerate.html");
		} else if (cmdReq.equals("voteList")) {
			voteDAO dao = new voteDAO();
			ArrayList<voteVO> voteList = dao.getvoteList();
			request.setAttribute("voteList", voteList);
			RequestDispatcher view = request.getRequestDispatcher("voteList.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("voteDelete")) {
			voteDAO dao = new voteDAO();
			String voteNum = request.getParameter("voteNum");
			dao.delete(voteNum);

			ArrayList<voteVO> voteList = dao.getvoteList();
			request.setAttribute("voteList", voteList);
			RequestDispatcher view = request.getRequestDispatcher("voteList.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("voteUpdate")) {
			voteDAO dao = new voteDAO();
			voteVO voteNum = dao.voteRead(request.getParameter("voteNum"));
			request.setAttribute("voteNum", voteNum);
			RequestDispatcher view = request.getRequestDispatcher("voteUpdate.jsp");
			view.forward(request, response);
		} else if (cmdReq.equals("updateCount")) {
			updateCount(request, response);
			voteDAO dao = new voteDAO();
			ArrayList<voteVO> voteList = dao.getvoteList();
			request.setAttribute("voteList", voteList);
			RequestDispatcher view = request.getRequestDispatcher("voteList.jsp");
			view.forward(request, response);
		}
	}

	private void updateCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String voteNumParam = request.getParameter("voteNum");
		String type = request.getParameter("type");

		if (voteNumParam != null && type != null) {
			int voteNum = Integer.parseInt(voteNumParam);

			// Update the count in the database using your DAO
			voteDAO voteDAO = new voteDAO();
			voteDAO.updateCount(voteNum, type);

			// Return a success message or updated count if needed
			System.out.println("+1");
		} else {
			// Handle invalid parameters
			response.getWriter().write("Invalid parameters");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String cmdReq = "";
		cmdReq = request.getParameter("cmd");

		if (cmdReq.equals("voteGenerateComplete")) {
			HttpSession session = request.getSession();
			memberVO loggedInUser = (memberVO) session.getAttribute("loggedInUser");

			if (loggedInUser != null) {
				String memberID = loggedInUser.getId();

				// Create a voteVO object and set its properties
				voteVO voteVO = new voteVO();
				voteDAO voteDAO = new voteDAO();
				voteVO.setVoteNum(voteDAO.maxVoteNum() + 1);
				voteVO.setVoteTitle(request.getParameter("voteTitle"));
				voteVO.setVoteContent(request.getParameter("voteContent"));
				voteVO.setMemberID(memberID);
				voteVO.setGoodCount(0);
				voteVO.setBadCount(0);
				// Set other properties of voteVO as needed
				// Call the addVote method of voteDAO with the memberID

				String message = "";
				if (voteDAO.addVote(voteVO, memberID, voteDAO.maxVoteNum() + 1))
					message = "투표글 작성을 축하합니다!";
				else
					message = "투표글 작성 실패입니다.";
				request.setAttribute("greetings", message);
				request.setAttribute("vote", voteVO);
				RequestDispatcher view = request.getRequestDispatcher("voteGenerateComplete.jsp");
				view.forward(request, response);
			} else {
				// Redirect to a page indicating the user is not logged in
				response.sendRedirect("login.html");
			}
		} else if (cmdReq.equals("voteUpdate")) {
			voteVO voteVO = new voteVO();

			voteVO.setVoteTitle(request.getParameter("voteTitle"));
			voteVO.setVoteContent(request.getParameter("voteContent"));
			voteVO.setMemberID(request.getParameter("memberID"));

			voteDAO dao = new voteDAO();

			String greeting = "";
			if (dao.voteUpdate(voteVO, request.getParameter("voteNum")))
				greeting = "수정이 완료되었습니다.";
			else
				greeting = "수정 실패입니다.";

			request.setAttribute("greetings", greeting);
			request.setAttribute("vote", voteVO);

			RequestDispatcher view = request.getRequestDispatcher("voteGenerateComplete.jsp");
			view.forward(request, response);
		}
	}
}
