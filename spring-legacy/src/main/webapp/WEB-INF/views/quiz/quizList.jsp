<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
<link rel="stylesheet" type="text/css" href="/resources/css/quiz.css">
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<main id="main">
		<div id="head">
			<div class="container">
				<h1 id="title">QUIZ 목록</h1>
				<div id="btn-write-form">
					<button id="btn-quizWrite" onclick="location.href='/quiz/write'">퀴즈 만들기</button>				
				</div>	
			</div>
		</div>		
		<div id="body">
			<div class="container">
				
				<div class="row" id="bunchList-form">
					<c:forEach var="bunch" items="${ bunchList }">
						<div class="col-sm-6 col-md-4">
							<div class="bunch-card" onclick="location.href='/quiz/content?bunchNum=${ bunch.num }'">
								<h4>${ bunch.title }</h4>
								<div class="quizList-count-form">
									<span class="quizList-count">${ fn:length(bunch.quizList) } 문제</span>
								</div>
								<span>${ bunch.memberId }</span><br>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	<script>
		
	</script>

</body>
</html>