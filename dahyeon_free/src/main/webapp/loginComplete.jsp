<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/vote.css" type="text/css"></link>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=AR+One+Sans:wght@500&family=Black+Han+Sans&family=Dongle&family=Gowun+Dodum&family=Hi+Melody&family=Jua&family=Nanum+Gothic:wght@800&family=Open+Sans&family=Poor+Story&display=swap')
	;
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
		<div class="margin_center margin_up15">
			<c:if test="${not empty loggedInUser}">
				<p>${loggedInUser.username}님반갑습니다!!</p>
			</c:if>
			<c:if test="${empty loggedInUser}">
				<p>로그인이 실패했습니다. 다시 시도해주세요.</p>
			</c:if>

			<p class="font20">저희 서비스를 이용하시려면 이용하실 서비스를 선택해주세요!!</p>
			<div class="margin_up10">
				<a
					href="http://localhost:8080/dahyeon_free/voteServlet?cmd=generate"
					target="_self" class="select">투표글 작성하러가기</a> <a
					href="http://localhost:8080/dahyeon_free/voteServlet?cmd=voteList"
					target="_self" class="select">전체 투표 목록 보기 </a>
			</div>
		</div>
	</div>
</body>
</html>
