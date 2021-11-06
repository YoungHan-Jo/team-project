<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<section id="why-us" class="why-us">
			<div class="container">

				<h5>게시판 답글쓰기</h5>
				<div class="divider" style="margin: 30px 0;"></div>

				<form action="/board/reply" method="POST"
					enctype="multipart/form-data">
					<input type="hidden" name="pageNum" value="${ pageNum }"> <input
						type="hidden" name="reRef" value="${ reRef }"> <input
						type="hidden" name="reLev" value="${ reLev }"> <input
						type="hidden" name="reSeq" value="${ reSeq }">

					<div class="board-div">

						<div>
							<label for="id">아이디</label> <input id="id" type="text"
								name="memberId" value="${ sessionScope.id }" readonly>
						</div>

						<div>
							<label for="title">제목</label> <input type="text" id="title"
								class="validate" name="subject">
						</div>

						<div class="form-floating">
							<textarea class="form-control" placeholder="Leave a comment here"
								id="floatingTextarea2" style="height: 100px" name="content"></textarea>
							<label for="textarea1">내용</label>
						</div>
						
						<div id="fileBox">
							<input type="file" name="files">
							<button class="file-delete">파일삭제</button>
						</div>


					</div>


					<br> <br>
					<div class="btn-wrap">
						<button type="submit">답글등록</button>
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