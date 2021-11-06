<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
		<section id="why-us" class="boardwrite why-us">
			<div class="container">
				<h5>게시판 새글쓰기</h5>

				<form action="/board/write" method="POST"">
					<input type="hidden" name="pageNum" value="${ pageNum }">

					<div class="board-div">

						<div>
							<label for="id">아이디</label> <input id="id" type="text"
								name="memberId" value="${ sessionScope.id }">
						</div>

						<div>
							<label for="title">제목</label> <input type="text" id="title"
								class="validate" name="subject">
						</div>

						<div>
							<label for="textarea1">내용</label>
							<textarea id="textarea1" name="content"></textarea>
						</div>

					</div>

					<br>
					<div class="btn-wrap">
						<button type="submit">새글등록</button>
						&nbsp;&nbsp;
						<button type="reset">초기화</button>
						&nbsp;&nbsp;
						<button type="button" onclick="location.href = '/board/list'">글목록</button>
					</div>
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