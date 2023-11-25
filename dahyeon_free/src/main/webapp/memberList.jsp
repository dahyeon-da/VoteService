<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
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
	<div class="background">
		<header>Member List</header>
		<p>기능만 구현했습니당</p>
		<div>
			<table>
				<tr>
					<td id=yellow>계정</td>
					<td id=yellow>이름</td>
					<td id=yellow>닉네임</td>
					<td id=yellow>핸드폰</td>
					<td id=yellow>이메일</td>
					<td id=yellow>관리</td>
				</tr>
				<%
				@SuppressWarnings("unchecked")
				List<memberVO> memberList = (List<memberVO>) request.getAttribute("memberList");
				for (memberVO vo : memberList) {
				%>
				<tr>
					<td><%=vo.getId()%></td>
					<td><%=vo.getUsername()%></td>
					<td><%=vo.getNickname()%></td>
					<td><%=vo.getMobile()%></td>
					<td><%=vo.getEmail()%></td>
					<td><a
						href="http://localhost:8080/dahyeon_free/memberServlet?cmd=update&id=<%=vo.getId()%>"
						target="_self">수정</a> <a
						href="http://localhost:8080/dahyeon_free/memberServlet?cmd=delete&id=<%=vo.getId()%>"
						target="_self">삭제</a></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>