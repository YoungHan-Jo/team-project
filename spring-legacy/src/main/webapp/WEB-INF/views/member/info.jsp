<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 문자열 →  Date 객체 변환  -->
<fmt:parseDate value="${member.birthday}" pattern="yyyymmdd" var="parseBirthday"/>

<!-- Date 객체 → 문자열 변환  -->
<fmt:formatDate value="${pageScope.parseBirthday}" pattern="yyyy-mm-dd" var="strBirthday" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
<link href="/resources/css/sidemenu.css" rel="stylesheet"/>
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main -->
	<main id="main">

     	<!-- sidemenu -->
		<jsp:include page="/WEB-INF/views/include/sidemenu.jsp"></jsp:include>

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">
				<div class="row d-flex justify-content-center align-items-center" style="margin: 31px auto;">
					<div class="col col-lg-6 mb-4 mb-lg-0">
						<div class="card text-center text-white shadow" style="background: linear-gradient(to right bottom, #112D4E, #3F72AF); border-radius: 1rem;">
							<div class="row p-3">
								<div class="col-md-4" style="margin-top: 100px;">
									<c:choose>
	                                    <c:when test="${ not empty profileImg }">
	                                        <c:set var="fileCallPath" value="${ profileImg.uploadpath }/${profileImg.memberId}/s_${ profileImg.uuid }_${ profileImg.filename }" />
	                                        <img src="/display?fileName=${fileCallPath}" class="img-fluid" style="width: 180px; height: 180px; border-radius: 100px;" />
	                                    </c:when>
	                                    <c:otherwise>
	                                        <img src="/resources/img/unknown.png" class="img-fluid" style="width: 180px; height: 180px; border-radius: 100px;" />
	                                    </c:otherwise>  
	                                </c:choose>
								</div>
								<div class="col-md-8">
									<div class="card-body p-4">
										<h1><b>내 정보</b></h1>
										<hr class="mt-0 mb-4">
										<div class="row pt-1">
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">아이디</h6>
												<p>${member.id}</p>
											</div>
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">이름</h6>
												<p>${member.name}</p>
											</div>
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">생년월일</h6>
												<p>${strBirthday}</p>
											</div>
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">성별</h6>
												<p>${ member.gender eq 'M' ? '남자' : '여자' }</p>
												
											</div>
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">이메일</h6>
												<p>${member.email}</p>
											</div>
											<div class="col-6 mb-3">
												<h6 style="font-weight: bold; color: #BBE1FA;">가입일</h6>
												<p>${member.regDate}</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<button type="button" class="btn btn-dark btn-sm mt-2" data-bs-toggle="modal" data-bs-target="#logoutModal">로그아웃</button>
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
	
	<!-- Modal -->
	<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Logout</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      정말 로그아웃을 하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary"  onclick="location.href='/member/logout'">예</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
	      </div>
	    </div>
	  </div>
	</div>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />

</body>

</html>