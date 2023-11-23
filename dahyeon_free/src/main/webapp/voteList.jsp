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
					<td><%=vo.getMemberID()%></td>
					<td><%=vo.getGoodCount()%></td>
					<td><%=vo.getBadCount()%></td>

					<td><a
						href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteUpdate&voteNum=<%=vo.getVoteNum()%>"
						target="_self">수정</a> <a
						href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteDelete&voteNum=<%=vo.getVoteNum()%>"
						target="_self">삭제</a>
						<button><img src="resources/like.png"></button>
						<button><img src="resources/unlike.png"></button></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
</body>
</html>