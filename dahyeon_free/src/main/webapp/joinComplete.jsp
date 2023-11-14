<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/vote.css" type="text/css"></link>
</head>
<body>
	<header>
		TUKOREA 2023 <br> Web Service Programming Community
	</header>
	<%
		memberVO student = (memberVO) request.getAttribute("member");
	%>
	<p id=sect><%=request.getAttribute("greeting")%><br>
	<div class=div>
		<p id=sect>커뮤니티 가입을 축하합니다.</p>
		<div id=center>
			<table>
				<tr>
					<td id=yellow>계정</td>
					<td id=yellow>이름</td>
					<td id=yellow>닉네임</td>
					<td id=yellow>핸드폰</td>
					<td id=yellow>이메일</td>
				</tr>
				<tr>
					<td><%=student.getId()%></td>
					<td><%=student.getUsername()%></td>
					<td><%=student.getNickname()%></td>
					<td><%=student.getMobile()%></td>
					<td><%=student.getEmail()%></td>
				</tr>
			</table>
		</div>
		<a href="http://localhost:8080/dahyeon_free/memberServlet?cmd=list"
			target="_self">전체 회원 목록 보기 </a>
	</div>
</body>
</html>