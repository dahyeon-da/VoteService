<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="tukorea.web.club.domain.*, java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/vote.css" type="text/css"></link>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=AR+One+Sans:wght@500&family=Black+Han+Sans&family=Dongle&family=Gowun+Dodum&family=Hi+Melody&family=Jua&family=Nanum+Gothic:wght@800&family=Open+Sans&family=Poor+Story&display=swap')
	;
</style>
</head>
<body>
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
		<div class="flex_column">
			<header class="header_font background margin_up5">Vote List</header>
			<div class="background">
			<p>원래 서비스였다면 수정과 삭제 버튼은 없었겠지만.. 교수님께서 보셔야하시니 넣었습니다...<br>그리고 좋아요와 싫어요 버튼은 누르면 DB에 잘 추가가 됩니다.<br>새로고침을 하시면 투표수가 올라간것을 확인하실 수 있습니다.</p>
				<table>
					<%
					@SuppressWarnings("unchecked")
					List<voteVO> voteList = (List<voteVO>) request.getAttribute("voteList");
					for (voteVO vo : voteList) {
					%>
					<tr>
						<td><%=vo.getVoteTitle()%></td>
						<td><%=vo.getMemberID()%></td>

						<td>
							<button class="btn_like btn" id="likeBtn<%=vo.getVoteNum()%>"
								onclick="updateCount('<%=vo.getVoteNum()%>', 'good')">
								<img src="resources/like.png" height="40px" width="40px">
							</button>
						</td>
						<td id="btn<%=vo.getVoteNum()%>"><%=vo.getGoodCount()%></td>
						<td>
							<button class="btn_like btn" id="dislikeBtn<%=vo.getVoteNum()%>"
								onclick="updateCount('<%=vo.getVoteNum()%>', 'bad')">
								<img src="resources/unlike.png" height="40px" width="40px">
							</button>
						<td id="btn<%=vo.getVoteNum()%>"><%=vo.getBadCount()%></td>
						<td><a
							href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteUpdate&voteNum=<%=vo.getVoteNum()%>"
							target="_self">수정</a><a
							href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteDelete&voteNum=<%=vo.getVoteNum()%>"
							target="_self">삭제</a></td>
					</tr>
					<%
					}
					%>
				</table>

				<div class="margin_up3 margin_bottom5">
					<a
						href="http://localhost:8080/dahyeon_free/voteServlet?cmd=generate"
						target="_self" class="reverse_select margin_left_generate">나도
						투표글 작성하러가기</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function updateCount(voteNum, type) {
		// Send an AJAX request to update the like or dislike count
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				// Update the like or dislike count on the page
				var countElement = document.getElementById(type + 'Count'
						+ voteNum);
				countElement.innerHTML = xhr.responseText;
			}
		};

		// Send the request to your server-side script (e.g., a servlet)
		xhr.open("GET",
				"http://localhost:8080/dahyeon_free/voteServlet?cmd=updateCount&voteNum="
						+ voteNum + "&type=" + type, true);
		xhr.send();
	}
</script>
</html>