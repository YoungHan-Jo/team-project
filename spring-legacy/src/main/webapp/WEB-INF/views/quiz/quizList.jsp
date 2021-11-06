<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
<style>
#head{
	padding-top: 50px;
	padding-bottom: 20px;
}
#body {
	background-color: #F6F7FB;
	padding : 50px 0;
}
#title{
	font-size: 50px;
}
.bunch-card {
	background-color: white;
	border-radius: 10px;
	margin : 10px;
	padding : 10px;
	
}
.bunch-card:hover {
	box-shadow: 0 8px 4px -4px rgba(0,0,255,1);
	cursor: pointer;
}
#btn-write-form{
	display: flex;
	justify-content: flex-end;
}
#btn-quizWrite{
	border: none;
	background-color: rgb(66, 87, 178);
	color: white;
	padding: 10px;
	border-radius: 20px;
	font-size: 20px;
}
.quizList-count-form{

}

</style>
</head>
<body>
	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<main id="main">
		<div id="head">
			<div class="container">
				<h1 id="title">QUIZ LIST</h1>
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
									<span>${ fn:length(bunch.quizList) }</span>
								</div>
								<span>${ bunch.memberId }</span><br>
							</div>
						</div>
						<br> <br>
					</c:forEach>
				</div>
			</div>


		</div>
		<!-- End Why Us Section -->

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