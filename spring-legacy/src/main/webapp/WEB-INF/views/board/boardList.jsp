<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<main id="main" class="container">
		<!-- Why Us Section -->
		<section id="why-us" class="board-section why-us">
			<div class="card col-md-12 lg-6 p-5 rounded">
				<div class="justify-content-center text-center">
					<h3 class="fw-bold">게시판</h3>
				</div>
				<br>
				<h5>일반 게시판 (글개수: ${ pageMaker.totalCount })</h5>

				<c:if test="${ not empty sessionScope.id }">
					<div class="write-btn row">

						<button class="btn-board btn btn-outline-success" type="button"  onclick="location='/board/write?pageNum=${ pageMaker.cri.pageNum }'">
							<i class="bi bi-pencil-fill"></i> 새글쓰기
						</button>
						
					</div>
				</c:if>
				<br>
				<table id="board" class="table table-hover">
					<thead>
						<tr class="table-secondary">
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>

					<tbody>

						<c:choose>
							<c:when test="${ pageMaker.totalCount gt 0 }">

								<c:forEach var="board" items="${ boardList }">

									<tr onclick="location.href='/board/view?num=${ board.num }&pageNum=${ pageMaker.cri.pageNum }'">
										<td>${ board.num }</td>
										<td><c:if test="${ board.reLev gt 0 }">
												<%-- 답글 --%>
												<span style="width: ${ board.reLev * 15 }px"></span>
												<i class="bi bi-arrow-return-right"></i>
											</c:if> ${ board.subject }</td>
										<td>${ board.memberId }</td>
										<td><fmt:formatDate value="${ board.regDate }" pattern="yyyy.MM.dd" /></td>
										<td>${ board.viewCount }</td>
									</tr>

								</c:forEach>

							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">게시판 글이 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>

					</tbody>
				</table>

				<br>
				<ul class="pagination justify-content-center">
					<%-- 이전 --%>
					<c:if test="${ pageMaker.prev eq true }">
						<li class="page-item"><a class="page-link" href="/board/list?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#board"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>

					<%-- 페이지블록 내 최대 5개 페이지씩 출력 --%>
					<c:forEach var="i" begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1">
						<li class="page-item ${ (pageMaker.cri.pageNum eq i) ? 'active' : '' }"><a class="page-link" href="/board/list?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#board">${ i }</a></li>
					</c:forEach>

					<%-- 다음 --%>
					<c:if test="${ pageMaker.next eq true }">
						<li class="page-item"><a class="page-link" href="/board/list?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#board"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>

				<div class="divider" style="margin: 30px 0;"></div>

				<form action="#!" method="GET" id="frm">
					<div class="search row">

						<div class="input-field">
							<select name="type">
								<option value="" disabled selected>=선택=</option>
								<option value="subject" ${ (pageMaker.cri.type eq 'subject') ? 'selected' : '' }>제목</option>
								<option value="content" ${ (pageMaker.cri.type eq 'content') ? 'selected' : '' }>내용</option>
								<option value="memberId" ${ (pageMaker.cri.type eq 'memberId')     ? 'selected' : '' }>작성자</option>
							</select> <input type="text" id="autocomplete-input" class="autocomplete" name="keyword" value="${ pageMaker.cri.keyword }"> <label for="autocomplete-input"></label>

							<button type="button" id="btnSearch" class="btn btn-outline-info">
								<i class="bi bi-search"></i>검색
							</button>

						</div>
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
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"> <i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	<script>
		// 검색 버튼 클릭시
		$('#btnSearch').on('click', function() {

			var query = $('#frm').serialize();
			console.log(query);

			location.href = '/board/list?' + query + '#board';
		});
	</script>
</body>
</html>