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
				<h3>내가 쓴 댓글 목록(댓글개수: ${ pageMaker.totalCount })</h3>
				<br>
					<table id="myComment">
						<tr>
							<th>댓글 번호</th>
							<th>댓글 내용</th>
							<th>작성자</th>
							<th>작성 날짜</th>
						</tr>

						<c:forEach var="myComment" items="${myComment}" >
							<tr>
								<td><c:out value="${myComment.num}" /></td>
								<td><a href="/board/view?num=${ myComment.num }"><c:out value="${myComment.content}" /></a></td>
								<td><c:out value="${myComment.memberId}" /></td>
								<td><fmt:formatDate value="${myComment.regDate}" pattern="yyyy/MM/dd" /></td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<ul class="pagination center">
							<%-- 이전 --%>
							<c:if test="${ pageMaker.prev eq true }">
								<li>
								<a href="/member/myCommentList?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myComment">
								</a></li>
							</c:if>
		
							<%-- 페이지블록 내 최대 5개 페이지씩 출력 --%>
							<c:forEach var="i" begin="${ pageMaker.startPage }"
								end="${ pageMaker.endPage }" step="1">
								<li
									class="waves-effect ${ (pageMaker.cri.pageNum eq i) ? 'active' : '' }"><a
									href="/member/myCommentList?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myComment">${ i }</a></li>
							</c:forEach>
		
							<%-- 다음 --%>
							<c:if test="${ pageMaker.next eq true }">
								<li><a
									href="/member/myCommentList?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myComment">
									</a></li>
							</c:if>
						</ul>
		
						<div class="divider" style="margin: 30px 0;"></div>
		
						<form action="/member/myCommentList" method="GET" id="frm">
							<input type="hidden" name="pageNum" value="${ pageMaker.cri.pageNum }">
							<div class="row">
								<div>
									<div class="input-field">
										<select
											name="type">
											<option value="" disabled selected>=선택=</option>
											<option value="content"
												${ (pageMaker.cri.type eq 'content') ? 'selected' : '' }>내용</option>
											<option value="memberId"
												${ (pageMaker.cri.type eq 'memberId')     ? 'selected' : '' }>작성자</option>
										</select> <label>검색 조건</label>
									</div>
								</div>
	
								<div>
									
									<div class="input-field">
										<i>search</i> <input type="text"
											id="autocomplete-input" class="autocomplete" name="keyword"
											value="${ pageMaker.cri.keyword }">
											<label for="autocomplete-input">검색어</label>
									</div>
									
								</div>
		
								<div>
									<button type="submit" id="btnSearch">
										검색
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
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
</body>
</html>