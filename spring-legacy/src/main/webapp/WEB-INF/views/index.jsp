<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- Hero -->
	<section id="hero" class="d-flex align-items-center text-center">
		<div class="container">
			<h1>Welcome to Quiz Site</h1>
			<h2>Take a quiz and make a quiz.</h2>
			<a href="/quiz/list" class="btn-get-started scrollto">quiz Start</a>
		</div>
	</section>
	<!-- End Hero -->

	<!-- main -->
	<main id="main m-0">

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">

				<div class="row">
					<div class="col-lg-4 d-flex align-items-stretch">
						<div class="content">
							<h3>어떤 사이트일까?</h3>
							<p>다양한 퀴즈를 통하여 반복, 지식 슥듭을 통하여 내가 필요한 지식을 향상 시켜주는 사이트 입니다.</p>
							<div class="text-center">
								<a href="/quiz/list" class="more-btn">QUIZ START
								<i class="bx bx-chevron-right"></i>
								</a>
							</div>
						</div>
					</div>
					<div class="col-lg-8 d-flex align-items-stretch">
						<div class="icon-boxes d-flex flex-column justify-content-center">
							<div class="row">
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-receipt"></i>
										<h4>복습이 된다</h4>
										<p>반복된 퀴즈 학습으로 다양한 문제를 복습 할 수 있습니다.</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-cube-alt"></i>
										<h4>다양한 퀴즈가 있다</h4>
										<p>다양한 퀴즈를 이용하여 여러가지 문제를 체험 할 수 있습니다.</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-images"></i>
										<h4>점수를 확인 할 수 있다</h4>
										<p>나의 퀴즈 점수를 확인하며 문제 학습 능력을 향상 시킬 수 있습니다.</p>
									</div>
								</div>
							</div>
						</div>
						<!-- End .content-->
					</div>
				</div>
				
				<div class="text-center mt-5">
				    <div>
				    <h2 class="panel-title text-white p-3" style="background-color: #112D4E; border-radius: 0.5rem; font-weight: bold;">한국산업인력공단_국가자격 시험일정 조회</h2>
				    </div>
				    <div>
				    <table class="table table-bordered table-hover bg-white" style="border: 3px solid #112D4E; font-size: 0.9em; border-radius: 0.5rem;">
				        <thead>
				            <tr style="background-color: #F9F7F7;">
				                <th>시행년도<br>(시행회차)</th>
				                <th>필기시험 원서접수<br>시작일 ~ 종료일</th>
				                <th>필기시험<br>시작일 ~ 종료일</th>
				                <th>필기시험 합격(예정)자<br>발표일</th>
				                <th>실기(작업)/면접시험 원서접수<br>시작일 ~ 종료일</th>
				                <th>실기(작업)/면접시험<br>시작일 ~ 종료일</th>
				                <th>실기(작업)/면접시험<br>발표일</th>
				            </tr>
				        </thead>
				        <tbody>
				            <c:choose>
				                <c:when test="${ fn:length(apiList) gt 0 }">
				                    <c:forEach var="item" items="${ apiList }">
				                        <tr>
				                            <td>${ item.implYyDTO }(${ item.implSeq })</td>
				                            <td>
				                            <fmt:parseDate var="docRegStartDt" value="${ item.docRegStartDt }" pattern="yyyyMMdd"/>
				                            <fmt:parseDate var="docRegEndDt" value="${ item.docRegEndDt }" pattern="yyyyMMdd"/>
				                            
				                            <fmt:formatDate value="${ docRegStartDt }" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${ docRegEndDt }" pattern="yyyy-MM-dd" />
				                            </td>
				                            <td><fmt:formatDate value="${ item.docExamStartDt }" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${ item.docExamEndDt }" pattern="yyyy-MM-dd" /></td>
				                            <td><fmt:formatDate value="${ item.docExamEndDt }" pattern="yyyy-MM-dd" /></td>
				                            <td><fmt:formatDate value="${ item.pracRegStartDt }" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${ item.pracRegEndDt }" pattern="yyyy-MM-dd" /></td>
				                            <td><fmt:formatDate value="${ item.pracExamStartDt }" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${ item.pracExamEndDt }" pattern="yyyy-MM-dd" /></td>
				                            <td><fmt:formatDate value="${ item.pracPassDt }" pattern="yyyy-MM-dd" /></td>
				                        </tr>
				                    </c:forEach>
				                </c:when>
				                <c:otherwise>
				                    <tr>
				                        <td colspan="7">데이터가 없습니다.</td>
				                    </tr>
				                </c:otherwise>
				            </c:choose>
				        </tbody>
				    </table>
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
	<i class="bi bi-arrow-up-short"></i></a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />

</body>

</html>