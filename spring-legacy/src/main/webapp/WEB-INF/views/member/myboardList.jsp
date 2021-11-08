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

	<!-- main -->
	<main id="main">

		<!-- Menu Box -->
		<div id="myBox">
			<div id="wrap">
				<div id="menu">
					<a>내 정보</a>
					<ul>
                        <li><a href="/member/modify">정보 수정</a></li>
                        <li><a href="/member/passwd">비밀번호 변경</a></li>
                        <li><a href="/member/remove">회원 탈퇴</a></li>
                        <li><a href="/member/myboardList">내가 쓴 게시물</a></li>
                        <li><a href="/member/myCommentList">내가 쓴 댓글</a></li>
                        <li><a href="/member/myQuizList">내가 만든 퀴즈</a></li>
                        <li><a href="/member/quizCheckList">내가 푼 퀴즈</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- End Menu Box -->

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<div class="row d-flex justify-content-center align-items-center" style="margin: 40px auto;">
					<div class="col col-lg-6 mb-4 mb-lg-0">
						<div class="card shadow text-center"
							style="background: #fff; border-radius: 1rem;">
							<div class="panel panel-primary p-3">
								<div class="panel-heading">
									<h3 class="panel-title text-white p-3"
										style="background-color: #112D4E; border-radius: 0.5rem; font-weight: bold;">내가 쓴 게시물</h3>
									<div class="pull-right">
										<label class="mt-3"><small>&laquo; 글개수 : ${ pageMaker.totalCount }&raquo;</small></label> <span class="clickable filter" data-toggle="tooltip"
											title="Toggle table filter" data-container="body"> <i class="glyphicon glyphicon-filter"></i>
										</span>
									</div>
								</div>
								<table class="table table-hover" id="myboard">
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성일</th>
										<th>조회수</th>
									</tr>

									<tbody>

										<c:choose>
											<c:when test="${ pageMaker.totalCount gt 0 }">

												<c:forEach var="myboardList" items="${myboardList}">
													<tr>
														<td><c:out value="${myboardList.num}" /></td>
														<td><a href="/board/view?num=${ myboardList.num }"><c:out value="${myboardList.subject}" /></a></td>
														<td><c:out value="${myboardList.memberId}" /></td>
														<td><fmt:formatDate value="${myboardList.regDate}" pattern="yyyy/MM/dd" /></td>
														<td><c:out value="${myboardList.viewCount}" /></td>
													</tr>
												</c:forEach>

											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="5">작성한 글이 없습니다.</td>
												</tr>
											</c:otherwise>
										</c:choose>

									</tbody>
								</table>
							</div>
						</div>
						<br>
						<ul class="pagination center">
							<%-- 이전 --%>
							<c:if test="${ pageMaker.prev eq true }">
								<li><a
									href="/member/myboardList?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">
								</a></li>
							</c:if>

							<%-- 페이지블록 내 최대 5개 페이지씩 출력 --%>
							<c:forEach var="i" begin="${ pageMaker.startPage }"
								end="${ pageMaker.endPage }" step="1">
								<li
									class="waves-effect ${ (pageMaker.cri.pageNum eq i) ? 'active' : '' }"><a
									href="/member/myboardList?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">${ i }</a></li>
							</c:forEach>

							<%-- 다음 --%>
							<c:if test="${ pageMaker.next eq true }">
								<li><a
									href="/member/myboardList?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">
								</a></li>
							</c:if>
						</ul>

						<div class="divider" style="margin: 30px 0;"></div>

						<form action="/member/myboardList" method="GET" id="frm">
							<input type="hidden" name="pageNum"
								value="${ pageMaker.cri.pageNum }">
							<div class="row">
								<div>
									<div class="input-field">
										<select name="type">
											<option value="" disabled selected>=선택=</option>
											<option value="subject"
												${ (pageMaker.cri.type eq 'subject') ? 'selected' : '' }>제목</option>
											<option value="content"
												${ (pageMaker.cri.type eq 'content') ? 'selected' : '' }>내용</option>
											<option value="memberId"
												${ (pageMaker.cri.type eq 'memberId')     ? 'selected' : '' }>작성자</option>
										</select> <label>검색 조건</label>
									</div>
								</div>

								<div>

									<div class="input-field">
										<i>search</i> <input type="text" id="autocomplete-input"
											class="autocomplete" name="keyword"
											value="${ pageMaker.cri.keyword }"> <label
											for="autocomplete-input">검색어</label>
									</div>

								</div>

								<div>
									<button type="submit" id="btnSearch">검색</button>
								</div>
							</div>
						</form>
					</div>
				
			</div>	
				
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