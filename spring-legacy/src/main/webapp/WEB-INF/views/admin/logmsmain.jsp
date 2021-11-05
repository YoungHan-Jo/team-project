<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- Hero -->
	<section id="hero" class="d-flex align-items-center text-center">
		<div class="container">
			<button type="button" id="btn1" onclick="location.href='/admin/logmsSelectMember'">회원전체조회</button><br>
			<button type="button" id="btn2" onclick="location.href='/admin/logmsDeleteMember'">회원삭제</button><br>
			<button type="button" id="btn3" onclick="location.href='/admin/logmsSelectBoards'">게시글조회</button><br>
			<button type="button" id="btn4" onclick="location.href='/admin/logmsInsertBoard'">게시글입력</button><br>
			<button type="button" id="btn5" onclick="location.href='/admin/logmsDeleteBoard'">게시글삭제</button><br>
			<button type="button" id="btn6" onclick="location.href='/admin/logmsSelectQuiz'">퀴즈조회</button><br>
			<button type="button" id="btn7" onclick="location.href='/admin/logmsSelectNotice'">공지사항</button><br>
			<button onclick="history.go(-1);">되돌아가기</button>
		</div>
	</section>


	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Tob Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />

</body>

</html>