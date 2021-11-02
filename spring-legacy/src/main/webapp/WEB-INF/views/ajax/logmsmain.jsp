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
			<h1>Welcome to LOGO</h1>
			<h2>Take a quiz and make a quiz.</h2>
			<a href="#!" class="btn-get-started scrollto">quiz Start</a>
		</div>
	</section>
	<!-- End Hero -->

	<!-- main -->
	<main id="main">
		<br><br>
		<button type="button" id="btn1" onclick="location.href='/ajax/logmsSelectMember'">회원전체조회</button>
		<button type="button" id="btn2" onclick="location.href='/ajax/logmsDeleteMember'">회원전체조회</button>
		<button onclick="history.go(-1);">되돌아가기</button>
	</main>
	<!-- End main -->

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