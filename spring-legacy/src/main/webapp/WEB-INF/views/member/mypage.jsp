<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main -->
  <main id="main" style="background-color: #DBE2EF;">

    <!-- Why Us Section -->
    <section id="why-us" class="why-us">
      <div class="container">
        <div class="row d-flex justify-content-center align-items-center" style="margin: 65px auto;">
          <div class="col col-lg-6 mb-4 mb-lg-0">
            <div class="card text-center text-white shadow" style="background: linear-gradient(to right bottom, #112D4E, #3F72AF); border-radius: 1rem;">
              <div class="row p-3">
                <div class="col-md-4" style="margin-top: 100px;">
                  <img src="/resources/img/unknown.png"class="img-fluid" style="width: 200px; border-radius: 100px;" />
                </div>
                <div class="col-md-8">
                  <div class="card-body p-4">
                    <h1><b>내 정보</b></h1>
                    <hr class="mt-0 mb-4">
                    <div class="row pt-1">
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">아이디</h6>
                        <p>example</p>
                      </div>
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">이름</h6>
                        <p>홍길동</p>
                      </div>
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">생년월일</h6>
                        <p>0000-00-00</p>
                      </div>
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">성별</h6>
                        <p>남자</p>
                      </div>
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">이메일</h6>
                        <p>example@naver.com</p>
                      </div>
                      <div class="col-6 mb-3">
                        <h6 style="font-weight: bold; color: #BBE1FA;">가입일</h6>
                        <p>0000-00-00</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="mt-2">
              <button type="button" class="btn btn-primary btn-sm">정보수정</button>
              <button type="button" class="btn btn-danger btn-sm">탈퇴</button>
            </div>
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

	<!-- Tob Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />

</body>

</html>