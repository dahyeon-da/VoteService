<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.voteVO"%>
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
			action="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteUpdate"
			method="post">
			<%
			voteVO vote = (voteVO) request.getAttribute("voteNum");
			%>
			<fieldset>
				<legend>Vote Update</legend>
				<ul>
					<li>투표 번호 : <input type="text" name="voteNum"
						value=<%=vote.getVoteNum()%> readonly></li>
					<li>투표 제목 : <input type="text" name="voteTitle"
						value=<%=vote.getVoteTitle()%> autofocus></li>
					<li>투표 내용 : <input type="text" name="voteContent"
						value=<%=vote.getVoteContent()%>></li>
					<li>좋아요 수 : <input type="text" name="goodCount"
						value=<%=vote.getGoodCount()%>></li>
					<li>싫어요 수 : <input type="text" name="badCount"
						value=<%=vote.getBadCount()%>></li>
					<li>작성자 ID : <input type="text" name="memberID"
						value=<%=vote.getMemberID()%>></li>
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