<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
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
				<h3>게시판</h3>
				<br>
				<form role="form" method="post" action="/board/write">
					<table>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>

						<c:forEach items="${boardList}" var="list">
							<tr>
								<td><c:out value="${list.num}" /></td>
								<td><a href="/board/view?num=${ list.num }"><c:out
											value="${list.subject}" /></a></td>
								<td><c:out value="${list.memberId}" /></td>
								<td><fmt:formatDate value="${list.regDate}"
										pattern="yyyy/MM/dd" /></td>
								<td><c:out value="${list.viewCount}" /></td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>

			<button type="button" onclick="location.href='/board/write'">글쓰기</button>
		</section>
		<!-- End Why Us Section -->

	</main>


	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
</body>
</html>