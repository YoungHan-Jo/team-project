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
				<h3>글 상세보기</h3>
				<br>
				<table id="boardList">
					<tr>
						<th>제목</th>
						<td colspan="5">${ board.subject }</td>
					</tr>
					<tr>
						<th class="center-align">작성자</th>
						<td>${ board.memberId }</td>
						<th>작성일</th>
						<td><fmt:formatDate value="${ board.regDate }"
								pattern="yyyy.MM.dd HH:mm:ss" /></td>
						<th>조회수</th>
						<td>${ board.viewCount }</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="5"><pre>${ board.content }</pre></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td colspan="5"><c:choose>
								<%-- 첨부파일이 있으면 --%>
								<c:when test="${ fn:length(attachList) gt 0 }">
									<%-- attachCount gt 0 --%>
									<ul>
										<c:forEach var="attach" items="${ attachList }">
											<c:if test="${ attach.filetype eq 'O' }">
												<%-- 일반파일 --%>
												<%-- 다운로드할 일반파일 경로 --%>
												<c:set var="fileCallPath"
													value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
												<li><a href="/download?fileName=${ fileCallPath }">
														<i class="material-icons">file_present</i> ${ attach.filename }
												</a></li>
											</c:if>
											<c:if test="${ attach.filetype eq 'I' }">
												<%-- 이미지파일 --%>
												<%-- 썸네일 이미지 경로 --%>
												<c:set var="fileCallPath"
													value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />
												<%-- 원본 이미지 경로 --%>
												<c:set var="fileCallPathOrigin"
													value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
												<li><a href="/display?fileName=${ fileCallPathOrigin }">
														<img src="/display?fileName=${ fileCallPath }">
												</a></li>
											</c:if>
										</c:forEach>
									</ul>
								</c:when>
								<c:otherwise>
						첨부파일 없음
					</c:otherwise>
							</c:choose></td>
					</tr>
				</table>
				<a href="/board/modify?num=${ board.num }&pageNum=${ pageNum }">글수정</a>
				<a onclick="remove(event)">글삭제</a> <a href="/board/list">글목록</a>
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

	<script>
		function remove(event) {
			event.preventDefault(); // a태그 기본동작 막기

			var isRemove = confirm('이 글을 삭제하시겠습니까?');
			if (isRemove == true) {
				location.href = '/board/remove?num=${ board.num }';
			}
		}
	</script>
</body>
</html>