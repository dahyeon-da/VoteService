package tukorea.web.club.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tukorea.web.club.domain.memberVO;
import tukorea.web.club.persistance.memberDAO;

@WebServlet("/RestServlet")
public class restServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public restServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		PrintWriter out = response.getWriter();

		String cmdReq;
		cmdReq = request.getParameter("cmd");
		if (cmdReq == null)
			return;

		memberDAO memberDAO = new memberDAO();
		JSONArray arrayJson = new JSONArray();

		if (cmdReq.equals("list")) {
			try {
				List<memberVO> memberList = memberDAO.getmemberList();
				for (memberVO vo : memberList) {
					JSONObject json = new JSONObject();
					json.put("passwd", vo.getPasswd());
					json.put("username", vo.getUsername());
					json.put("nickname", vo.getNickname());
					json.put("id", vo.getId());
					json.put("mobile", vo.getMobile());
					json.put("email", vo.getEmail());
					arrayJson.put(json);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(arrayJson);
		}
		if (cmdReq.equals("read")) {
			try {
				String id = request.getParameter("id");
				if (id == null) {
					out.print("계정을 확인하세요.");
					return;
				}
				memberVO vo = memberDAO.read(id);
				JSONObject json = new JSONObject();
				json.put("id", vo.getId());
				json.put("passwd", vo.getPasswd());
				json.put("username", vo.getUsername());
				json.put("nickname", vo.getNickname());
				json.put("mobile", vo.getMobile());
				json.put("email", vo.getEmail());
				arrayJson.put(json);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			out.print(arrayJson);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}