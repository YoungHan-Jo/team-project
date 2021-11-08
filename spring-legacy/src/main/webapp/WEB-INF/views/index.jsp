<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<h1>Welcome!</h1>
			<h2>Take a quiz and make a quiz.</h2>
			<a href="/quiz/list" class="btn-get-started scrollto">quiz Start</a>
		</div>
	</section>
	<!-- End Hero -->

	<!-- main -->
	<main id="main">

		<!-- Why Us Section -->
		<section id="why-us" class="why-us">
			<div class="container">

				<div class="row">
					<div class="col-lg-4 d-flex align-items-stretch">
						<div class="content">
							<h3>어떤 사이트일까?</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
								Duis aute irure dolor in reprehenderit Asperiores dolores sed et.
								Tenetur quia eos.
								Autem tempore quibusdam vel necessitatibus optio ad corporis.</p>
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
										<p>Consequuntur sunt aut quasi enim aliquam quae
										harum pariatur laboris nisi ut aliquip</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-cube-alt"></i>
										<h4>다양한 퀴즈가 있다</h4>
										<p>Excepteur sint occaecat cupidatat non proident,
										sunt in culpa qui officia deserunt</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-images"></i>
										<h4>점수를 확인 할 수 있다</h4>
										<p>Aut suscipit aut cum nemo deleniti aut omnis.
										Doloribus ut maiores omnis facere</p>
									</div>
								</div>
							</div>
						</div>
						<!-- End .content-->
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