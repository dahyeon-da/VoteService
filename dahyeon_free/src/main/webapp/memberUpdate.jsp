<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.memberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/vote.css" type="text/css"></link>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=AR+One+Sans:wght@500&family=Black+Han+Sans&family=Dongle&family=Gowun+Dodum&family=Hi+Melody&family=Jua&family=Nanum+Gothic:wght@800&family=Open+Sans&family=Poor+Story&display=swap')
	;
</style>
</head>
<body>
	<div class="background margin_up10 update_form">
		<form
			action="http://localhost:8080/dahyeon_free/memberServlet?cmd=update"
			method="post">
			<%
			memberVO member = (memberVO) request.getAttribute("member");
			%>
			<fieldset>
				<legend class="join_font">Information Update</legend>
				<ul>
					<li class="login_box">ID : <input type="text" name="id"
						value=<%=member.getId()%> readonly></li>
					<li class="login_box margin_up3">PASSWORD : <input type="password" name="passwd"
						value=<%=member.getPasswd()%> autofocus></li>
					<li class="login_box margin_up3">USERNAME : <input type="text" name="username"
						value=<%=member.getUsername()%>></li>
					<li class="login_box margin_up3">NICKNAME : <input type="text" name="nickname"
						value=<%=member.getNickname()%>></li>
					<li class="login_box margin_up3">MOBILE : <input type="text" name="mobile"
						value=<%=member.getMobile()%>></li>
					<li class="login_box margin_up3">EMAIL : <input type="text" name="email"
						value=<%=member.getEmail()%>></li>
				</ul>
			</fieldset>
			<br>
			<div class="margin_left_update">
				<fieldset>
					<input type="submit" name="submit" value="최종 수정" class="reverse_select">
					<input type="reset" name="reset" value="다시 작성" class="reverse_select">
				</fieldset>
			</div>
		</form>
	</div>
</body>
</html>