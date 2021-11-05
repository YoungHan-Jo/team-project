<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<main id="main">
		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<h1>퀴즈 리스트</h1>
				<br>
				<button class="btn-quizWrite" onclick="location.href='/quiz/write'">퀴즈 만들기</button>
				<div class="row" id="bunchList-form">
					<h3>퀴즈 리스트 (${ pageMaker.totalCount })</h3>
					<c:forEach var="bunch" items="${ bunchList }">
						<div class="col-md-4" style="border : 1px solid black;">
							<h4>${ bunch.num }. ${ bunch.title }</h4>
							<span>${ bunch.memberId }</span><br> 
							<span>${ bunch.regDate }</span>
							<button onclick="location.href='/quiz/content?bunchNum=${ bunch.num }'">문제 풀기</button>
							<button onclick="location.href='/quiz/modify?bunchNum=${ bunch.num }'">수정</button>
							<button id="btn-delete" onclick="location.href='/quiz/delete?bunchNum=${ bunch.num }'">삭제</button>
						</div>
						<br> <br>
					</c:forEach>
				</div>
			</div>


		</section>
		<!-- End Why Us Section -->

	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />

	<script>
		
	</script>

</body>
</html>