<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
				<h3>내가 푼 퀴즈 리스트 (${ pageMaker.totalCount })</h3>
				<br>
				<table id="prevQuiz">
					<tr>
						<th>퀴즈 번호<th>
						<th>퀴즈 제목</th>
						<th>푼 날짜</th>
						<th>점수</th>
						<th>다시 풀기</th>
					</tr>

					<tbody>
						<c:forEach var="quizCheck" items="${ quizCheck }">
								<tr>
									<td><c:out value="${quizCheck.bunchNum}" /></td>
									<td><c:out value="${quizCheck.bunchVO.title}" /></td>
									<td><fmt:formatDate value="${quizCheck.solveDate}" pattern="yyyy/MM/dd" /></td>
									<td><c:out value="${quizCheck.point}" /></td>
									<td><a href="/quiz/content?bunchNum=${ quizCheck.bunchNum }">문제 다시 풀기</a></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<ul class="pagination center">
					<%-- 이전 --%>
					<c:if test="${ pageMaker.prev eq true }">
						<li>
						<a href="/member/myPrevQuizList?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#prevQuiz">
						</a></li>
					</c:if>

					<%-- 페이지블록 내 최대 5개 페이지씩 출력 --%>
					<c:forEach var="i" begin="${ pageMaker.startPage }"
						end="${ pageMaker.endPage }" step="1">
						<li
							class="waves-effect ${ (pageMaker.cri.pageNum eq i) ? 'active' : '' }"><a
							href="/member/myPrevQuizList?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#prevQuiz">${ i }</a>
							</li>
					</c:forEach>

					<%-- 다음 --%>
					<c:if test="${ pageMaker.next eq true }">
						<li>
						<a href="/member/myPrevQuizList?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#prevQuiz">
						</a></li>
					</c:if>
				</ul>

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