<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<h1>퀴즈 만들기</h1>
				<br>

				<form action="/quiz/write" method="POST">

					<div id='quizBox'>
						<div style="border: solid 1px;">
							<label>퀴즈 이름</label> <input type="text" name="title" required> <br>
							<label>문제</label><input type="text" name="questions" required> <br> 
							<label>1</label> <input type="text" name="numOnes" required> <br> 
							<label>2</label> <input type="text" name="numTwos" required> <br> 
							<label>3</label> <input type="text" name="numThrees" required> <br> 
							<label>4</label> <input type="text" name="numFours" required> <br> 
							<label>정답</label> <input type="text" name="answers" required> <br>
						</div>
						<br>
					</div>
					<div class="row">
						<button type="button" id="btn-addQuiz">+ 문제 추가</button>
					</div>
					<button type="submit">만들기</button>







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
			const str = `<div style="border: solid 1px;">
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
	</script>


</body>
</html>