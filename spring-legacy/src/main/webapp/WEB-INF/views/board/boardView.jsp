<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<section id="why-us" class="board-view why-us">
			<div class="container">
				<div class="card col-sm-12 col-md-8 col-lg-10 p-5">

					<h5 class="fw-bold">게시판 상세보기</h5>
					<hr>

					<section class="boardview-section">
						<div class="btn-boardview">
							<button type="button" class="btn btn-outline-success" onclick="location='/board/modify?num=${ board.num }&pageNum=${ pageNum }'"><i class="bi bi-pencil-fill"></i>글수정</button>
							<button type="button" class="btn btn-outline-success" onclick="location='/board/reply?reRef=${ board.reRef }&reLev=${ board.reLev }&reSeq=${ board.reSeq }&pageNum=${ pageNum }'"><i class="bi bi-reply"></i>답글</button>
							<button type="button" class="btn btn-outline-danger" onclick="remove(event)"><i class="bi bi-trash2"></i>글삭제</button>
							<button type="button" class="btn btn-outline-info" onclick="location='/board/list?pageNum=${ pageNum }'"><i class="bi bi-list-task"></i>글목록</button>
						</div>

						<table class="table table-borderless" id="boardList">
							<tr>
								<th class="text-center ">제목</th>
								<td colspan="5">${ board.subject }</td>
							</tr>
							<tr>
								<th class="text-center">작성자</th>
								<td>${ board.memberId }</td>
								<th class="text-center">작성일</th>
								<td><fmt:formatDate value="${ board.regDate }" pattern="yyyy.MM.dd HH:mm:ss" /></td>
								<th class="text-center">조회수</th>
								<td>${ board.viewCount }</td>
							</tr>
							<tr>
								<th class="text-center">내용</th>
								<td colspan="5" class="fs-3 "><pre>${ board.content }</pre></td>
							</tr>
							<tr>
								<th class="text-center">첨부파일</th>
								<td colspan="5"><c:choose>
										<%-- 첨부파일 존재 --%>
										<c:when test="${ fn:length(attachList) gt 0 }">

											<ul>
												<c:forEach items="${ attachList }" var="attach">
													<c:if test="${ attach.filetype eq 'O' }">

														<c:set var="fileCallPath" value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
														<li><a href="/download?fileName=${ fileCallPath }"> <i class="material-icons">file_present</i> ${ attach.filename }
														</a></li>
													</c:if>
													<c:if test="${ attach.filetype eq 'I' }">

														<c:set var="fileCallPath" value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />

														<c:set var="fileCallPathOrigin" value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
														<li><a href="/display?fileName=${ fileCallPathOrigin }"> <img src="/display?fileName=${ fileCallPath }">
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
					</section>

					<hr>
					<!-- 댓글 -->
					<div id="comment">
						<a>댓글</a>
						<ul class="commentList">
							<c:forEach items="${ commentList }" var="comment">
								<table class="table-borderless table-sm">
									<tr>
										<th class="text-center ">작성자</th>
										<td> ${comment.memberId}</td>
										<th class="text-center ">작성날짜</th>
										<td><fmt:formatDate value="${comment.regDate}" pattern="yyyy/MM/dd" /></td>
									</tr>
									<tr>
										<th>내용</th>
										<td colspan=“3” class="text-center">${ comment.content }</td>
									</tr>
									<tr>
										<th><button class="btn btn-outline-success btn-sm" onclick="location='/comment/reply?num=${ comment.num }'"><i class="bi bi-reply"></i>댓글</button></th>
										<td><button class="btn btn-outline-success btn-sm" onclick="location='/comment/commentModify'"><i class="bi bi-pencil"></i>수정</button></td>
										<td><button class="btn btn btn-outline-danger btn-sm" onclick="location='/comment/commentRemove?num=${ comment.num }'"><i class="bi bi-trash2"></i>삭제</button></td>
								</table>
								<br>
							</c:forEach>
						</ul>
					</div>

					
					<div class="comment">

						<form method="post" action="/comment/commentWrite">

							<input type="hidden" name="pageNum" value="${ pageNum }"> <input type="hidden" name="boardNum" value="${ board.num }">

							<div class="comment-div">
								<label for="id">작성자</label>
								<input id="id" type="text" name="memberId" value="${ sessionScope.id }" readonly="readonly">
							</div>

							<div>
								<label for="textarea1">내용</label>
								<textarea id="textarea1" name="content"></textarea>
							</div>



							<br>
							<div class="btn-comment">
								<button  class="btn btn-outline-primary" type="submit"><i class="bi bi-pencil"></i>댓글등록</button>
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
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"> <i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	<script>
		function remove(event) {
			event.preventDefault(); // a태그 기본동작 막기

			var isRemove = confirm('이 글을 삭제하시겠습니까?');
			if (isRemove == true) {
				location.href = '/board/remove?num=${ board.num }&pageNum=${ pageNum }';
			}
		}
	</script>
</body>
</html>