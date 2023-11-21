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
		Vote Generate Complete
	</header>
	<%
		voteVO vote = (voteVO) request.getAttribute("vote");
	%>
	<p id=sect><%=request.getAttribute("greetings")%><br>
	<div class=div>
		<p id=sect>투표글 등록이 완료되었습니다.</p>
		<div id=center>
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
				<tr>
					<td><%=vote.getVoteNum()%></td>
					<td><%=vote.getVoteTitle()%></td>
					<td><%=vote.getVoteContent()%></td>
					<td><%=vote.getGoodCount()%></td>
					<td><%=vote.getBadCount()%></td>
					<td><%=vote.getVoteDate() %></td>
				</tr>
			</table>
		</div>
		<a href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteList"
			target="_self">전체 투표 목록 보기 </a>
	</div>
</body>
</html>