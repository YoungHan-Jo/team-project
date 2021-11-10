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
					<c:if test="${ not empty id }">
						<button id="btn-quizWrite" onclick="location.href='/quiz/write'">퀴즈 만들기</button>				
					</c:if>
									
				</div>	
			</div>
		</div>		
		<div id="body">
			<div class="container">
				<div class="search-form">
					
				</div>
				<div class="row" id="bunchList-form">
					<c:if test="${ fn:length(bunchList) eq 0 }">
						<div class="no-result">
							<p>검색결과 없음</p>
						</div>
					</c:if>
					<c:forEach var="bunch" items="${ bunchList }">
						<div class="col-sm-6 col-md-4">
							<div class="bunch-card" onclick="location.href='/quiz/content?bunchNum=${ bunch.num }'">
								<h4>${ bunch.title }</h4>
								<div class="quizList-count-form">
									<span class="quizList-count">${ bunch.quizCount } 문제</span>
								</div>
								<span>${ bunch.memberId }</span><br>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="page-form">
					<ul class="pagination">
						<c:if test="${ pageMaker.prev eq true }">
							<li class="page-item">
								<a class="page-link" href="/quiz/list?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }">Previous</a>
							</li>						
						</c:if>
						<c:forEach var="i" begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1">
							<li class="page-item ${ pageMaker.cri.pageNum eq i ? 'active' : '' }">
								<a class="page-link" href="/quiz/list?pageNum=${ pageScope.i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }">${ i }</a>
							</li>
						</c:forEach>
						<c:if test="${ pageMaker.next eq true }">
							<li class="page-item"><a class="page-link" href="/quiz/list?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }">Next</a></li>
						</c:if>
					</ul>
				</div>
				<div class="search-form">
					<form action="/quiz/list" method="GET">
						<select class="selectpicker input-form input-search" name="type">
							<option value="" disabled selected>--</option>
						    <option value="title" ${ pageMaker.cri.type eq 'title' ? 'selected' : '' }>제목</option>
							<option value="memberId" ${ pageMaker.cri.type eq 'memberId' ? 'selected' : '' }>작성자</option>
						</select>
						<input class="input-form input-search" name="keyword" value="${ pageMaker.cri.keyword }">
						<button type="submit" class="btn-search">검색</button>
					</form>
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