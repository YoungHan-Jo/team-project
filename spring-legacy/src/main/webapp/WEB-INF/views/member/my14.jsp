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
				<h3>내가 만든 퀴즈 리스트 (${ pageMaker.totalCount })</h3>
				<br>
				<table id="myQuiz">
					<tr>
						<th>퀴즈 번호</th>
						<th>퀴즈 제목</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>

					<tbody>
						<c:forEach var="myQuiz" items="${ myQuiz }">
								<tr>
									<td><a href="/quiz/list?num=${ myQuiz.num }"><c:out value="${myQuiz.num}" /></a></td>
									<td><a href="/quiz/list?num=${ myQuiz.num }"><c:out value="${myQuiz.title}" /></a></td>
									<td><c:out value="${myQuiz.memberId}" /></td>
									<td><fmt:formatDate value="${myQuiz.regDate}" pattern="yyyy/MM/dd" /></td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<ul class="pagination center">
					<%-- 이전 --%>
					<c:if test="${ pageMaker.prev eq true }">
						<li>
						<a href="/member/myQuizList?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myQuiz">
						</a></li>
					</c:if>

					<%-- 페이지블록 내 최대 5개 페이지씩 출력 --%>
					<c:forEach var="i" begin="${ pageMaker.startPage }"
						end="${ pageMaker.endPage }" step="1">
						<li
							class="waves-effect ${ (pageMaker.cri.pageNum eq i) ? 'active' : '' }"><a
							href="/member/myQuizList?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myQuiz">${ i }</a>
							</li>
					</c:forEach>

					<%-- 다음 --%>
					<c:if test="${ pageMaker.next eq true }">
						<li>
						<a href="/member/myQuizList?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myQuiz">
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