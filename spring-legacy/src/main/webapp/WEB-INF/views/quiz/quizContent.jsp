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
				<h1>퀴즈 풀기</h1>
				<br>
				<button onclick="location.href='/quiz/list'">목록으로 돌아가기</button>
				
				<div>
					<h2>${ bunch.title }</h2>
					<form action="/quiz/submit?bunchNum=${ bunch.num }" method="POST">
					<button>제출하기</button>
						<c:forEach var="quiz" items="${ quizList }">
							<div style="border : 1px soild black">
							<h4>${ quiz.questionNum }. ${ quiz.question }</h4>
							<p><input type="radio" name="reply${ quiz.questionNum }" value="1" required>1번 ${ quiz.numOne }</p>
							<p><input type="radio" name="reply${ quiz.questionNum }" value="2" required>2번 ${ quiz.numTwo }</p>
							<p><input type="radio" name="reply${ quiz.questionNum }" value="3" required>3번 ${ quiz.numThree }</p>
							<p><input type="radio" name="reply${ quiz.questionNum }" value="4" required>4번 ${ quiz.numFour }</p>
							</div>
							<br>
						</c:forEach>
					</form>
				</div>
			</div>

		</section>
		<!-- End Why Us Section -->

	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Tob Button -->
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