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
</body>
</html>