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
		<section id="why-us" class="board-view why-us">
			<div class="container">

				<h5>게시판 상세보기</h5>


				<section class="boardview-section">
					<h2>제목 : ${ board.subject }</h2>

					<ul>
						<li>작성자 : ${ board.memberId }</li>
						<li>작성일 : <fmt:formatDate value="${ board.regDate }"
								pattern="yyyy.MM.dd HH:mm:ss" />
						</li>
						<li>조회수 : ${ board.viewCount }</li>
					</ul>

					<div class="contents">
						<pre>${ board.content }</pre>
					</div>

					<div>
						첨부파일 :
						<c:choose>
							<%-- 첨부파일 존재 --%>
							<c:when test="${ fn:length(attachList) gt 0 }">
								<ul>
									<c:forEach var="attach" items="${ attachList }">
										<c:if test="${ attach.filetype eq 'O' }">
											<c:set var="fileCallPath"
												value="${ attach.uploadpath }/${ attach.uuid }_${ attach.filename }" />
											<li><a href="/download?fileName=${ fileCallPath }">
													${ attach.filename } </a></li>
										</c:if>
										<c:if test="${ attach.filetype eq 'I' }">
											<c:set var="fileCallPath"
												value="${ attach.uploadpath }/s_${ attach.uuid }_${ attach.filename }" />
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
						</c:choose>
					</div>

					<div class="btn-boardview">
						<button type="button"
							onclick="location='/board/modify?num=${ board.num }&pageNum=${ pageNum }'">글수정</button>
						<button type="button" onclick="remove(event)">글삭제</button>
						<button type="button"
							onclick="location='/board/reply?reRef=${ board.reRef }&reLev=${ board.reLev }&reSeq=${ board.reSeq }&pageNum=${ pageNum }'">답글</button>
						<button type="button"
							onclick="location='/board/list?pageNum=${ pageNum }'">글목록</button>
					</div>

				</section>

				<hr>
				<!-- 댓글 -->
				<div id="comment">
					<a>댓글</a>
					<ul class="commentList">
						<c:forEach items="${ commentList }" var="comment">
							<li>
								<div>
					    					<input type=hidden name="pageNum" value="${ pageNum }">
											<input type="hidden" name="boardNum" value="${ board.num }">
											<input type="hidden" name="num" value="${ comment.num }">
											<input type="hidden" name="reRef" value="${ comment.reRef }"> 
											<input type="hidden" name="reLev" value="${ comment.reLev }"> 
											<input type="hidden" name="reSeq" value="${ comment.reSeq }">
											<p>
												작성자 : ${comment.memberId}<br/>
												작성 날짜 : <fmt:formatDate value="${comment.regDate}" pattern="yyyy/MM/dd" /><br/>
												${ comment.content }
											</p>
											<a href="/comment/reply?num=${ comment.num }">대댓글</a>
											&nbsp;&nbsp; 
											<a href="/comment/modify?num=${ comment.num }">댓글수정</a>
											&nbsp;&nbsp; 
											<a href="/comment/commentRemove?num=${ comment.num }">댓글삭제</a>	
					    				</div>
							</li>
							<br>
						</c:forEach>
					</ul>
				</div>

				<br>
				<hr>
				<br>

				<div class="comment">

					<form method="post" action="/comment/commentWrite">

						<input type="hidden" name="pageNum" value="${ pageNum }">
						<input type="hidden" name="boardNum" value="${ board.num }">

						<div class="comment-div">
							<label for="id">작성자</label> 
							<input id="id" type="text" name="memberId" value="${ sessionScope.id }">
						</div>

						<div>
							<label for="textarea1">내용</label>
							<textarea id="textarea1" name="content"></textarea>
						</div>



						<br>
						<div class="btn-comment">
							<button type="submit">댓글등록</button>
						</div>

					</form>

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