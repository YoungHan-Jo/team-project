<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
<style>
.quiz-form {
	border: 1px solid black;
}
</style>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<main id="main">
		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<h1>퀴즈 수정</h1>
				<br>

				<form action="/quiz/modify?bunchNum=${ bunch.num }" method="POST">

					<div id='quizBox'>
						<label>퀴즈 이름</label> <input type="text" name="title" value="${ bunch.title }" required> <br>
						
						<c:forEach var="quiz" items="${ bunch.quizList }">
							<div class="quiz-form">
								<button class="btn-delete">문제 삭제</button> <br>
								<label>문제</label><input type="text" name="questions" value="${ quiz.question }" required> <br>
								<label>1</label> <input type="text" name="numOnes" value="${ quiz.numOne }" required> <br>
								<label>2</label> <input type="text" name="numTwos" value="${ quiz.numTwo }" required> <br>
								<label>3</label> <input type="text" name="numThrees" value="${ quiz.numThree }" required> <br>
								<label>4</label> <input type="text" name="numFours" value="${ quiz.numFour }" required> <br>
								<label>정답</label> <input type="text" name="answers" value="${ quiz.answer }" required> <br>
							</div>
						</c:forEach>
						<br>
					</div>
					<div class="row">
						<button type="button" id="btn-addQuiz">+ 문제 추가</button>
					</div>
					<button type="submit">수정 하기</button>

				</form>

			</div>

		</section>
		<!-- End Why Us Section -->
	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->
	<script src="/resources/js/jquery-3.6.0.js"></script>

	<script>
		$('#btn-addQuiz').on('click', function() {
			const str = `<div class="quiz-form">
							<button class="btn-delete">문제 삭제</button> <br>
							<label>문제</label><input type="text" name="questions" required> <br> 
							<label>1</label> <input type="text" name="numOnes" required> <br> 
							<label>2</label> <input type="text" name="numTwos" required> <br> 
							<label>3</label> <input type="text" name="numThrees" required> <br> 
							<label>4</label> <input type="text" name="numFours" required> <br> 
							<label>정답</label> <input type="text" name="answers" required> <br>	
						 </div>
						 <br>`;
			$('#quizBox').append(str);
						 
		})
		
		$('#quizBox').on('click','button.btn-delete',function(){
			$(this).closest('div').remove();
		})
	</script>


</body>
</html>