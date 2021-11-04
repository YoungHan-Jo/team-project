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
				<h3>내가 글 목록</h3>
				<br>
				<form action="/myboardlist" method="post" id="frm" >
					<table>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>

						<c:forEach var="myboardList" items="${myboardList}" >
							<tr>
								<td><c:out value="${myboardList.num}" /></td>
								<td><a href="/board/view?num=${ myboardList.num }"><c:out value="${myboardList.subject}" /></a></td>
								<td><c:out value="${myboardList.memberId}" /></td>
								<td><fmt:formatDate value="${myboardList.regDate}" pattern="yyyy/MM/dd" /></td>
								<td><c:out value="${myboardList.viewCount}" /></td>
							</tr>
						</c:forEach>
					</table>
				</form>
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
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
</body>
</html>