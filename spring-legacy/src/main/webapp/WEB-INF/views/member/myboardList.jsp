<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 문자열 →  Date 객체 변환  -->
<fmt:parseDate value="${member.birthday}" pattern="yyyymmdd"
	var="dateBirthday" />
<!-- Date 객체 → 문자열 변환  -->
<fmt:formatDate value="${pageScope.dateBirthday}" pattern="yyyy-mm-dd"
	var="strBirthday" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />

<style>
#myBox {
	position: absolute;
	margin-top: 100px;
	font-size: 70%;
	line-height: 1;
	border: 0;
	padding: 0;
	outline: none;
	box-sizing: border-box;
	vertical-align: baseline;
}

#myBox #wrap {
	display: block;
	width: 900px;
	margin: 0 auto;
	padding-top: 35px;
}

#myBox #menu {
	padding: 13px 16px;
	width: 150px;
	margin: 0 auto;
	position: relative;
	background: #112D4E;
	color: #fff;
	border-left: 4px solid #6c6d70;
	font-size: 1.5em;
	text-align: center;
	transition: all 0.15s linear;
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
}

#myBox #menu ul {
	position: absolute;
	top: 100%;
	left: -4px;
	width: 150px;
	padding: 5px 0px;
	border-left: 4px solid #8e9196;
	background: #fff;
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.3);
	list-style: none;
}

#myBox #menu ul li a {
	font-size: 0.85em;
	text-decoration: none;
	display: block;
	color: #3F72AF;
	padding: 7px 15px;
}

#myBox #menu ul li a:hover {
	color: #6fa0e9;
	background: #DBE2EF;
}

.form-check {
	display: inline-block;
	margin-left: 5px;
	margin-right: 8px;
}

.panel-heading div {
	margin-top: -18px;
	font-size: 15px;
}

.panel-heading div span {
	margin-left: 5px;
}
</style>
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
						<li><a href="/member/myreplyList">내가 쓴 댓글</a></li>
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
									<h3 class="panel-title text-white p-3" style="background-color: #112D4E; border-radius: 0.5rem; font-weight: bold;">내가 쓴 게시물</h3>
									<div class="pull-right">
										<label class="mt-3"><small>&laquo; 글개수 : ${ pageMaker.totalCount } &raquo;</small></label>
										<span class="clickable filter" data-toggle="tooltip" title="Toggle table filter" data-container="body">
											<i class="glyphicon glyphicon-filter"></i>
										</span>
									</div>
								</div>
								<table class="table table-hover" id="myboard">
									<thead>
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>작성일</th>
											<th>조회수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="myboardList" items="${myboardList}">
											<tr>
												<td><c:out value="${myboardList.num}" /></td>
												<td><a href="/board/view?num=${ myboardList.num }"><c:out value="${myboardList.subject}" /></a></td>
												<td><c:out value="${myboardList.memberId}" /></td>
												<td><fmt:formatDate value="${myboardList.regDate}" pattern="yyyy/MM/dd" /></td>
												<td><c:out value="${myboardList.viewCount}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<ul class="pagination justify-content-center mt-4">
							<c:if test="${ pageMaker.prev eq true }">
								<li class="page-item">
									<a href="/member/myboardList?pageNum=${ pageMaker.startPage - 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">
										<span aria-hidden="true">&laquo;</span>
									</a>
								</li>
							</c:if>

							<c:forEach var="i" begin="${ pageMaker.startPage }" end="${ pageMaker.endPage }" step="1">
								<li class="page-item ${ pageMaker.cri.pageNum eq i ? 'active' : '' }">
									<a class="page-link" href="/member/myboardList?pageNum=${ i }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">${ i }</a>
								</li>
							</c:forEach>

							<c:if test="${ pageMaker.next eq true }">
								<li class="page-item">
									<a href="/member/myboardList?pageNum=${ pageMaker.endPage + 1 }&type=${ pageMaker.cri.type }&keyword=${ pageMaker.cri.keyword }#myboard">
										<span aria-hidden="true">&raquo;</span>
									</a>
								</li>
							</c:if>
						</ul>

						<form action="#!" method="GET" id="frm">
							<div class="input-group mx-auto my-2" style="width: 60%">
								<div class="input-group-prepend">
									<select class="btn btn-dark px-2" name="type">
										<option value="" disabled selected>선택</option>
										<option value="subject" ${ (pageMaker.cri.type eq 'subject') ? 'selected' : '' }>제목</option>
										<option value="content" ${ (pageMaker.cri.type eq 'content') ? 'selected' : '' }>내용</option>
										<option value="memberId" ${ (pageMaker.cri.type eq 'memberId') ? 'selected' : '' }>작성자</option>
									</select>
								</div>
								<input id="autocomplete-input" type="text" class="form-control autocomplete" name="keyword" value="${ pageMaker.cri.keyword }">
								<div class="input-group-append">
									<button class="btn btn-dark px-3" type="button" id="btnSearch">검색</button>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</section>
		<!-- End Why Us Section -->

	</main>
	<!-- End main -->

	<!-- Footer -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
		<i class="bi bi-arrow-up-short"></i>
	</a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
</body>

</html>