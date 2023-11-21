<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div>
		<header>Vote List</header>
		<div>
			<table>
				<tr>
					<td>투표번호</td>
					<td>투표제목</td>
					<td>투표내용</td>
					<td>아이디</td>
					<td>좋아요 수</td>
					<td>싫어요 수</td>
					<td>투표 날짜</td>
				</tr>
				<%
				@SuppressWarnings("unchecked")
				List<voteVO> voteList = (List<voteVO>) request.getAttribute("voteList");
				for (voteVO vo : voteList) {
				%>
				<tr>
					<td><%=vo.getVoteNum()%></td>
					<td><%=vo.getVoteTitle()%></td>
					<td><%=vo.getVoteContent()%></td>
					<td><%=vo.getGoodCount()%></td>
					<td><%=vo.getBadCount()%></td>
					<td><%=vo.getVoteDate()%></td>

					<td><a
						href="http://localhost:8080/dahyeon_free/memberServlet?cmd=update&id=<%=vo.getMemberID()%>"
						target="_self">수정</a> <a
						href="http://localhost:8080/dahyeon_free/memberServlet?cmd=delete&id=<%=vo.getMemberID()%>"
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