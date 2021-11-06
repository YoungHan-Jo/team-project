<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/include/head.jsp" />

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

											<form action="/member/login" method="POST" id="login-frm" name="login-frm" class="login-frm">
												<div class="group">
													<label for="id" class="label">아이디</label>
													<input class="input" id="id" name="id" type="text" required>
												</div>
												<div class="group">
													<label for="passwd" class="label">비밀번호</label>
													<input class="input" id="passwd" name="passwd" type="password" required>
												</div>
												<div class="form-check">
													<input id="check" type="checkbox" class="check">
													<label class="input" for="check"><span class="icon"></span> 로그인 상태 유지</label>
												</div>
												<hr class="dropdown-divider">
												<div class="group">
													<input type="submit" class="button" value="로그인">
												</div>
											</form>

											<form action="/member/signUp" method="POST" id="sign-up-frm" name="sign-up-frm" class="sign-up-frm">
												<div class="group">
													<label for="id" class="label">아이디</label>
													<input class="input" id="signUpId" name="id" type="text" required>
												</div>
												<div class="group">
													<label for="passwd" class="label">비밀번호</label>
													<input class="input" id="signUpPasswd" name="passwd" type="password" required>
												</div>
												<div class="group">
													<label for="passwd2" class="label">비밀번호 재확인</label>
													<input class="input" id="signUpPasswd2" name="passwd2" type="password" required>
												</div>
												<hr class="dropdown-divider">
												<div class="group">
													<label for="name" class="label">이름</label>
													<input class="input" id="name" name="name" type="text">
												</div>
												<div class="group">
													<label for="birthday" class="label">생년월일</label>
													<input class="input" id="birthday" name="birthday" type="date">
												</div>
												<div class="group">
													<label for="gender" class="label">성별</label>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="gender" id="gender" value="M" checked>
														<label class="form-check-label">남자</label>
													</div>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="gender" id="gender" value="F">
														<label class="form-check-label">여자</label>
													</div>
												</div>
												<div class="group">
													<label for="email" class="label">이메일</label>
													<input class="input" id="email" name="email" type="email">
												</div>
												<div class="group">
													<label for="recvEmail" class="label">이메일 수신 여부</label>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="Y" checked>
														<label class="form-check-label">예</label>
													</div>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="recvEmail" id="recvEmail" value="N">
														<label class="form-check-label">아니오</label>
													</div>
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

	<!-- Top Button -->
	<div id="preloader"></div>
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center">
	<i class="bi bi-arrow-up-short"></i></a>

	<!-- JavaScript -->
	<jsp:include page="/WEB-INF/views/include/javascript.jsp" />
	
    <script>
	    $('#signUpPasswd2').on('focusout',function(){
			const signUpPasswd = $('#signUpPasswd').val();
			const signUpPasswd2 = $(this).val();
			
			if(signUpPasswd == signUpPasswd2){
				alert('비밀번호 일치함.');
			}else{
				alert('비밀번호 일치하지 않음.');
                $('input#signUpPasswd').focus().val('');
                $('input#signUpPasswd2').val('');
			}
			
		})
    
        $('input#signUpId').on({
        	
            focusout : function() {
            	const id = $(this).val();
                $.ajax({
                    url : '/api/members/' + id,
                    method : 'GET',
                    success : function(data) {
                        console.log(data);
                        console.log(typeof data);

                        if (data.count == 0) {
                            alert('중복된 아이디입니다.');
                            $('input#signUpId').focus().val('');
                        } else { // obj.count == 0
                            alert('사용 가능한 아이디입니다.');
                            $('input#signUpId').attr('readonly', true);
                        }
                    }
                });
            },
            
            keyup : function(event) {
                if (!(event.keyCode >= 37 && event.keyCode <= 40)) {
                	const id = $(this).val();
                    $(this).val(id.replace(/[^a-z0-9]/gi, ''));
                }
            }
            
        });
    </script>

</body>

</html>