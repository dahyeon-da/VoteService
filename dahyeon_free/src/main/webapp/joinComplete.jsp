<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*"%>
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
	<%
	memberVO member = (memberVO) request.getAttribute("member");
	%>
	<div>
		<div class="flex_between">
			<div id="logo">
				<a class="btn"
				href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteList"
				target="_self"> <img src="resources/dahyeon_free_logo.png">
			</a>
			</div>
			<div class="restrict margin_up3 flex margin_right2">
				<a href="http://localhost:8080/dahyeon_free/memberServlet?cmd=join"
					target="_self" class="font20">JOIN</a> &nbsp;|&nbsp; <a
					href="http://localhost:8080/dahyeon_free/memberServlet?cmd=login"
					target="_self" class="font20">LOGIN</a>
			</div>
		</div>
		<div class="background margin_up8">
			<p id=sect><%=request.getAttribute("greetings")%><br>
			<div id=center>
				<table>
					<tr>
						<td class="color">계정</td>
						<td class="color">이름</td>
						<td class="color">닉네임</td>
						<td class="color">핸드폰</td>
						<td class="color">이메일</td>
					</tr>
					<tr>
						<td><%=member.getId()%></td>
						<td><%=member.getUsername()%></td>
						<td><%=member.getNickname()%></td>
						<td><%=member.getMobile()%></td>
						<td><%=member.getEmail()%></td>
					</tr>
				</table>
			</div>
			<div class="margin_left_joinComplete margin_up3">
				<a href="http://localhost:8080/dahyeon_free/memberServlet?cmd=list"
					target="_self" class="select">전체 회원 목록 보기 </a>
			</div>
		</div>
	</div>
</body>
</html>