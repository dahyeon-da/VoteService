<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.memberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<header>Member Update</header>
		<HR>
		<form
			action="http://localhost:8080/dahyeon_free/memberServlet?cmd=update"
			method="post">
			<%
			memberVO member = (memberVO) request.getAttribute("member");
			%>
			<fieldset>
				<legend>Information Update</legend>
				<ul>
					<li>ID : <input type="text" name="id"
						value=<%=member.getId()%> readonly></li>
					<li>PASSWORD : <input type="password" name="passwd"
						value=<%=member.getPasswd()%> autofocus></li>
					<li>USERNAME : <input type="text" name="username"
						value=<%=member.getUsername()%>></li>
					<li>NICKNAME : <input type="text" name="nickname"
						value=<%=member.getNickname()%>></li>
					<li>MOBILE : <input type="text" name="mobile"
						value=<%=member.getMobile()%>></li>
					<li>EMAIL : <input type="text" name="email"
						value=<%=member.getEmail()%>></li>
				</ul>
			</fieldset>
			<br>
			<fieldset>
				<input type="submit" name="submit" value="최종 수정"> <input
					type="reset" name="reset" value="다시 작성">
			</fieldset>
		</form>
	</div>
</body>
</html>