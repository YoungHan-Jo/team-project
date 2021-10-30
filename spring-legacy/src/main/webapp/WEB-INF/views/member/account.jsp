<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />

<!-- Member Menu CSS Files -->
<link href="/resources/css/member.css" rel="stylesheet">
</head>

<body>

	<!-- Header -->
	<jsp:include page="/WEB-INF/views/include/top.jsp" />
	<!-- End Header -->

	<!-- main-->
	<main id="main" style="background-color: #DBE2EF;">

    <!-- Why Us Section -->
    <section id="why-us" class="why-us">
      <div class="container">
        <div id="view">
          <div class="row">
            <div class="col-md-6 mx-auto p-0">
              <div class="card">
                <div class="box">
                  <div class="snip">
                    <input id="tab-1" type="radio" name="tab" class="login" checked>
                    <label for="tab-1" class="tab">로그인</label>
                    <input id="tab-2" type="radio" name="tab" class="sign-up">
                    <label for="tab-2" class="tab">회원가입</label>

                    <div class="form-space">

                      <form action="" method="POST" id="login-frm" name="login-frm" class="login-frm">
                        <div class="group">
                          <label for="id" class="label">아이디</label>
                          <input id="id" name="id" type="text" class="input" required>
                        </div>
                        <div class="group">
                          <label for="passwd" class="label">비밀번호</label>
                          <input id="passwd" name="passwd" type="password" class="input" required>
                        </div>
                        <div class="form-check">
                          <input id="check" type="checkbox" class="check">
                          <label for="check"><span class="icon"></span> 로그인 상태 유지</label>
                        </div>
                        <hr class="dropdown-divider">
                        <div class="group">
                          <input type="submit" class="button" value="로그인">
                        </div>
                      </form>

                      <form action="/member/accountSignUp" method="POST" id="sign-up-frm" name="sign-up-frm" class="sign-up-frm">
                        <div class="group">
                          <label for="id" class="label">아이디</label>
                          <input id="id" name="id" type="text" class="input" required>
                        </div>
                        <div class="group">
                          <label for="passwd" class="label">비밀번호</label>
                          <input id="passwd" name="passwd" type="password" class="input" required>
                        </div>
                        <div class="group">
                          <label for="name" class="label">이름</label>
                          <input id="name" name="name" type="text" class="input">
                        </div>
                        <div class="group">
                          <label for="birthday" class="label">생년월일</label>
                          <input id="birthday" name="birthday" type="date" class="input">
                        </div>
                        <div class="group">
                          <label for="gender" class="label">성별</label>
                          <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="gender" value="M" checked>
                            <label class="form-check-label">남자</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" type="radio" name="gender" id="gender" value="F" >
                            <label class="form-check-label">여자</label>
                          </div>
                        </div>
                        <div class="group">
                          <label for="email" class="label">이메일</label>
                          <input id="email" name="email" type="email" class="input">
                        </div>
                        <div class="group">
                          <label for="recvEmail" class="label">이메일 수신 여부</label>
                          <div class="form-check">
                            <input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="Y" checked>
                            <label class="form-check-label">예</label>
                          </div>
                          <div class="form-check">
                            <input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="N" >
                            <label class="form-check-label">아니오</label>
                          </div>
                        </div>
                        <div class="group">
                          <label for="file" class="label">프로필</label>
                          <input id="file" name="file" type="file" class="input form-control-sm">
                        </div>
                        <hr class="dropdown-divider">
                        <div class="group">
                          <input type="submit" class="button" value="가입하기">
                        </div>
                      </form>

                    </div>
                    
                  </div>
                </div>
              </div>
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