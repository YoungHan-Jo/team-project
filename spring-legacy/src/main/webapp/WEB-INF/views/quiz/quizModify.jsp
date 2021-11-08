<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<h1 id="title">QUIZ 수정</h1>
					<div id="btn-return-form">
						<button class="btn-quiz return" onclick="location.href='/quiz/list'">목록으로 돌아가기</button>
					</div>	
				</div>
			</div>
			<div id="body">
				<div class="container">
				<form action="/quiz/modify?bunchNum=${ bunch.num }" method="POST">

					<div id='quizBox'>
						<label class="modify-quiz-title">퀴즈 제목</label> <input class="modify-quiz-title input-form" type="text" name="title" value="${ bunch.title }" required> <br>
						
						<c:forEach var="quiz" items="${ bunch.quizList }">
							<div class="quiz-form">
								<label class="question-title">문제</label><input class="question-title input-form" type="text" name="questions" value="${ quiz.question }" required>
								<button class="btn-delete-question btn-quiz">문제 삭제</button> <br>
								<label>1</label> <input class="input-form" type="text" name="numOnes" value="${ quiz.numOne }" required> <br>
								<label>2</label> <input class="input-form" type="text" name="numTwos" value="${ quiz.numTwo }" required> <br>
								<label>3</label> <input class="input-form" type="text" name="numThrees" value="${ quiz.numThree }" required> <br>
								<label>4</label> <input class="input-form" type="text" name="numFours" value="${ quiz.numFour }" required> <br>
								<label>정답</label>
								<select class="selectpicker input-form input-search" name="answers" required>
								    <option value="1" ${ quiz.answer eq '1' ? 'selected' : '' }>1</option>
								    <option value="2" ${ quiz.answer eq '2' ? 'selected' : '' }>2</option>
								    <option value="3" ${ quiz.answer eq '3' ? 'selected' : '' }>3</option>
								    <option value="4" ${ quiz.answer eq '4' ? 'selected' : '' }>4</option>
								</select>
							</div>
						</c:forEach>
					</div>
					<div class="row">
						<button type="button" id="btn-addQuiz">+ 문제 추가</button>
					</div>
					<div id="btn-submit-form">
						<button type="submit" class="btn-quiz submit">수정 하기</button>
					</div>
				</form>
				</div>
			</div>
	</main>

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->
	<script src="/resources/js/jquery-3.6.0.js"></script>

	<script>
		$('#btn-addQuiz').on('click', function() {
			const str = `<div class="quiz-form">
							<label class="question-title">문제</label><input class="question-title input-form" type="text" name="questions" required>
							<button class="btn-delete-question btn-quiz">문제 삭제</button> <br>
							<label>1</label> <input class="input-form" type="text" name="numOnes" required> <br> 
							<label>2</label> <input class="input-form" type="text" name="numTwos" required> <br> 
							<label>3</label> <input class="input-form" type="text" name="numThrees" required> <br> 
							<label>4</label> <input class="input-form" type="text" name="numFours" required> <br> 
							<label>정답</label>
							<select class="selectpicker input-form input-search" name="answers" required>
								<option value="" disabled selected>--</option>
							    <option value="1">1</option>
							    <option value="2">2</option>
							    <option value="3">3</option>
							    <option value="4">4</option>
							</select>
						 </div>`;
			$('#quizBox').append(str);
						 
		})
		
		$('#quizBox').on('click','button.btn-delete-question',function(){
			$(this).closest('div').remove();
		})
	</script>


</body>
</html>