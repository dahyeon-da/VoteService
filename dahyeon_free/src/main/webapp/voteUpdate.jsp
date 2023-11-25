<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.voteVO"%>
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
			action="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteUpdate"
			method="post">
			<%
			voteVO vote = (voteVO) request.getAttribute("voteNum");
			%>
			<fieldset>
				<legend class="join_font">Vote Update</legend>
				<p>고치는건 잘 되는데... 불러오는게 잘 안됩니다.. 하지만 정보 update는 됩니다..<br>그리고 회원가입 정보는 잘 됩니다...</p>
				<ul>
					<li class="login_box">투표 번호 : <input type="text" name="voteNum"
						value=<%=vote.getVoteNum()%> class="input_box" readonly></li>
					<li class="login_box margin_up3">투표 제목 : <input type="text"
						name="voteTitle" class="input_box" value=<%=vote.getVoteTitle()%> autofocus
						class="input_box"></li>
					<li class="login_box margin_up3">투표 내용 : <input type="text"
						name="voteContent"  class="input_box" value=<%=vote.getVoteContent()%>
						class="input_box" class="input_box"></li>
					<li class="login_box margin_up3">작성자 ID : <input type="text"
						name="memberID" value=<%=vote.getMemberID()%> class="input_box"></li>
				</ul>
			</fieldset>
			<br>
			<div class="margin_left_update">
				<fieldset>
					<input type="submit" name="submit" value="최종 수정"
						class="reverse_select"> <input type="reset" name="reset"
						value="다시 작성" class="reverse_select">
				</fieldset>
			</div>
		</form>
	</div>
</body>
</html>