<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.memberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="java.io.PrintWriter"%>
	<%
	memberVO loggedInUser = (memberVO) session.getAttribute("loggedInUser");
	if (loggedInUser != null) {
		out.println("User is logged in. Username: " + loggedInUser.getUsername());
	} else {
		out.println("No user is currently logged in.");
	}
	%>
	<a href="http://localhost:8080/dahyeon_free/voteServlet?cmd=generate"
		target="_self">투표글 작성하러가기</a>
	<a href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteList"
		target="_self">전체 투표 목록 보기 </a>
</body>
</html>