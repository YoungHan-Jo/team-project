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
				<h1 id="title">채점 결과</h1>
				<div id="btn-return-form">
					<button class="btn-quiz return" onclick="location.href='/quiz/list'">목록으로 돌아가기</button>
				</div>
			</div>
		</div>
	
		<div id="body">
			<div class="container">
				<h3 class="point">점수 : ${ solveHistory.point } 점</h3>
				<div style="border-top : 5px solid black; height : 30px"></div>
				
				<h3>틀린 문제</h3>
				<c:if test="${ fn:length(incorrectList) eq 0 }">
					<h4>잘했어요~ 틀린 문제가 하나도 없네요~</h4>
				</c:if>
				<div class="incorrect-form" >
					<c:forEach var="quiz" items="${ incorrectList }">
					<div class="quiz-form" style="border-top : 1px solid black">
						<h4>${ quiz.questionNum }번 문제</h4>
						<p>${ quiz.question }</p>
						<span>1. ${ quiz.numOne }</span><br> 
						<span>2. ${ quiz.numTwo }</span><br> 
						<span>3. ${ quiz.numThree }</span><br>
						<span>4. ${ quiz.numFour }</span><br><br>
						<span>정답 : ${ quiz.answer }</span>
						<br><br><br>
					</div>
					</c:forEach>
				</div>
				<div style="border-top : 5px solid black; height : 30px"></div>
				<h3>맞은 문제</h3>
				<c:if test="${ fn:length(correctList) eq 0 }">
					<h4>노오력하세요! 맞은 문제가 하나도 없어요!</h4>
				</c:if>
				<div class="incorrect-form" >
					<c:forEach var="quiz" items="${ correctList }">
					<div class="quiz-form" style="border-top : 1px solid black">
						<h4>${ quiz.questionNum }번 문제</h4>
						<p>${ quiz.question }</p>
						<span>1. ${ quiz.numOne }</span><br> 
						<span>2. ${ quiz.numTwo }</span><br> 
						<span>3. ${ quiz.numThree }</span><br>
						<span>4. ${ quiz.numFour }</span><br><br>
						<span>정답 : ${ quiz.answer }</span>
						<br><br><br>
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